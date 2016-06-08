package com.github.thomdare.scratternerf;

import java.util.List;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;

import com.github.thomdare.main.Main;

public class ScatterNerf implements Listener {
	public void onEntityExplode(EntityExplodeEvent event) {

		List<Block> destroyed = event.blockList();
		for (Block b : destroyed) {
			if (b.getType() == Material.DISPENSER) {
				InventoryHolder inv = ((InventoryHolder) b.getState());
				Inventory inven = inv.getInventory();
				int tntamount = Main.getAmountOf(Material.TNT, inven) * Main.getMainClass().getConfig().getInt("TNT Explosivness");
				int amount = 0;
				for (String ammo : Main.getMainClass().getAmmoTypes()) {
					Main.getMainClass();
					amount = amount + Main.getAmountOf(Material.getMaterial(ammo), inven);
				}
				boolean setFire = false;
				if (Main.getAmountOf(Material.FIREBALL, inven) > 0 && Main.getMainClass().getConfig().getBoolean("Fire Charges Cause Fire") == true|| Main.getMainClass().getConfig().getBoolean("Explosions Cause Fire") == true) {
					setFire = true;
				}
				float explosion = (float) amount + tntamount;
				if (amount < Main.getMainClass().getConfig().getInt("Explosion Limit")) {
					b.getLocation().getWorld().createExplosion(b.getLocation(), explosion, setFire);
				} else {
					float maxexplosion = (float) Main.getMainClass().getConfig().getInt("Explosion Limit");
					b.getLocation().getWorld().createExplosion(b.getLocation(), maxexplosion, setFire);
				}
			}
		}

	}
}
