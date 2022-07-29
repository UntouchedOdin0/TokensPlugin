package me.untouchedodin0.tokens.enchantment;

import me.untouchedodin0.tokens.utils.addon.Enchantment;
import org.bukkit.Material;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.util.Collections;

public class Mighty extends Enchantment {

    public Mighty() {
        super("Mighty", 2, Collections.singletonList(Material.POTATO));
    }

    public void onInit() {
        System.out.println("THE MIGHTY POTATO is here?!!!!");
    }

    @Override
    public void onChat(AsyncPlayerChatEvent event) {
        // this doesn't make sense, but you can just leave it unimplemented
    }

    @Override
    public void onBreak(BlockBreakEvent event) {
        event.getPlayer().sendMessage("Mighty potato block break woo");
    }
}
