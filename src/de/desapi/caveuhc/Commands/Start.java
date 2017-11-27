package de.desapi.caveuhc.Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import de.desapi.caveuhc.Main.Main;

public class Start
  implements CommandExecutor
{
  @SuppressWarnings("static-access")
public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
  {
    if ((cmd.getName().equalsIgnoreCase("start")) && 
      (args.length == 0) && 
      (sender.hasPermission("CaveUHC.start"))) {
      if (Main.main.cd.lobby > 5)
      {
        Main.main.cd.lobby = 5;
        sender.sendMessage(Main.pr + "§aDu hast das Spiel vorzeitig gestartet!");
      }
      else
      {
        sender.sendMessage("§aDas Spiel läuft bereits!");
      }
    }
    return false;
  }
}
