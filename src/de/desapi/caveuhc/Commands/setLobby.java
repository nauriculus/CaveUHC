package de.desapi.caveuhc.Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import de.desapi.caveuhc.Main.Main;

public class setLobby
  implements CommandExecutor
{
  public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
  {
    if ((sender instanceof Player))
    {
      Player p = (Player)sender;
      if (p.hasPermission("CaveUHC.setspawn"))
      {
        if (args.length != 0)
        {
          p.sendMessage(Main.pr + "Zu viele Argumente!");
          return true;
        }
        Main.main.lm.setLocation("lobby", p.getLocation());
        p.sendMessage(Main.pr + "§7Du hast die §aLobby §7 gesetzt!");
      }
    }
    return false;
  }
}
