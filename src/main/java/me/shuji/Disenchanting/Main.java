package me.shuji.Disenchanting;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Map;
import java.util.logging.Logger;


public final class Main extends JavaPlugin implements Listener {

	FileConfiguration config = getConfig();
	Logger console = getLogger();
	ItemStack firstItem = null;
	Map.Entry<Enchantment, Integer> enchToMove = null;

	@Override
	public void onEnable() {
		setConfig();

		if (config.getBoolean("enabled")) {
			onDisable();
		}

		getServer().getPluginManager().registerEvents(this, this);
	}

	private void setConfig() {
		config.addDefault("enabled", true);
		config.addDefault("shouldCostXp", false);
		config.addDefault("xpCostPerLvl", 2);
		config.options().copyDefaults(true);
		saveConfig();
	}

}
