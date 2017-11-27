package de.desapi.caveuhc.Files;

import java.io.File;
import java.io.IOException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import de.desapi.caveuhc.Main.Main;

public class FileManager
{
  public File file = new File("plugins/" + Main.main.getName(), "config.yml");
  public FileConfiguration cfg = YamlConfiguration.loadConfiguration(this.file);
  
  public void register()
  {
    this.cfg.options().copyDefaults(true);
    this.cfg.addDefault("Prefix", Main.pr);
    this.cfg.addDefault("MinPlayer", Integer.valueOf(Main.main.min));
    this.cfg.addDefault("MaxPlayer", Integer.valueOf(Main.main.max));
    saveCfg();
    Main.main.min = this.cfg.getInt("MinPlayer"); 
    Main.main.max = this.cfg.getInt("MaxPlayer"); 
  }
  
  public void saveCfg()
  {
    try
    {
      this.cfg.save(this.file);
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
  }
}
