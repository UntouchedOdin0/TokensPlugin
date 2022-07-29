package me.untouchedodin0.tokens.utils.addon;

import me.untouchedodin0.tokens.utils.EnchantListener;
import me.untouchedodin0.tokens.utils.EnchantRegistry;
import me.untouchedodin0.tokens.utils.EnchantTrigger;
import me.untouchedodin0.tokens.utils.Utils;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.*;

public abstract class Enchantment implements Listener {

    private final String name;
    private final int maxLevel;
    private List<Material> applicableMaterials;
    private EnchantRegistry registry;
    private Map<EnchantTrigger<?>, EnchantListener<?>> triggers = new HashMap<>();

    public Enchantment(String name, int maxLevel, List<Material> applicable) {
        this.onInit();
        this.name = name;
        this.maxLevel = maxLevel;
        this.applicableMaterials = applicable;

        // register the listener
    }

    public void register(EnchantRegistry registry) {
        this.registry = registry;
    }

    public abstract void onInit();

    @EventHandler
    public abstract void onChat(AsyncPlayerChatEvent event);

    @EventHandler
    public abstract void onBreak(BlockBreakEvent event);

    public ItemStack apply(ItemStack itemStack, int level) {
        if (itemStack == null || level == 0) return null;
        ItemMeta meta = itemStack.getItemMeta();
        List<String> lore;
        if (meta != null) {
            lore = meta.getLore();
            lore = lore == null ? new ArrayList<>() : lore;
            int where = -1;
            boolean replace = false;
            String displayName = getName();
            for (int i = lore.size() - 1; i >= 0; i--) {
                String line = lore.get(i);
                if (line.startsWith(displayName)) {
                    replace = true;
                    where = i;
                    break;
                }
            }

            if (where == -1) {
                where = lore.size();
            }
            if (replace) {
                lore.set(where, getLore(level));
            } else {
                lore.add(where, getLore(level));
            }
            meta.setLore(lore);
        }
        itemStack.setItemMeta(meta);
        return itemStack;
    }

    public String getName() {
        return name;
    }

    /**
     * @return The display name of this CustomEnchant, generated using the namer function of its EnchantRegistry
     */
    public String getDisplayName() {
        return registry.getDisplayName(this);
    }

    /**
     * @return The ID of this CustomEnchant, the same as a lowercase version of the name that has spaces replaced with underscores
     */
    public final String getId() {
        return name.toLowerCase().replace(" ", "_");
    }

    /**
     * Gets the lore that will be added to an item if this CustomEnchant is applied at the given level
     * @param level The level to be specified in the lore
     * @return The line of lore
     */
    public String getLore(int level) {
        if (level == 1 && maxLevel == 1) {
            return getName();
        }
        return getName() + " " + Utils.toRomanNumerals(level);
    }

    /**
     * Gets the level of this CustomEnchant on an item
     * @param itemStack The item to check the level on
     * @return The level on the item, 0 if it is absent or if the itemStack is null
     */
    public int getLevel(ItemStack itemStack) {
        if (itemStack == null || !itemStack.hasItemMeta() || !Objects.requireNonNull(itemStack.getItemMeta()).hasLore()) {
            return 0;
        }
        List<String> lore = itemStack.getItemMeta().getLore();
        String displayName = getName();
        for (int i = (lore != null ? lore.size() : 0) - 1; i >= 0; i--) {
            String line = lore.get(i);
            if (line.startsWith(displayName)) {
                if (line.length() == displayName.length()) {
                    return 1;
                }
                return Utils.fromRomanNumerals(line.substring(displayName.length() + 1));
            }
        }
        return 0;
    }

    public int getMaxLevel() {
        return maxLevel;
    }

    boolean appliesTo(Material material) {
        return applicableMaterials.stream().anyMatch(material1 -> material1.equals(material));
    }

    public final EnchantRegistry getRegistry() {
        return registry;
    }

    public final Map<EnchantTrigger<?>, EnchantListener<?>> getTriggers() {
        return triggers;
    }
}