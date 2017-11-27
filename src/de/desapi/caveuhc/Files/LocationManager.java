package de.desapi.caveuhc.Files;

import java.io.File;
import java.io.IOException;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import de.desapi.caveuhc.Main.Main;

public class LocationManager
{
  public File file = new File("plugins/" + Main.main.getName(), "locations.yml");
  public FileConfiguration cfg = YamlConfiguration.loadConfiguration(this.file);
  
  public void setLocation(String name, Location loc)
  {
    this.cfg.set(name + ".world", loc.getWorld().getName());
    this.cfg.set(name + ".x", Double.valueOf(loc.getX()));
    this.cfg.set(name + ".y", Double.valueOf(loc.getY()));
    this.cfg.set(name + ".z", Double.valueOf(loc.getZ()));
    
    this.cfg.set(name + ".yaw", Float.valueOf(loc.getYaw()));
    this.cfg.set(name + ".pitch", Float.valueOf(loc.getPitch()));
    
    saveCfg();
  }
  
  
  public Location getLocation(String name)
  {
    World w = Bukkit.getWorld(this.cfg.getString(name + ".world"));
    double x = this.cfg.getDouble(name + ".x");
    double y = this.cfg.getDouble(name + ".y");
    double z = this.cfg.getDouble(name + ".z");
    Location loc = new Location(w, x, y, z);
    loc.setPitch(this.cfg.getInt(name + ".yaw"));
    loc.setPitch(this.cfg.getInt(name + ".pitch"));
    return loc;
  }
  
  
  public void setSpawn(int num, Location loc)
  {
    String name = "Spawn";
    
    this.cfg.set(name + "." + num + ".world", loc.getWorld().getName());
    this.cfg.set(name + "." + num + ".x", Double.valueOf(loc.getX()));
    this.cfg.set(name + "." + num + ".y", Double.valueOf(loc.getY()));
    this.cfg.set(name + "." + num + ".z", Double.valueOf(loc.getZ()));
    
    this.cfg.set(name + "." + num + ".yaw", Float.valueOf(loc.getYaw()));
    this.cfg.set(name + "." + num + ".pitch", Float.valueOf(loc.getPitch()));
    
    saveCfg();
  }
  
  public Location getSpawn(int num)
  {
    String name = "Spawn";
    World w = Bukkit.getWorld(this.cfg.getString(name + "." + num + ".world"));
    double x = this.cfg.getDouble(name + "." + num + ".x");
    double y = this.cfg.getDouble(name + "." + num + ".y");
    double z = this.cfg.getDouble(name + "." + num + ".z");
    Location loc = new Location(w, x, y, z);
    loc.setPitch(this.cfg.getInt(name + "." + num + ".yaw"));
    loc.setPitch(this.cfg.getInt(name + "." + num + ".pitch"));
    return loc;
  }
  
  
  public void saveCfg()
  {
    try
    {
      this.cfg.save(this.file);
    }
    catch (IOException localIOException) {}
  }

  
  public void setDeathmatch(int num, Location loc)
  {
    String name = "Deathmatch";
    this.cfg.set(name + "." + num + ".world", loc.getWorld().getName());
    this.cfg.set(name + "." + num + ".x", Double.valueOf(loc.getX()));
    this.cfg.set(name + "." + num + ".y", Double.valueOf(loc.getY()));
    this.cfg.set(name + "." + num + ".z", Double.valueOf(loc.getZ()));
    
    this.cfg.set(name + "." + num + ".yaw", Float.valueOf(loc.getYaw()));
    this.cfg.set(name + "." + num + ".pitch", Float.valueOf(loc.getPitch()));
    saveCfg();
  }
  
  public Location getDeathmatch(int num)
  {
    String name = "Deathmatch";
    World w = Bukkit.getWorld(this.cfg.getString(name + "." + num + ".world"));
    double x = this.cfg.getDouble(name + "." + num + ".x");
    double y = this.cfg.getDouble(name + "." + num + ".y");
    double z = this.cfg.getDouble(name + "." + num + ".z");
    Location loc = new Location(w, x, y, z);
    loc.setYaw(this.cfg.getInt(name + "." + num + ".yaw"));
    loc.setPitch(this.cfg.getInt(name + "." + num + ".pitch"));
    return loc;
  }
  
  public void DeathmatchTeleport()
  {
    int count = 1;
    for (Player alive : Main.main.alive)
    {
      alive.teleport(getDeathmatch(count));
      count++;
    }
  }


  public void mapTeleport()
  {
    int count = 1;
    for (Player alive : Main.main.alive)
    {
      alive.teleport(getSpawn(count));
      count++;
    }
  }
}
