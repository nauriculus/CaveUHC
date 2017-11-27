package de.desapi.caveuhc.CountDown;

import java.util.Iterator;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import de.desapi.caveuhc.Main.Main;

public class Countdown {

	
  //Booleans
  public static boolean lobbystarted = false;
  public static boolean restartstarted = false;
  
  //Integer
  public static int lobbycd;
  public static int gracecd;
  public static int ingamecd; 
  public static int deathmatchcd;
  public static int beforecd;
  public static int restartcd;
  public static int lobby = Main.main.lobby;
  static int grace = Main.main.grace;
  static int ingame = Main.main.ingame;
  static int before = Main.main.before;
  static int deathmatch = Main.main.deathmatch;
  static int restart = Main.main.restart;
  
  //Lobby Countdown
  public static void startLobbyCD()
  {
    Main.main.state = Gamestate.Lobby;
    if (!lobbystarted)
    {
      lobbystarted = true;
      lobbycd = Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.main, new Runnable()
      {
        public void run()
        {
          if (Countdown.lobby >= 1)
          {
            if (((Bukkit.getOnlinePlayers().size() == Main.main.max - 1) || 
              (Bukkit.getOnlinePlayers().size() == Main.main.max - 2)) && 
              (Countdown.lobby <= 5) && (Countdown.lobby >= 1))
            {
              Countdown.lobby = 30;
              for (Player all : Bukkit.getOnlinePlayers())
              {
                all.playSound(all.getLocation(), Sound.WITHER_DEATH, 1.0F, 1.0F);
                all.sendMessage(
                  Main.pr + "§cEs werden mehr Spieler benötigt, damit das Spiel startet"); //Message if there are not many players in the round.
              }
            }
            if ((Countdown.lobby == 60) || (Countdown.lobby == 30) || (Countdown.lobby == 20) || (Countdown.lobby == 10) || ((Countdown.lobby <= 5) && (Countdown.lobby >= 2))) {
              for (Player all : Bukkit.getOnlinePlayers())
              {
                all.playSound(all.getLocation(), Sound.NOTE_BASS, 1.0F, 1.0F);
                all.sendMessage(Main.pr + "§7Das Spiel startet in §b" + Countdown.lobby + " §7Sekunden");
              }
              
              if (((Bukkit.getOnlinePlayers().size() == Main.main.max - 1) || 
                      (Bukkit.getOnlinePlayers().size() == Main.main.max - 2)) && 
                      (Countdown.lobby <= 5) && (Countdown.lobby >= 1))
                    {
                      Countdown.lobby = 30;
                      for (Player all : Bukkit.getOnlinePlayers())
                      {
                        all.playSound(all.getLocation(), Sound.WITHER_DEATH, 1.0F, 1.0F);
                        all.sendMessage(
                          Main.pr + "§cEs werden mehr Spieler benötigt, damit das Spiel startet"); //Message if there are not many players in the round.
                      }
                    }

            }
            if ((Countdown.lobby <= 60) && (Countdown.lobby >= 1)) {
              for (Player all : Bukkit.getOnlinePlayers()) {
                all.setLevel(Countdown.lobby);
              }
            }
          }
          if (Countdown.lobby == 1) {
            for (Player all : Bukkit.getOnlinePlayers())
            {
              all.playSound(all.getLocation(), Sound.NOTE_BASS, 1.0F, 1.0F);
              all.sendMessage(Main.pr + "§7Das Spiel startet in §b" + Countdown.lobby + " §7Sekunde");
            }
          }
          if (Countdown.lobby == 0)
          {
            for (Player all : Bukkit.getOnlinePlayers())
            {
              Main.main.utils.clearPlayer(all); //Clear the player
              all.sendMessage(Main.pr + "§eMöge das Spiel beginnen...");
              
              	//Spitzhacke
              	ItemStack Pickaxe = createItem("§aSpitzhacke", Material.DIAMOND_PICKAXE, 1, (short) 0);
              	Pickaxe.addUnsafeEnchantment(Enchantment.DIG_SPEED, 1);
              	Pickaxe.setAmount(1);
              	ItemMeta Pickaxem = Pickaxe.getItemMeta();
              	Pickaxe.setItemMeta(Pickaxem);
              	all.getInventory().setItem(0, Pickaxe);
              	//Axt
              	ItemStack Axt = createItem("§aSpitzhacke", Material.IRON_AXE, 1, (short) 0);
              	Axt.addUnsafeEnchantment(Enchantment.DIG_SPEED, 1);
              	Axt.setAmount(1);
              	ItemMeta Axtm = Axt.getItemMeta();
              	Axt.setItemMeta(Axtm);
              	all.getInventory().setItem(1, Axt);
            }
            Main.main.lm.mapTeleport();
            Countdown.startgraceCD();

            Bukkit.getScheduler().cancelTask(Countdown.lobbycd);
          }
          Countdown.lobby -= 1;
        }
      }, 0L, 20L);
    }
  }
  
  
  public static void startgraceCD()
  {
    Main.main.state = Gamestate.Grace;
    gracecd = Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.main, new Runnable()
    {
      @SuppressWarnings("deprecation")
	public void run()
      {
    	  for (Player all : Bukkit.getOnlinePlayers()) {
          Main.main.alive.contains(all);
        }
        if (Countdown.grace >= 1)
        {
          for (Player all : Bukkit.getOnlinePlayers()) {
            if (Main.main.alive.contains(all)) {
              all.setGameMode(GameMode.SURVIVAL);
            }
          }
          if (Countdown.grace == 1) {
            for (Player all : Bukkit.getOnlinePlayers())
            {
              all.playSound(all.getLocation(), Sound.NOTE_BASS, 1.0F, 1.0F);
              all.sendTitle("§e" + Countdown.grace, " ");
              all.setLevel(Countdown.grace);
 
            }
          } else if ((Countdown.grace <= 3) && (Countdown.grace >= 2)) {
            for (Player all : Bukkit.getOnlinePlayers())
            {
              all.playSound(all.getLocation(), Sound.NOTE_BASS, 1.0F, 1.0F);
              all.sendTitle("§e" + Countdown.grace, " ");
              all.setLevel(Countdown.grace);
              
            }
          }
        }
        if (Countdown.grace == 0)
        {
          for (Player all : Bukkit.getOnlinePlayers()) {
            if (Main.main.alive.contains(all))
            {
           
              all.sendTitle("§eDas Spiel beginnt!", " ");
            }
          }
          Countdown.startIngameCD();
          Bukkit.getScheduler().cancelTask(Countdown.gracecd);
        }
        Countdown.grace -= 1;
      }
    }, 0L, 20L);
  }
  
  
  //InGame Countdown
  public static void startIngameCD()
  {
    Main.main.state = Gamestate.Ingame;
    ingamecd = Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.main, new Runnable()
    {
      public void run()
      {
        if ((Countdown.ingame == 0) || (Main.main.alive.size() == Main.main.dmstart) || 
          (Main.main.alive.size() <= Main.main.dmstart))
        {
          Countdown.startbeforecd();
          Bukkit.getScheduler().cancelTask(Countdown.ingamecd);
        }
        Countdown.ingame -= 1;
      }
    }, 0L, 20L);
  }
  
  
  public static void startbeforecd()
  {
    Main.main.state = Gamestate.Ingame;
    beforecd = Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.main, new Runnable()
    {
      public void run()
      {
        if (Countdown.before >= 1) {
          if ((Countdown.before == 60) || (Countdown.before == 30) || (Countdown.before == 10) || ((Countdown.before <= 5) && (Countdown.before >= 1))) {
            if (Countdown.before == 1) {
              for (Player all : Bukkit.getOnlinePlayers())
              {
                all.sendMessage(Main.pr + "§7Das Deathmatch beginnt in §b" + Countdown.before + " §7Sekunde");
                all.playSound(all.getLocation(), Sound.NOTE_BASS, 1.0F, 1.0F);
              }
            } else {
              for (Player all : Bukkit.getOnlinePlayers())
              {
                all.sendMessage(Main.pr + "§7Das Deathmatch beginnt in §b" + Countdown.before + " §7Sekunden");
                all.playSound(all.getLocation(), Sound.NOTE_BASS, 1.0F, 1.0F);
              }
            }
          }
        }
        if (Countdown.before == 0)
        {
          Main.main.lm.DeathmatchTeleport();
          Countdown.startDeathmatchCD();
          Bukkit.getScheduler().cancelTask(Countdown.beforecd);
        }
        Countdown.before -= 1;
      }
    }, 0L, 20L);
  }
  
  
  public static void startDeathmatchCD()
  {
    Main.main.state = Gamestate.DeathMatch;
    deathmatchcd = Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.main, new Runnable()
    {
      public void run()
      {
        if ((Countdown.deathmatch >= 1) && (
          (Countdown.deathmatch == 30) || (Countdown.deathmatch == 20) || (Countdown.deathmatch == 10) || (
          (Countdown.deathmatch <= 5) && (Countdown.deathmatch >= 2)))) {
          for (Player all : Bukkit.getOnlinePlayers())
          {
            all.playSound(all.getLocation(), Sound.NOTE_BASS, 1.0F, 1.0F);
            all.sendMessage(Main.pr + "§7Das Spiel endet in §b" + Countdown.deathmatch + " §7Sekunden");
          }
        }
        if (Countdown.deathmatch == 0)
        {
          Countdown.startRestartCD();
          Bukkit.getScheduler().cancelTask(Countdown.deathmatchcd);
        }
        Countdown.deathmatch -= 1;
      }
    }, 0L, 20L);
  }
  
  
  public static void startRestartCD()
  {
    Main.main.state = Gamestate.Restarting;
    if (!restartstarted)
    {
      restartstarted = true;
      restartcd = Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.main, new Runnable()
      {
        public void run()
        {
          Iterator localIterator1;
          Iterator localIterator2;
          if (Countdown.restart >= 1)
          {
            if (Countdown.restart == 15) {
              for (localIterator1 = Bukkit.getOnlinePlayers().iterator(); localIterator1.hasNext(); localIterator2.hasNext())
              {
                Player all = (Player)localIterator1.next();
                localIterator2 = Main.main.alive.iterator(); 
                continue;
              }
            }
            if ((Countdown.restart == 15) || (Countdown.restart == 10) || ((Countdown.restart <= 5) && (Countdown.restart >= 1))) {
              if (Countdown.restart == 1) {
                for (Player all : Bukkit.getOnlinePlayers()) {
                  all.sendMessage(
                    Main.pr + "§7Der Server restartet in §b" + Countdown.restart + " §7Sekunde");
                }
              } else {
                for (Player all : Bukkit.getOnlinePlayers()) {
                  all.sendMessage(
                    Main.pr + "§7Der Server restartet in §b" + Countdown.restart + " §7Sekunden");
                }
              }
            }
          }
          else if (Countdown.restart == 0)
          {
            for (Player all : Bukkit.getOnlinePlayers()) {
              all.kickPlayer("Reload");
            
            }
            Bukkit.getScheduler().cancelTask(Countdown.restartcd);
            Bukkit.shutdown();
          }
          Countdown.restart -= 1;
        }
      }, 0L, 20L);
    }
  }


  
  private static ItemStack createItem(String name, Material type, int amount, int id)
  {
    ItemStack i1 = new ItemStack(type);
    ItemMeta i1m = i1.getItemMeta();
    i1.setAmount(amount);
    i1m.setDisplayName(name);
    i1.setItemMeta(i1m);
    return i1;
  }
}