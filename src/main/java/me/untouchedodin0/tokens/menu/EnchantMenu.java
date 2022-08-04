package me.untouchedodin0.tokens.menu;

import me.untouchedodin0.tokens.Tokens;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import redempt.redlib.enchants.EnchantRegistry;
import redempt.redlib.inventorygui.InventoryGUI;

import java.util.concurrent.atomic.AtomicInteger;

public class EnchantMenu {


    EnchantRegistry enchantRegistry;
    public EnchantMenu(EnchantRegistry enchantRegistry) {
        this.enchantRegistry = enchantRegistry;
    }

    public void openNew(Player player) {
        Tokens tokens = Tokens.getTokens();

        AtomicInteger slot = new AtomicInteger();

        ItemStack itemStack = player.getInventory().getItemInMainHand();

//        CustomEnchant explosiveEnchant = EnchantRegistry.get(plugin).getByName("Explosive");

        InventoryGUI inventoryGUI = new InventoryGUI(9, "Enchants");

        enchantRegistry.getEnchants().forEach(customEnchant -> {
            player.sendMessage(" --- ");
            player.sendMessage("customEnchant: " + customEnchant.getName());
            String displayName = tokens.getConfig().getString("Enchants." + customEnchant.getName() + ".displayname");
            player.sendMessage("Display name: " + displayName);
        });

        inventoryGUI.open(player);

        player.sendMessage("enchantRegistry: " + enchantRegistry);
        player.sendMessage("inventoryGUI: " + inventoryGUI);
    }
}
