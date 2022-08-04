package me.untouchedodin0.tokens.utils.addon;

import me.untouchedodin0.tokens.Tokens;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import redempt.redlib.enchants.EnchantRegistry;

import java.util.List;

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

    boolean appliesTo(Material material) {
        return applicableMaterials.stream().anyMatch(material1 -> material1.equals(material));
    }
}