package de.desapi.caveuhc.Commands;

import de.desapi.caveuhc.Main.Main;
import de.desapi.caveuhc.Files.LocationManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class setDeathMatch implements CommandExecutor {
		
  public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
  {
    if ((sender instanceof Player))
    {
      Player p = (Player)sender;
      if (p.hasPermission("CaveUHC.setdm"))
      {
        if (args.length != 1)
        {
          p.sendMessage(Main.pr + "§7Benutze §e/setdeathmatch <Nummer>");
          return true;
        }
        try
        {
          int number = Integer.parseInt(args[0]);
          Main.main.lm.setDeathmatch(number, p.getLocation());
          p.sendMessage(Main.pr + "§7Du hast den Deathmatch-Spawn §b" + number + " §7gesetzt");
        }
        catch (NumberFormatException ex)
        {
          p.sendMessage(Main.pr + "§7Du musst eine §bZahl §7eingeben");
        }
      
    }
  }
	return false;
}
}