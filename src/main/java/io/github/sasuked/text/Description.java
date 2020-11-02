package io.github.sasuked.text;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import org.bukkit.ChatColor;

import java.util.List;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Description implements Cloneable {
  
  private String[] content;
  
  public static Description of(String... content) {
    return new Description(content);
  }
  
  public static Description of(List<String> lore) {
    String[] text = new String[lore.size()];
    
    for (int i = 0; i < lore.size(); i++) {
      text[i] = lore.get(i);
    }
    
    return of(text);
  }
  
  public Description replace(String placeHolder, String replacement) {
    for (int i = 0, contentLength = content.length; i < contentLength; i++) {
      content[i] = content[i].replace(placeHolder, replacement);
    }
    
    return this;
  }
  
  public Description colorizeLines(ChatColor color) {
    for (int i = 0, contentLength = content.length; i < contentLength; i++) {
      content[i] = color.toString() + content[i];
    }
    
    return this;
  }
  
  public Description replace(String placeHolder, Object object) {
    return replace(placeHolder, String.valueOf(object));
  }
  
  public Description colorize() {
    for (int i = 0, contentLength = content.length; i < contentLength; i++) {
      content[i] = content[i].replace('&', '§');
    }
    
    return this;
  }
  
  public String[] getContent() {
    return content;
  }
  
  @Override
  public Description clone() {
    return new Description(content.clone());
  }
}