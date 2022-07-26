package me.untouchedodin0.tokens.commands;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import redempt.redlib.commandmanager.CommandHook;

public class TokensCommand {

    @CommandHook("debug")
    public void debug(Player player) {
        player.sendMessage("debugging.");
        Location location = player.getLocation();

    }
}
