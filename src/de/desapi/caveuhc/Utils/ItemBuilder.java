package de.desapi.caveuhc.Utils;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

public class ItemBuilder {

	ItemStack item;
	ItemMeta itemMeta;
	
	public ItemBuilder(Material material, int anzahl) {
		item = new ItemStack(material, anzahl);
	}
	
	public ItemStack create(){
		return item;
	}
	
	public ItemBuilder setDamageValue(int damagevalue){
		item.setDurability((short) damagevalue);
		return this;
	}
	
	public ItemBuilder setHeadName(String headName){
		SkullMeta skullMeta = (SkullMeta) item.getItemMeta();
		skullMeta.setOwner(headName);
		item.setItemMeta(skullMeta);
		return this;
	}
	
	public ItemBuilder addEnchantment(Enchantment e, Integer level){
		item.addUnsafeEnchantment(e, level);
		return this;
	}
	
	public ItemBuilder setUnbreakable(boolean unbreakable){
		itemMeta = item.getItemMeta();
		itemMeta.spigot().setUnbreakable(unbreakable);
		item.setItemMeta(itemMeta);
		return this;
	}
	
	public ItemBuilder setGlow(){
		itemMeta = item.getItemMeta();
		if(!(itemMeta.hasEnchants())){
			itemMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
			item.setItemMeta(itemMeta);
			item.addUnsafeEnchantment(Enchantment.ARROW_DAMAGE, 1);
		}
		return this;
	}
	
	public ItemBuilder hideFlags(){
		itemMeta = item.getItemMeta();
		itemMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		itemMeta.addItemFlags(ItemFlag.HIDE_DESTROYS);
		itemMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		itemMeta.addItemFlags(ItemFlag.HIDE_PLACED_ON);
		itemMeta.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS);
		itemMeta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
		item.setItemMeta(itemMeta);
		return this;
	}
	
	public ItemBuilder setLore(List<String> lore){
		
		if(lore.isEmpty()){
			return this;
		}
		
		itemMeta = item.getItemMeta();
		itemMeta.setLore(lore);
		item.setItemMeta(itemMeta);
		return this;
	}
	
	public ItemBuilder setDisplayName(String displayName){
		itemMeta = item.getItemMeta();
		itemMeta.setDisplayName(displayName);
		item.setItemMeta(itemMeta);
		return this;
	}

	
}