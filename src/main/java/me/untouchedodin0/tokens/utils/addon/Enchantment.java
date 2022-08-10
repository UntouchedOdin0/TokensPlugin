package me.untouchedodin0.tokens.utils.addon;

import me.untouchedodin0.tokens.Tokens;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import redempt.redlib.enchants.EnchantRegistry;

import java.util.ArrayList;
import java.util.List;

import static me.untouchedodin0.tokens.utils.Utils.fromRomanNumerals;
import static me.untouchedodin0.tokens.utils.Utils.toRomanNumerals;

public abstract class Enchantment implements Listener {

    private final String name;
    private final int maxLevel;
    private List<Material> applicableMaterials;
    private EnchantRegistry registry;

    public Enchantment(String name, int maxLevel, List<Material> applicable) {
        this.onInit();

        this.name = name;
        this.maxLevel = maxLevel;
        this.applicableMaterials = applicable;
        this.registry = Tokens.getTokens().getEnchantRegistry();

        // register the listener

        register(registry);
    }

    public void register(EnchantRegistry registry) {
        this.registry = registry;
    }

    public abstract void onInit();

    @EventHandler
    public abstract void onChat(AsyncPlayerChatEvent event);

    @EventHandler
    public abstract void onBreak(BlockBreakEvent event);


    public String getName() {
        return name;
    }

    public int getMaxLevel() {
        return maxLevel;
    }

    public int getLevel(ItemStack itemStack) {
        if (itemStack == null || !itemStack.hasItemMeta() || !itemStack.getItemMeta().hasLore()) {
            return 0;
        }

        List<String> lore = itemStack.getItemMeta().getLore();
        String name = getName();
        for (int i = lore.size() -1; i >= 0; i--) {
            String line = lore.get(i);
            if (line.startsWith(name)) {
                if (line.length() == name.length()) {
                    return 1;
                }
                return fromRomanNumerals(line.substring(name.length() + 1));
            }
        }
        return 0;
    }

    boolean appliesTo(Material material) {
        return applicableMaterials.stream().anyMatch(material1 -> material1.equals(material));
    }

    public ItemStack apply(ItemStack itemStack, int level) {
        if (itemStack == null) {
            return null;
        }
        if (level == 0) {
            remove(itemStack);
        }

        ItemMeta itemMeta = itemStack.getItemMeta();
        List<String> lore = itemMeta != null ? itemMeta.getLore() : null;
        lore = lore == null ? new ArrayList<>() : lore;
        int where = -1;
        boolean replace = false;
        String name = getName();

        for (int i = lore.size() - 1; i >= 0; i--) {
            String line = lore.get(i);
            if (registry.fromLoreLine(line) != null) {
                where = i + 1;
            }

            if (line.startsWith(name)) {
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

        if (itemMeta != null) {
            itemMeta.setLore(lore);
        }
        itemStack.setItemMeta(itemMeta);
        return itemStack;
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
        return getName() + " " + toRomanNumerals(level);
    }

    public ItemStack remove(ItemStack item) {
        if (item == null || !item.hasItemMeta() || !item.getItemMeta().hasLore()) {
            return item;
        }

        ItemMeta meta = item.getItemMeta();
        List<String> lore = meta.getLore();
        if (lore != null) {
            lore.removeIf(s -> s.startsWith(getName()));
        }
        meta.setLore(lore);
        item.setItemMeta(meta);
        return item;
    }
}