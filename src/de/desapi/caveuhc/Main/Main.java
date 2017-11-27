package de.desapi.caveuhc.Main;

import java.util.ArrayList;
import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitTask;

import de.desapi.caveuhc.Commands.Start;
import de.desapi.caveuhc.Commands.setDeathMatch;
import de.desapi.caveuhc.Commands.setLobby;
import de.desapi.caveuhc.Commands.setSpawn;
import de.desapi.caveuhc.CountDown.Countdown;
import de.desapi.caveuhc.CountDown.Gamestate;
import de.desapi.caveuhc.Files.FileManager;
import de.desapi.caveuhc.Files.LocationManager;
import de.desapi.caveuhc.Listener.DeathListener;
import de.desapi.caveuhc.Listener.Events;
import de.desapi.caveuhc.Listener.Teleporter;
import de.desapi.caveuhc.Utils.Utils;
	
public class Main extends JavaPlugin {
		
  public Utils utils;
  public static int chestspawn = 15;
  public static Main main;
  public static String pr = "§eCaveUHC §8● ";
  public boolean newgame = false;
  public FileManager fm;
  public LocationManager lm;
  public Countdown cd;
  public static World Map;
  
  public Gamestate state;
  
  public int lobby = 20;
  public int ingame = 1800;
  public int min = 2;
  public int max = 6;
  public int sz = 180;
  public int deathmatch = 300;
  public int before = 60;
  public int restart = 15;
  public int grace = 3;
  public int dmstart = 3;
  
  //HashMaps
  public HashMap<String, BukkitTask> countdown = new HashMap<>();
  public HashMap<String, BukkitTask> tasks = new HashMap<>();
  public HashMap<Player, Player> lastdmg = new HashMap<>();
  public HashMap<Player, Integer> killsRound = new HashMap<>();
  
  //ArrayLists
  public ArrayList<Player> alive;
  public ArrayList<Player> schutz;
  public ArrayList<String> schutz_zeit_join = new ArrayList<>();
  
  public void onEnable()
  {
    main = this;
    this.lm = new LocationManager();
    this.lastdmg = new HashMap<Player, Player>();
    this.state = Gamestate.Lobby;
    this.alive = new ArrayList<Player>();
    this.schutz = new ArrayList<Player>();
    this.fm = new FileManager();

    this.cd = new Countdown();
    this.utils = new Utils();
    this.fm.saveCfg();
    this.fm.register();
    this.lm.saveCfg();
    
    //Events
    Bukkit.getPluginManager().registerEvents(new Events(), this);
    Bukkit.getPluginManager().registerEvents(new DeathListener(), this);
    Bukkit.getPluginManager().registerEvents(new Teleporter(), this);
    
    //Commands
    getCommand("setLobby").setExecutor(new setLobby());
    getCommand("start").setExecutor(new Start());
    getCommand("setSpawn").setExecutor(new setSpawn());
    getCommand("setDeathMatch").setExecutor(new setDeathMatch());
  
  }
  
	public static Main getInstance(){
		return main;
	}

	
	@Override
	public void onDisable() {
		for(Player all : Bukkit.getOnlinePlayers()){
			all.kickPlayer("§6Spielneustart...");
		}
	}
  
 
  @SuppressWarnings("deprecation")
		public void Win()
	  		{
		    if ((this.state == Gamestate.Ingame) || (this.state == Gamestate.DeathMatch)) {
		    	if (Bukkit.getOnlinePlayers().size() == 0)
		    		{
		    	  Countdown.startRestartCD();
		    		}
		    	 else if (this.alive.size() == 1)
		        {
		          Player winner = (Player)this.alive.get(0);
		          for (Player all : Bukkit.getOnlinePlayers())
		          {
		            main.utils.clearPlayer(all);
		            all.sendTitle(winner.getDisplayName(), "§ehat gewonnen§7!");
		            all.sendMessage(Main.pr + "§a" + winner.getDisplayName() + " §ehat das Spiel gewonnen!");
		          }
		          Countdown.startRestartCD();
		        }
		    }
	  	}
	}

