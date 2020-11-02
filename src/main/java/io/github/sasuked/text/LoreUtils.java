package io.github.sasuked.text;

import com.google.common.base.Joiner;
import org.bukkit.ChatColor;

import java.util.LinkedList;
import java.util.List;

public class LoreUtils {
  
  public static String getLastColors(String text) {
    int textLength = text.length();
    StringBuilder colors = new StringBuilder();
    
    for (int charIndex = textLength - 1; charIndex > 0; charIndex--) {
      char c = text.charAt(charIndex);
      ChatColor color = ChatColor.getByChar(c);
      char behindChar = text.charAt(charIndex - 1);
      
      if (color == null || behindChar != '§') {
        if (colors.length() > 0) break;
      } else {
        charIndex--;
        colors.append(c);
      }
    }
    
    return colors.length() > 0 ? "§" + Joiner.on("§").join(colors.reverse().toString().split("")) : null;
  }
  
  public static List<String> wrapText(String text) {
    return wrapText(text, ChatColor.GRAY, 5);
  }
  
  public static List<String> wrapText(String text, ChatColor startColor, int wordsPerLine) {
    List<String> textWrapped = new LinkedList<>();
    
    String[] textWords = text.split(" ");
    int wordsLength = textWords.length;
    
    StringBuilder line = new StringBuilder();
    int wordsInLine = 0;
    String lastColors = startColor != null ? startColor.toString() : "";
    
    for (int i = 0; i <= wordsLength; i++) {
      boolean isLastWord = i == wordsLength;
      if (isLastWord || wordsInLine >= wordsPerLine) {
        String lineAsString = line.toString();
        String string = lastColors + lineAsString;
        textWrapped.add(string);
        
        String textLastColors = getLastColors(lineAsString);
        if (textLastColors != null) {
          lastColors = textLastColors;
        }
        
        line = new StringBuilder();
        wordsInLine = 0;
      }
      if (isLastWord) break;
      
      String word = textWords[i];
      
      if (wordsInLine != 0) {
        line.append(" ");
      }
      
      line.append(word);
      wordsInLine++;
    }
    
    return textWrapped;
  }
  
}
