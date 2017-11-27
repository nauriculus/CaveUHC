package de.desapi.caveuhc.Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import de.desapi.caveuhc.Main.Main;

public class setSpawn
  implements CommandExecutor
{
  public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
  {
    if ((sender instanceof Player))
    {
      Player p = (Player)sender;
      if (p.hasPermission("CaveUHC.setspawn"))
      {
    	  
        if (args.length != 1)
        {
          p.sendMessage(Main.pr + "§c/setspawn <Nummer>");
          return true;
        }
        try
        {
          int number = Integer.parseInt(args[0]);
          Main.main.lm.setSpawn(number, p.getLocation());
          p.sendMessage(Main.pr + "§7Du hast den Spawn §b" + number + " §7erfolgreich gesetzt!");
        }
        catch (NumberFormatException ex)
        {
          p.sendMessage(Main.pr + "§cDu musst eine Zahl eingeben!");
        }
        
      }
    }
    return false;
  }
}
