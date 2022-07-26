package me.untouchedodin0.tokens.utils.addon;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public abstract class Enchantment implements Listener {

    public Enchantment(String name, int maxLevel) {
        this.onLoad();
    }

    public abstract void onLoad();

    @EventHandler
    public abstract void onBlockBreak(BlockBreakEvent event);
}
