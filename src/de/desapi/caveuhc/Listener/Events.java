package de.desapi.caveuhc.Listener;

import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.TNTPrimed;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.CreatureSpawnEvent.SpawnReason;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.player.PlayerAchievementAwardedEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.weather.WeatherChangeEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;

import de.desapi.caveuhc.CountDown.Countdown;
import de.desapi.caveuhc.CountDown.Gamestate;
import de.desapi.caveuhc.Main.Main;
import de.desapi.caveuhc.Utils.ItemBuilder;
import net.md_5.bungee.api.ChatColor;

public class Events implements Listener {
	
	public static World Map;
	public Random rnd = new Random();
	
  @EventHandler
  public void onJoin(PlayerJoinEvent e)
  {
	  for (Player p : Bukkit.getOnlinePlayers()) {
	    p.playSound(p.getLocation(), Sound.CHICKEN_EGG_POP, 1.0F, 1.0F);
	    p.teleport(Main.main.lm.getLocation("lobby"));
		Main.main.utils.clearPlayer(p); 
        setScoreBoard(p);
	      for (Player all : Bukkit.getOnlinePlayers()) {
	          all.showPlayer(p);
	        }
             
    if (Main.main.state == Gamestate.Lobby)
    {
      if (!Main.main.alive.contains(p)) {
        Main.main.alive.add(p);
      }
      e.setJoinMessage(Main.pr + ChatColor.GOLD + p.getDisplayName() + " §7ist dem §bSpiel §7beigetreten.");
  	  p.playSound(p.getLocation(), Sound.CHICKEN_EGG_POP, 1.0F, 1.0F);
      p.teleport(Main.main.lm.getLocation("lobby"));
      Main.main.utils.clearPlayer(p);
      if (Bukkit.getOnlinePlayers().size() == Main.main.min) {
      Countdown.startLobbyCD();
      }
    }
    else
    {
      p.kickPlayer(Main.pr + "Das Spiel hat schon begonnen!");
      	}
    	}
	  }
  
