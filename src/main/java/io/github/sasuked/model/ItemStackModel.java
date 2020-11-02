package io.github.sasuked.model;

import io.github.sasuked.menu.ItemBuilder;
import io.github.sasuked.text.StringHelper;
import lombok.Builder;
import lombok.Data;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.material.MaterialData;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.github.sasuked.text.StringHelper.translateToColorCode;

@Data
@Builder
public class ItemStackModel {
  
  private Material type;
  private int data;
  private int amount;
  private String displayName;
  private List<String> lore;
  private Map<Enchantment, Integer> itemEnchantments;
  
  public static ItemStackModel fromItemStack(ItemStack itemStack) {
    MaterialData materialData = itemStack.getData();
    
    Material itemType = materialData.getItemType();
    int data = materialData.getData();
    int amount = itemStack.getAmount();
    
    ItemStackModelBuilder modelBuilder = ItemStackModel.builder()
      .type(itemType)
      .data(data);
    
    ItemMeta itemMeta = itemStack.getItemMeta();
    if (itemMeta != null) {
      
      if (itemMeta.hasDisplayName()) {
        modelBuilder.displayName(translateToColorCode(itemMeta.getDisplayName()));
      }
      if (itemMeta.hasLore()) {
        modelBuilder.lore(StringHelper.translateListToColorCode(itemMeta.getLore()));
      }
      
      modelBuilder.itemEnchantments(itemMeta.getEnchants());
    }
    
    
    return modelBuilder.build();
  }
  
  public static ItemStackModel fromSection(ConfigurationSection section) {
    Material type = Material.valueOf(section.getString("type").toUpperCase());
    int data = section.getInt("data");
    int amount = section.getInt("amount");
    
    ItemStackModelBuilder modelBuilder = ItemStackModel.builder()
      .type(type)
      .data(data)
      .amount(amount);
    
    if (section.contains("displayName")) {
      modelBuilder.displayName(StringHelper.translateToColorCode(section.getString("displayName")));
    }
    
    if (section.contains("lore")) {
      List<String> lore = section.getStringList("lore");
      if (!lore.isEmpty()) {
        modelBuilder.lore(StringHelper.translateListToColorCode(lore));
      }
    }
    
    Map<Enchantment, Integer> enchantments = new HashMap<>();
    ConfigurationSection enchantmentSection = section.getConfigurationSection("enchantments");
    if (enchantmentSection != null) {
      for (String key : enchantmentSection.getKeys(false)) {
        Enchantment enchantment = Enchantment.getByName(key);
        if (enchantment == null) {
          continue;
        }
        
        int level = enchantmentSection.getInt(key);
        enchantments.put(enchantment, level);
      }
    }
    
    
    return modelBuilder
      .itemEnchantments(enchantments)
      .build();
  }
  
  public void save(String path, ConfigurationSection section) {
    section.set(path + ".type", type.name());
    section.set(path + ".data", data);
    section.set(path + ".amount", amount);
    section.set(path + ".displayName", displayName);
    section.set(path + ".lore", lore);
    
    itemEnchantments.forEach((enchantment, level) -> {
      section.set(path + ".enchantments." + enchantment.getName(), level);
    });
  }
  
  public ItemStack toItemStack() {
    ItemBuilder itemBuilder = new ItemBuilder(type, amount, data);
    if (displayName != null) {
      itemBuilder.name(StringHelper.colorize(displayName));
    }
    if (lore != null && !lore.isEmpty()) {
      itemBuilder.lore(StringHelper.colorizeList(lore));
    }
    
    return itemBuilder
      .withEnchantments(itemEnchantments)
      .build();
  }
}
