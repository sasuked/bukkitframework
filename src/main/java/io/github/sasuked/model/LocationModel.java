package io.github.sasuked.model;

import lombok.Builder;
import lombok.Data;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.ConfigurationSection;

@Data
@Builder
public class LocationModel {
  
  private String worldName;
  private double x;
  private double y;
  private double z;
  private double yaw;
  private double pitch;
  
  public static LocationModel fromLocation(Location location) {
    return LocationModel.builder()
      .worldName(location.getWorld().getName())
      .x(location.getX())
      .y(location.getY())
      .z(location.getZ())
      .yaw(location.getYaw())
      .pitch(location.getPitch())
      .build();
  }
  
  public static LocationModel fromSection(ConfigurationSection section) {
    String worldName = section.getString("world");
    double x = section.getDouble("x");
    double y = section.getDouble("y");
    double z = section.getDouble("z");
    double yaw = section.getDouble("yaw", 0);
    double pitch = section.getDouble("pitch", 0);
    
    return LocationModel.builder()
      .worldName(worldName)
      .x(x)
      .y(y)
      .z(z)
      .yaw(yaw)
      .pitch(pitch)
      .build();
  }
  
  public Location toLocation() {
    return new Location(
      Bukkit.getWorld(worldName),
      x,
      y,
      z,
      (float) yaw,
      (float) pitch
    );
  }
  
}
