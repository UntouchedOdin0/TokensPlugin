package me.untouchedodin0.tokens.commands;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import redempt.redlib.commandmanager.CommandHook;

public class TokensCommand {

    @CommandHook("debug")
    public void debug(Player player) {
        player.sendMessage("debugging.");
        Location location = player.getLocation();
        ItemStack itemStack = player.getInventory().getItemInMainHand();

    }
}
