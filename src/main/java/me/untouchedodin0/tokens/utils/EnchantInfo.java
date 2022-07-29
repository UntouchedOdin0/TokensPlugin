package me.untouchedodin0.tokens.utils;

import me.untouchedodin0.tokens.utils.addon.Enchantment;

/**
 * Represents a CustomEnchant and level
 * @author Redempt
 */
public class EnchantInfo {

    private Enchantment ench;
    private int level;

    /**
     * Constructs an EnchantInfo from a CustomEnchant and level
     * @param ench The CustomEnchant
     * @param level The level
     */
    public EnchantInfo(Enchantment ench, int level) {
        this.ench = ench;
        this.level = level;
    }

    /**
     * @return The level stored in this EnchantInfo
     */
    public int getLevel() {
        return level;
    }

    /**
     * @return The CustomEnchant stored in this EnchantInfo
     */
    public Enchantment getEnchant() {
        return ench;
    }
}