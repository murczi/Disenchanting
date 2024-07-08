package me.shuji.Disenchanting.Handlers;

import me.shuji.Disenchanting.Main;
import me.shuji.Disenchanting.Utils.Functions;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.HumanEntity;
import org.bukkit.event.inventory.PrepareAnvilEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.EnchantmentStorageMeta;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class DisenchantHandler {

	public Map<UUID, Enchantment> removeLastEnchant = new HashMap<>();
	public Map<UUID, Boolean> isModified = new HashMap<>();

	private ItemStack fI;

	public DisenchantHandler(PrepareAnvilEvent e, ItemStack fItem, ItemStack sItem) {
		if (e != null) {
			fI = fItem;
			if (fI != null && sItem != null)
				if (sItem.getType() == Material.BOOK) {
					e.getInventory().setRepairCost(0);
					if (fItem.getType() == Material.ENCHANTED_BOOK) HandleEnchantedBook(e);
					else HandleDisenchant(e);
				}
		}
	}

	public void HandleEnchantedBook(PrepareAnvilEvent e) {
		EnchantmentStorageMeta meta = (EnchantmentStorageMeta) fI.getItemMeta();
		Object[] enchs = meta.getStoredEnchants().keySet().toArray();
		if (enchs.length <= 1)
			return;
		Enchantment removeEnchant = (Enchantment) enchs[enchs.length - 1];
		for (Object i : enchs) {
			Enchantment ench = (Enchantment) i;
			if (e.getInventory().getRenameText().equalsIgnoreCase(Functions.GetEnchantmentName(ench)))
				removeEnchant = ench;
		}
		ItemStack resultBook = new ItemStack(Material.ENCHANTED_BOOK);
		EnchantmentStorageMeta resultMeta = (EnchantmentStorageMeta) resultBook.getItemMeta();
		if (Main.config.getBoolean("shouldCostXp")) {
			e.getInventory().setRepairCost(meta.getStoredEnchantLevel(removeEnchant) * Main.config.getInt("xpCostPerLvl"));
		}
		resultMeta.addStoredEnchant(removeEnchant, meta.getStoredEnchantLevel(removeEnchant), true);
		resultBook.setItemMeta(resultMeta);
		meta.removeStoredEnchant(removeEnchant);
		for (HumanEntity he : e.getViewers()) {
			removeLastEnchant.put(he.getUniqueId(), removeEnchant);
		}
		e.setResult(resultBook);
		for (HumanEntity he : e.getViewers())
			isModified.put(he.getUniqueId(), true);

	}

	public void HandleDisenchant(PrepareAnvilEvent e) {
		Object[] enchs = fI.getItemMeta().getEnchants().keySet().toArray();
		if (enchs.length == 0)
			return;
		Enchantment removeEnchant = (Enchantment) enchs[enchs.length - 1];

		ItemStack resultBook = new ItemStack(Material.ENCHANTED_BOOK);
		EnchantmentStorageMeta resultMeta = (EnchantmentStorageMeta) resultBook.getItemMeta();
		if (Main.config.getBoolean("shouldCostXp")) {
			e.getInventory().setRepairCost(fI.getItemMeta().getEnchantLevel(removeEnchant) * Main.config.getInt("xpCostPerLvl"));
		}
		resultMeta.addStoredEnchant(removeEnchant, fI.getItemMeta().getEnchantLevel(removeEnchant), true);
		for (HumanEntity he : e.getViewers()) {
			removeLastEnchant.put(he.getUniqueId(), removeEnchant);
		}
		resultBook.setItemMeta(resultMeta);
		e.setResult(resultBook);
		for (HumanEntity he : e.getViewers())
			isModified.put(he.getUniqueId(), true);
	}
}
