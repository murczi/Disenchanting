package me.shuji.basePlugin;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;


public final class Main extends JavaPlugin implements Listener {

	FileConfiguration config = getConfig();
	Logger console = getLogger();

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
		config.options().copyDefaults(true);
		saveConfig();
	}

}
