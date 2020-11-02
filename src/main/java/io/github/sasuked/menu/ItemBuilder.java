package io.github.sasuked.menu;

import io.github.sasuked.text.Description;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.material.MaterialData;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

public class ItemBuilder {
  
  private ItemStack itemStack;
  
  public ItemBuilder(Material material) {
    this(material, 1, 0);
  }
  
  public ItemBuilder(Material material, int data) {
    this(material, 1, data);
  }
  
  public ItemBuilder(MaterialData materialData) {
    this(materialData.getItemType(), materialData.getData());
  }
  
  public ItemBuilder(Material material, int amount, int data) {
    this(new ItemStack(material, amount, (byte) data));
  }
  
  public ItemBuilder(ItemStack itemStack) {
    this.itemStack = itemStack;
  }
  
  public ItemBuilder applyItemStack(Consumer<ItemStack> consumer) {
    consumer.accept(itemStack);
    return this;
  }
  
  public ItemBuilder applyItemMeta(Consumer<ItemMeta> consumer) {
    ItemMeta itemMeta = itemStack.getItemMeta();
    
    consumer.accept(itemMeta);
    
    itemStack.setItemMeta(itemMeta);
    return this;
  }
  
  public ItemBuilder name(String name) {
    return applyItemMeta(meta -> meta.setDisplayName(name));
  }
  
  public ItemBuilder lore(String... lore) {
    return applyItemMeta(meta -> meta.setLore(Arrays.asList(lore)));
  }
  
  public ItemBuilder lore(List<String> lore) {
    return applyItemMeta(meta -> meta.setLore(lore));
  }
  
  public ItemBuilder lore(Description description) {
    return lore(description.getContent());
  }
  
  public ItemBuilder hideAllFlags() {
    return removeFlags(ItemFlag.values());
  }
  
  public ItemBuilder removeFlags(ItemFlag... flag) {
    return applyItemMeta(im -> im.addItemFlags(flag));
  }
  
  public ItemBuilder amount(int amount) {
    return applyItemStack(stack -> stack.setAmount(amount));
  }
  
  public ItemBuilder withEnchantments(Map<Enchantment, Integer> enchantments) {
    return applyItemMeta(itemMeta ->
      enchantments.forEach(
        (enchantment, level) -> itemMeta.addEnchant(enchantment, level, true)
      )
    );
  }
  
  public ItemStack build() {
    return itemStack;
  }
}