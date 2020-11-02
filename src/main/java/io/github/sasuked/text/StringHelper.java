package io.github.sasuked.text;

import org.bukkit.ChatColor;

import java.util.List;
import java.util.stream.Collectors;

import static com.google.common.base.Strings.repeat;

public class StringHelper {
  
  public static String getProgressBar(
    int current,
    int max,
    int totalBars,
    char symbol,
    String completedColor,
    String notCompletedColor
  ) {
    float percent = (float) current / max;
    int progressBars = (int) (totalBars * percent);
    
    return repeat("" + completedColor + symbol, progressBars)
      + repeat("" + notCompletedColor + symbol, totalBars - progressBars);
  }
  
  public static String colorize(String string) {
    return ChatColor.translateAlternateColorCodes('&', string);
  }
  
  public static List<String> colorizeList(List<String> list) {
    return list.stream()
      .map(StringHelper::colorize)
      .collect(Collectors.toList());
  }
  
  public static String translateToColorCode(String string) {
    return string.replace('§', '&');
  }
  
  public static List<String> translateListToColorCode(List<String> list) {
    return list.stream()
      .map(StringHelper::translateToColorCode)
      .collect(Collectors.toList());
  }
}