  public static void setScoreBoard(Player p) {
	Scoreboard board2 = Bukkit.getScoreboardManager().getNewScoreboard();
		Objective o = board2.registerNewObjective("Ven", "trox");
		    o.setDisplaySlot(DisplaySlot.SIDEBAR);
		  	o.setDisplayName("§b§lCaveUHC");
		  	o.getScore("§a ").setScore(9);
		  	o.getScore("§7Teams§8:").setScore(8);
		  	o.getScore("§cVerboten").setScore(7);
		  	o.getScore("§b ").setScore(6);
		  	o.getScore("§7Map§8:").setScore(5);
		  	o.getScore("§dBasic").setScore(4);
		  	o.getScore("§c ").setScore(3);
		  	o.getScore("§7Kills§8:").setScore(2);
		  	o.getScore("§a" + Main.getInstance().killsRound.get(p)).setScore(1);
	  	
		  	p.setScoreboard(board2);
	  }
  	

  
	@EventHandler
	public void onBreakBlock(BlockBreakEvent e) {
		Player p = e.getPlayer();
		if (Main.main.state == Gamestate.Ingame)
		{
		if (!(e.getBlock().getType() == Material.DIAMOND_ORE || e.getBlock().getType() == Material.IRON_ORE
				|| e.getBlock().getType() == Material.LOG || e.getBlock().getType() == Material.EMERALD_ORE
				|| e.getBlock().getType() == Material.LEAVES || e.getBlock().getType() == Material.WEB
				|| e.getBlock().getType() == Material.STONE || e.getBlock().getType() == Material.COBBLESTONE
				|| e.getBlock().getType() == Material.GRAVEL
				|| e.getBlock().getType() == Material.GOLD_ORE || e.getBlock().getType() == Material.LAPIS_ORE
				|| e.getBlock().getType() == Material.REDSTONE_ORE || e.getBlock().getType() == Material.COAL_ORE
				|| e.getBlock().getType() == Material.GLOWING_REDSTONE_ORE
				|| e.getBlock().getType() == Material.ENCHANTMENT_TABLE || e.getBlock().getType() == Material.ANVIL
				|| e.getBlock().getType() == Material.WORKBENCH || e.getBlock().getType() == Material.SKULL)) {
			e.setCancelled(true);
		}

		Location loc = e.getBlock().getLocation();
		World world = e.getBlock().getWorld();
		
		if (e.getBlock().getType() == Material.GOLD_ORE) {
			e.getBlock().setType(Material.AIR);
			p.getInventory().addItem(new ItemStack(Material.GOLD_INGOT, 2));
		}

		if (e.getBlock().getType() == Material.IRON_ORE) {
			e.getBlock().setType(Material.AIR);
			p.getInventory().addItem(new ItemStack(Material.IRON_INGOT, 2));
		}

		if (e.getBlock().getType() == Material.REDSTONE_ORE
				|| e.getBlock().getType() == Material.GLOWING_REDSTONE_ORE) {
			e.getBlock().setType(Material.AIR);
			p.getInventory().addItem(new ItemStack(Material.APPLE, 1));
		}

		if (e.getBlock().getType() == Material.EMERALD_ORE) {
			e.getBlock().setType(Material.AIR);
			p.getInventory().addItem(new ItemStack(Material.EXP_BOTTLE, 1 + rnd.nextInt(7)));
		}

		if (e.getBlock().getType() == Material.LAPIS_ORE) {
			e.getBlock().setType(Material.AIR);
			p.getInventory().addItem(new ItemStack(Material.INK_SACK, 2, (short) 4));
		}

		if (e.getBlock().getType() == Material.DIAMOND_ORE) {
			e.getBlock().setType(Material.AIR);
			p.getInventory().addItem(new ItemStack(Material.DIAMOND, 2));
		}
		if (e.getBlock().getType() == Material.STONE) {
			p.getInventory().addItem(new ItemStack(Material.COBBLESTONE, 1));
		}
		if (e.getBlock().getType() == Material.COBBLESTONE) {
			p.getInventory().addItem(new ItemStack(Material.COBBLESTONE, 1));
		}
		if (e.getBlock().getType() == Material.GRAVEL) {
			p.getInventory().addItem(new ItemStack(Material.GRAVEL, 1));
		}
		if (e.getBlock().getType() == Material.WEB) {
			p.getInventory().addItem(new ItemStack(Material.STRING, 1));
		}
		if (e.getBlock().getType() == Material.LEAVES) {
			p.getInventory().addItem(new ItemStack(Material.APPLE, 1));
		}
		if (e.getBlock().getType() == Material.COAL_ORE) {
			e.getBlock().setType(Material.AIR);
			int randomInt = 1 + rnd.nextInt(99);

			if (randomInt < 25) {
				p.getInventory().addItem(new ItemStack(Material.PAPER, 4 + rnd.nextInt(6)));
			}

			if (randomInt > 25 && randomInt < 60) {
				p.getInventory().addItem(new ItemStack(Material.LEATHER, 2 + rnd.nextInt(1)));
			}

			if (randomInt > 60 && randomInt < 75) {
				p.getInventory().addItem(new ItemStack(Material.FEATHER, 1 + rnd.nextInt(1)));
			}

			if (randomInt > 75 && randomInt < 100) {
				p.getInventory().addItem(new ItemStack(Material.STRING, 1 + rnd.nextInt(1)));
			}
		}
		
		if(e.getBlock().getType() == Material.SKULL){
			e.getBlock().setType(Material.AIR);
			world.dropItem(loc, new ItemBuilder(Material.GOLDEN_APPLE, 1).setDisplayName("§6Golden Head").create());
		}
		}

	}
	//Achievements
	@EventHandler
	public void onAchievment(PlayerAchievementAwardedEvent e) {
		e.setCancelled(true);
	}
	
	//Spawn Mobs
	@EventHandler
	public void onSpawn(CreatureSpawnEvent e) {
		if (!(e.getSpawnReason() == SpawnReason.CUSTOM)) {
			e.setCancelled(true);
		}
	}
 
  	//Login Listener
  	@EventHandler
  	public void onLogin(PlayerLoginEvent e)
  	{
  		if (Main.main.state != Gamestate.Lobby)
  		{
  			e.disallow(PlayerLoginEvent.Result.KICK_FULL, Main.pr + "§cDas Spiel hat schon begonnen!");
  			return;
  		}
  		if (Main.main.alive.size() >= Main.main.max) {
  			e.disallow(PlayerLoginEvent.Result.KICK_OTHER, Main.pr + "§cDas Spiel ist voll!");
  		}
  	}
  
