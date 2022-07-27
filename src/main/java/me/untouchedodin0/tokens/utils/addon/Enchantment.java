package me.untouchedodin0.tokens.utils.addon;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.util.List;

public abstract class Enchantment implements Listener {

    public Enchantment(String name, int levels, List<Material> enchantables) {
        this.onInit();
        // register the listener
    }

    public abstract void onInit();

    @EventHandler
    public abstract void onChat(AsyncPlayerChatEvent event);

    @EventHandler
    public abstract void onBreak(BlockBreakEvent event);

}