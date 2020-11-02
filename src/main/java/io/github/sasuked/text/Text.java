package io.github.sasuked.text;


import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;

/**
 * @author SaiintBrisson muito lindo obrigado mamaria sim
 */
public class Text extends TextComponent {
  public Text(TextComponent textComponent) {
    super(textComponent);
  }
  
  public Text(BaseComponent... extras) {
    super(extras);
  }
  
  public Text(String text) {
    super(text);
  }
  
  public Text() {
  }
  
  public Text text(String text) {
    getExtra().add(new TextComponent(text));
    return this;
  }
  
  public Text color(ChatColor color) {
    getCurrentComponent().setColor(color);
    return this;
  }
  
  public Text bold(boolean bool) {
    getCurrentComponent().setBold(bool);
    return this;
  }
  
  public Text italic(boolean bool) {
    getCurrentComponent().setItalic(bool);
    return this;
  }
  
  public Text underlined(boolean bool) {
    getCurrentComponent().setUnderlined(bool);
    return this;
  }
  
  public Text strikethrough(boolean bool) {
    getCurrentComponent().setStrikethrough(bool);
    return this;
  }
  
  public Text obfuscated(boolean bool) {
    getCurrentComponent().setObfuscated(bool);
    return this;
  }
  
  public Text insertion(String insertion) {
    getCurrentComponent().setInsertion(insertion);
    return this;
  }
  
  public Text clickEvent(ClickEvent.Action action, String value) {
    getCurrentComponent().setClickEvent(new ClickEvent(action, value));
    return this;
  }
  
  public Text hoverEvent(HoverEvent.Action action, String value) {
    getCurrentComponent().setHoverEvent(new HoverEvent(action, new BaseComponent[]{new TextComponent(value)}));
    return this;
  }
  
  public Text hoverEvent(HoverEvent.Action action, BaseComponent[] value) {
    getCurrentComponent().setHoverEvent(new HoverEvent(action, value));
    return this;
  }
  
  public Text extra(BaseComponent component) {
    addExtra(component);
    return this;
  }
  
  public BaseComponent getCurrentComponent() {
    if (getExtra().size() == 0) {
      return this;
    } else {
      return getExtra().get(getExtra().size() - 1);
    }
  }
}