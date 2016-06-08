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
				int amount = Main.getAmountOf(Material.TNT, inven);
				float explosion = (float) amount;
				if (amount < Main.getMainClass().getConfig().getInt("Explosion Limit")) {
					b.getLocation().getWorld().createExplosion(b.getLocation(), explosion);
				} else {
					float maxexplosion = (float) Main.getMainClass().getConfig().getInt("Explosion Limit");
					b.getLocation().getWorld().createExplosion(b.getLocation(), maxexplosion);
				}
			}
		}

	}
}
