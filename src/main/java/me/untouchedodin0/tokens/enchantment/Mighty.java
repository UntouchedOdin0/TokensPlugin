package me.untouchedodin0.tokens.enchantment;

import me.untouchedodin0.tokens.Tokens;
import me.untouchedodin0.tokens.utils.addon.Enchantment;
import org.bukkit.Material;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import redempt.redlib.enchants.EnchantRegistry;

import java.util.Collections;

public class Mighty extends Enchantment {

    Tokens tokens;
    EnchantRegistry enchantRegistry;
    public Mighty() {
        super("Mighty", 2, Collections.singletonList(Material.POTATO));
//        this.tokens = Tokens.tokens;
//        Bukkit.getLogger().info("tokens: " + tokens);
//
//        this.enchantRegistry = EnchantRegistry.get(tokens);
    }

    public void onInit() {
        this.tokens = Tokens.tokens;
        this.enchantRegistry = tokens.getEnchantRegistry();

//        System.out.println("THE MIGHTY POTATO is here?!!!!");
//        tokens.getLogger().info("enchantRegistry: " + enchantRegistry);

        tokens.getLogger().info(String.format("Registering enchant %s into the registry!", getName()));
        enchantRegistry.getEnchants().forEach(customEnchant -> {
            tokens.getLogger().info("found enchant: " + customEnchant.getName() + "!");
        });
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