  	//Leave Event
  	@EventHandler
  	public void onLeave(PlayerQuitEvent e)
  	{
  		Player p = e.getPlayer();
  		if ((Main.main.state == Gamestate.Lobby) && 
  				(Main.main.alive.contains(p))) {
  				Main.main.alive.remove(p);
  		}
  		if ((Main.main.state == Gamestate.Ingame) || (Main.main.state == Gamestate.Grace))
  		{
  			if (Main.main.alive.contains(p))
  			{
  				Main.main.alive.remove(p);
  				p.setHealth(0.0D);
  				e.setQuitMessage(Main.pr + "§c" + p.getDisplayName() + " §7ist gestorben");
  			}
  			else
  			{
  				e.setQuitMessage(null);
  			}
  		}
  		else {
      e.setQuitMessage("§8» §a" + p.getDisplayName() + " §7hat das Spiel verlassen");
  		}
  	}
  
  
  	//FoodLose
  	@EventHandler
  	public void onLoseFeed(FoodLevelChangeEvent e)
  	{
  		if ((Main.main.state == Gamestate.Ingame) || (Main.main.state == Gamestate.DeathMatch)) {
  			e.setCancelled(true);
  	}
  	}
  
  	//Damage 
  	@EventHandler
  	public void onDamage(EntityDamageEvent e)
  	{
  		if ((Main.main.state == Gamestate.Lobby) || (Main.main.state == Gamestate.Grace) || 
      (Main.main.state == Gamestate.Restarting))
  		{
  			e.setCancelled(true);
  			if (e.getCause() == EntityDamageEvent.DamageCause.FALL) {
  				e.setCancelled(true);
  			}
  		}
  		else
  		{
  			e.setCancelled(false);
  		}
  	}
  
  
  	@EventHandler
  	public void onEntDmgByEnt(EntityDamageByEntityEvent e)
  	{
  		if ((Main.main.state == Gamestate.Ingame) || (Main.main.state == Gamestate.DeathMatch)) {
  			e.setCancelled(false);
  		} else {
  			e.setCancelled(true);
  		}
  	}

  	//Break Event
  	@EventHandler
  	public void onBreak(BlockBreakEvent e)
  	{
	  if (Main.main.state == Gamestate.Lobby)
	    {
		  e.setCancelled(true);
	    }
  
	  else if (Main.main.state == Gamestate.Ingame) {
	    e.setCancelled(false);
	  }
	  else if (Main.main.state == Gamestate.DeathMatch) {
		    e.setCancelled(true);
	  	}	
  	}

  	//PlaceEvent
  	@EventHandler
  		public void onPlace(BlockPlaceEvent e) {
	  
  		if (Main.main.state == Gamestate.Lobby)
  		{
  			e.setCancelled(true);
  		}

  		else if (Main.main.state == Gamestate.Ingame) {
  			e.setCancelled(false);
  		}
  		else if (Main.main.state == Gamestate.DeathMatch) {
  			e.setCancelled(true);
  		}
  	}
  	
  	//Drop Event
  	@EventHandler
  	public void onDrop(PlayerDropItemEvent e)
	  {
	    if (!Main.main.alive.contains(e.getPlayer()))
	    {
	      e.setCancelled(true);
	      return;
	    }
	    if (Main.main.state != Gamestate.Ingame) {
	      e.setCancelled(false);
	    }
		  else if (Main.main.state != Gamestate.DeathMatch) {
			    e.setCancelled(false);
		  }
	  }
  	
  	
  	
    //Wetter
	@EventHandler
	public void on(WeatherChangeEvent e) {
		e.setCancelled(true);
	}
	
  
		//Pickup Event
		@EventHandler
		public void onPickup(PlayerPickupItemEvent e)
  	{
	  if (Main.main.state == Gamestate.Lobby || !Main.main.alive.contains(e.getPlayer()))
	    {
		  e.setCancelled(true);
	    }

	  else if (Main.main.state != Gamestate.Ingame) {
	    e.setCancelled(false);
	  }
	  else if (Main.main.state != Gamestate.DeathMatch) {
		    e.setCancelled(false);
	  }
  	}

  	
  	//TNT PlaceEvent
  	@EventHandler
  	public void TNTPlace(BlockPlaceEvent e)
	  {
	    if (e.getBlock().getType() == Material.TNT)
	    {
	      e.getBlock().setType(Material.AIR);
	      Entity tnt = e.getPlayer().getWorld().spawn(e.getBlock().getLocation(), TNTPrimed.class);
	      ((TNTPrimed)tnt).setFuseTicks(20);
	    }
	  }
  	
  
  	//TNT Explosion
  	@EventHandler
  	public void onEntityExplode(EntityExplodeEvent e) {
  		e.blockList().clear();
  		}
	}



