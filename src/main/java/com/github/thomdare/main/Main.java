package com.github.thomdare.main;

import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
	private FileConfiguration config = getConfig();
	public static Main main;

	public void onEnable() {
		config.addDefault("Explosion Limit", 100);
	}

	public void onDisable() {

	}

	public void onLoad() {
		main = this;
	}

	public static Main getMainClass() {
		return main;
	}

	public static int getAmountOf(Material item, Inventory inventory) {
		int i = 0;
		for (ItemStack is : inventory.getContents()) {
			if (is != null)
				if (is.getType() == item) {
					i += is.getAmount();
				}
		}
		return i;
	}

}
