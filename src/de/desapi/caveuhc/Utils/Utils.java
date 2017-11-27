package de.desapi.caveuhc.Utils;

import java.util.ArrayList;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;

public class Utils
{
  public ArrayList<ItemStack> loot;
  
  public ItemStack create(Material mat, int amount)
  {
    return new ItemStack(mat, amount);
  }
  
  public ItemStack createCustom(Material mat, int amount, short id, String display)
  {
    ItemStack it = new ItemStack(mat, amount, id);
    ItemMeta meta = it.getItemMeta();
    meta.setDisplayName(display);
    it.setItemMeta(meta);
    return it;
  }
  
  
  public void clearPlayer(Player p)
  {
    p.setHealth(20.0D);
    p.setFoodLevel(20);
    p.setLevel(0);
    p.setExp(0.0F);
    p.getInventory().clear();
    p.getInventory().setArmorContents(null);
    for (PotionEffect effect : p.getActivePotionEffects()) {
      p.removePotionEffect(effect.getType());
    }
  }
}
