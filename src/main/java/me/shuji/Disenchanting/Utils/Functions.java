package me.shuji.Disenchanting.Utils;
import org.bukkit.enchantments.Enchantment;


public class Functions
{

	public static String GetEnchantmentName(Enchantment ench) {

		if(ench.equals(Enchantment.POWER)) return "Power";
		if(ench.equals(Enchantment.FLAME)) return "Flame";
		if(ench.equals(Enchantment.INFINITY)) return "Infinity";
		if(ench.equals(Enchantment.PUNCH)) return "Punch";
		if(ench.equals(Enchantment.BINDING_CURSE)) return "Curse of Binding";
		if(ench.equals(Enchantment.CHANNELING)) return "Channeling";
		if(ench.equals(Enchantment.SHARPNESS)) return "Sharpness";
		if(ench.equals(Enchantment.BANE_OF_ARTHROPODS)) return "Bane of Arthropods";
		if(ench.equals(Enchantment.SMITE)) return "Smite";
		if(ench.equals(Enchantment.DEPTH_STRIDER)) return "Depth Strider";
		if(ench.equals(Enchantment.EFFICIENCY)) return "Efficiency";
		if(ench.equals(Enchantment.PROTECTION)) return "Protection";
		if(ench.equals(Enchantment.FIRE_PROTECTION)) return "Fire Protection";
		if(ench.equals(Enchantment.FEATHER_FALLING)) return "Feather Falling";
		if(ench.equals(Enchantment.BLAST_PROTECTION)) return "Blast Protection";
		if(ench.equals(Enchantment.PROJECTILE_PROTECTION)) return "Projectile Protection";
		if(ench.equals(Enchantment.RESPIRATION)) return "Respiration";
		if(ench.equals(Enchantment.AQUA_AFFINITY)) return "Aqua Affinity";
		if(ench.equals(Enchantment.THORNS )) return "Thorns";
		if(ench.equals(Enchantment.FIRE_ASPECT)) return "Fire Aspect";
		if(ench.equals(Enchantment.LOOTING)) return "Looting";
		if(ench.equals(Enchantment.SILK_TOUCH )) return "Silk Touch";
		if(ench.equals(Enchantment.UNBREAKING)) return "Unbreaking";
		if(ench.equals(Enchantment.FORTUNE)) return "Fortune";
		if(ench.equals(Enchantment.LUCK_OF_THE_SEA)) return "Luck";
		if(ench.equals(Enchantment.LURE )) return "Lure";
		if(ench.equals(Enchantment.LOYALTY)) return "Loyalty";
		if(ench.equals(Enchantment.MULTISHOT)) return "Multishot";
		if(ench.equals(Enchantment.MENDING)) return "Mending";
		if(ench.equals(Enchantment.PIERCING)) return "Piercing";
		if(ench.equals(Enchantment.QUICK_CHARGE)) return "Quick Charge";
		if(ench.equals(Enchantment.RIPTIDE)) return "Riptide";
		if(ench.equals(Enchantment.SWEEPING_EDGE)) return "Sweeping Edge";
		if(ench.equals(Enchantment.VANISHING_CURSE)) return "Curse of Vanishing";
		if(ench.equals(Enchantment.FROST_WALKER)) return "Frost Walker";
		if(ench.equals(Enchantment.IMPALING)) return "Impaling";
		if(ench.equals(Enchantment.KNOCKBACK)) return "Knockback";
		if(ench.equals(Enchantment.SOUL_SPEED)) return "Soul Speed";
		return "";
	}

}
