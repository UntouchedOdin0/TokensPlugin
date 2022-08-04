package me.untouchedodin0.tokens.listener;

import me.untouchedodin0.tokens.Tokens;
import me.untouchedodin0.tokens.menu.EnchantMenu;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.plugin.Plugin;
import redempt.redlib.enchants.EnchantRegistry;

public class PickaxeRightClickEvent implements Listener {

    Player player;
    EnchantRegistry enchantRegistry;

    public PickaxeRightClickEvent(EnchantRegistry enchantRegistry) {
        this.enchantRegistry = enchantRegistry;
    }

    @EventHandler
    public void onRightClick(PlayerInteractEvent event) {
        this.player = event.getPlayer();
        Action action = event.getAction();
        EnchantRegistry enchantRegistry1 = Tokens.getTokens().getEnchantRegistry();

        if (player.getInventory().getItemInMainHand().getType() != Material.DIAMOND_PICKAXE) {
            return;
        } else if (player.getInventory().getItemInMainHand().getType() == Material.DIAMOND_PICKAXE
                && action == Action.RIGHT_CLICK_BLOCK
                || action == Action.RIGHT_CLICK_AIR) {
            EnchantMenu enchantMenu = new EnchantMenu(enchantRegistry);
            enchantMenu.openNew(player);

//            MainMenu mainMenu = new MainMenu(plugin);
//            mainMenu.open(player);
        }
    }
}