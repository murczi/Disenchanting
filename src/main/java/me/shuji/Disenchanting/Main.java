package me.shuji.Disenchanting;

import me.shuji.Disenchanting.Handlers.DisenchantHandler;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.inventory.PrepareAnvilEvent;
import org.bukkit.inventory.AnvilInventory;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.EnchantmentStorageMeta;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;


public final class Main extends JavaPlugin implements Listener {

	FileConfiguration config = getConfig();
	public static Logger console;
	private static DisenchantHandler dh;

	@Override
	public void onEnable() {
		setConfig();
		console = getLogger();
		if (config.getBoolean("enabled")) {
			onDisable();
		}

		getServer().getPluginManager().registerEvents(this, this);
	}

	@EventHandler(priority = EventPriority.HIGHEST)
	public void onPrepareAnvil(PrepareAnvilEvent e) {
		dh = new DisenchantHandler(e, e.getInventory().getFirstItem(), e.getInventory().getSecondItem());
	}

	@EventHandler(priority = EventPriority.HIGHEST)
	public void onInventoryClick(InventoryClickEvent e) {
		if (!e.isCancelled()) {
			HumanEntity ent = e.getWhoClicked();
			if (ent == null)
				return;

			Player player = (Player) ent;
			Inventory inv = e.getInventory();

			if (inv instanceof AnvilInventory) {
				AnvilInventory ainv = (AnvilInventory) inv;
				InventoryView view = e.getView();
				ItemStack currentItem = e.getCurrentItem();
				if (e.getSlotType() == InventoryType.SlotType.RESULT && currentItem != null && currentItem.getType() != Material.AIR) {
					if (inv.getContents()[1] != null) {
						if (inv.getContents()[1].getType() == Material.BOOK) {
							ItemStack firstItem = inv.getContents()[0];
							int amount = inv.getContents()[1].getAmount();
							ItemStack bookItem = inv.getContents()[1];
							view.setCursor(ainv.getResult()); //give player the disenchanted book
							bookItem.setAmount(amount - 1); //remove one book

							//region Remove enchant
							if (dh.removeLastEnchant.containsKey(ent.getUniqueId())) {
								if (dh.removeLastEnchant.get(ent.getUniqueId()) != null) {
									if (firstItem.getType() == Material.ENCHANTED_BOOK) {
										EnchantmentStorageMeta firstMeta = (EnchantmentStorageMeta) firstItem.getItemMeta();
										Enchantment enchant = dh.removeLastEnchant.get(ent.getUniqueId());
										firstMeta.removeStoredEnchant(enchant);
										firstItem.setItemMeta(firstMeta);
									} else {
										Enchantment removeEnch = dh.removeLastEnchant.get(ent.getUniqueId());
										firstItem.removeEnchantment(removeEnch);
									}
									for (HumanEntity he : e.getClickedInventory().getViewers())
										dh.removeLastEnchant.put(he.getUniqueId(), null);
								}
							}
							//endregion

							inv.setContents(new ItemStack[]{firstItem, bookItem, null});
						}
					}
				}
			}
		}
	}

	private void setConfig() {
		config.addDefault("enabled", true);
		config.addDefault("shouldCostXp", false);
		config.addDefault("xpCostPerLvl", 2);
		config.options().copyDefaults(true);
		saveConfig();
	}

}
