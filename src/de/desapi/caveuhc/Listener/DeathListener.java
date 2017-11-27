package de.desapi.caveuhc.Listener;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Effect;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import de.desapi.caveuhc.Main.Main;
import net.minecraft.server.v1_8_R3.IChatBaseComponent;
import net.minecraft.server.v1_8_R3.PacketPlayOutChat;
import net.minecraft.server.v1_8_R3.PlayerConnection;

public class DeathListener implements Listener {
  
	
	@SuppressWarnings("deprecation")
	@EventHandler
	  public void onDeath(PlayerDeathEvent e)
	  {
	    Player p = e.getEntity();
	    if (Main.main.alive.contains(p))
	    {
	      Main.main.alive.remove(p);
	      if (Main.main.lastdmg.containsKey(p))
	      {
	    	Player killer = p.getKiller();
	        e.setDeathMessage(Main.pr + ChatColor.GREEN  + p.getDisplayName() + "§6wurde von " + ((Player)Main.main.lastdmg.get(p)).getDisplayName() + " §cgetötet!");
	        sendActionBar(p, "§7Du wurdest von §6" + killer.getName() + " §7getötet!");
			sendActionBar(killer, "§7Du hast §6" + killer.getName() + " §7getötet!");
	    	p.playSound(p.getLocation(), Sound.CHICKEN_EGG_POP, 1.0F, 1.0F);
	    	
	        Main.main.Win();
	        
	      } else {
	    	p.sendTitle("§4✖", "§cGESTORBEN");
	        e.setDeathMessage(Main.pr + ChatColor.GREEN + p.getDisplayName() + " §cist gestorben!");
	        Main.main.Win();
	      }
	      for (Player all : Bukkit.getOnlinePlayers()) {
	        all.hidePlayer(p);
				}
	      
	    	} else {
	      e.setDeathMessage(null);
	      Main.main.Win();
	    }
	  }
	  
	

	public static void sendActionBar(Player player, String message) {
		PlayerConnection connect = ((CraftPlayer) player).getHandle().playerConnection;

		IChatBaseComponent chat = IChatBaseComponent.ChatSerializer.a("{\"text\": \"" + message + "\"}");
		PacketPlayOutChat packet = new PacketPlayOutChat(chat, (byte) 2);

		connect.sendPacket(packet);
	}
  
  
  @EventHandler
  public void onRespawn(PlayerRespawnEvent e)
  {
    Player p = e.getPlayer();
	p.setGameMode(GameMode.SPECTATOR);
	p.playEffect(p.getLocation(), Effect.PORTAL, 1);
    ItemStack compass = new ItemStack(Material.COMPASS, 1);
    ItemMeta compassmeta = compass.getItemMeta();
    compassmeta.setDisplayName("§aTeleporter");
    compass.setItemMeta(compassmeta);
    p.getInventory().addItem(new ItemStack[] { compass });
	p.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 1, 5));
	p.setHealth(20.0D);
	p.setGameMode(GameMode.ADVENTURE);
	p.setAllowFlight(true);
	p.setFlying(true);
	p.setLevel(0);
	p.setExp(0);
  }
}
