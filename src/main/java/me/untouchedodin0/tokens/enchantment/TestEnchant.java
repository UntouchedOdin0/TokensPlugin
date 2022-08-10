package me.untouchedodin0.tokens.enchantment;

import org.bukkit.Bukkit;
import redempt.redlib.enchants.CustomEnchant;
import redempt.redlib.enchants.trigger.EnchantTrigger;

public class TestEnchant extends CustomEnchant {

    public TestEnchant() {
        super("Test", 100);
        addTrigger(EnchantTrigger.MINE_BLOCK, ((event, integer) -> {
            Bukkit.broadcastMessage("event: " + event);
            Bukkit.broadcastMessage("integer: " + integer);
        }));
    }
}
