package me.untouchedodin0.tokens.enchantment;

import me.untouchedodin0.tokens.utils.addon.Enchantment;
import org.bukkit.Bukkit;
import org.bukkit.event.block.BlockBreakEvent;

public class Explosive extends Enchantment {

    public Explosive() {
        super("Explosive", 5);
    }

    @Override
    public void onLoad() {
        Bukkit.getLogger().info("oh hey, I've been loaded lol!");
    }

    @Override
    public void onBlockBreak(BlockBreakEvent event) {

    }
}
