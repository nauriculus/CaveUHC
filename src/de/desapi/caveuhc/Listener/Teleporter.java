package de.desapi.caveuhc.Listener;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import de.desapi.caveuhc.Main.Main;

public class Teleporter implements Listener {
  
  @SuppressWarnings("deprecation")
  @EventHandler
  public void onClick(InventoryClickEvent e)
  {
    Player p = (Player)e.getWhoClicked();
    if ((e.getClickedInventory() != null) && (e.getCurrentItem() != null) && 
      (!Main.main.alive.contains(p)))
    {
      e.setCancelled(true);
      if (e.getClickedInventory().getTitle().equals("§aTeleporter"))
      {
        String playerName = e.getCurrentItem().getItemMeta().getDisplayName();
        if (Main.main.alive.contains(Bukkit.getPlayer(playerName)))
        {
          Player tar = Bukkit.getPlayer(playerName);
          p.teleport(tar);
          p.sendTitle("§7Du beobachtest nun §b" + tar.getName(), playerName);
        }
        else
        {
          p.sendMessage("§cDieser Spieler ist nicht mehr am leben!");
        }
      }
    }
  }
  
  @EventHandler
  public void onInteract(PlayerInteractEvent e)
  {
    Player p = e.getPlayer();
    if ((!Main.main.alive.contains(p)) && 
      (p.getItemInHand().getType() == Material.COMPASS))
    {
      int length = Main.main.alive.size() / 9 + 1;
      Inventory inv = Bukkit.createInventory(null, 9 * length, "§aTeleporter");
      for (Player alive : Main.main.alive)
      {
        ItemStack head = new ItemStack(Material.SKULL_ITEM, 1, (short)3);
        SkullMeta skullmeta = (SkullMeta)head.getItemMeta();
        skullmeta.setOwner(alive.getName());
        skullmeta.setDisplayName(alive.getName());
        head.setItemMeta(skullmeta);
        inv.addItem(new ItemStack[] { head });
      }
      p.openInventory(inv);
    }
  }
}
