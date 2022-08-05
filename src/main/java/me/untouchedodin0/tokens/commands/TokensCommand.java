package me.untouchedodin0.tokens.commands;

import me.untouchedodin0.tokens.utils.SQLUtils;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import redempt.redlib.commandmanager.CommandHook;

import java.math.BigInteger;
import java.util.UUID;

public class TokensCommand {

    @CommandHook("main")
    public void main(CommandSender sender) {
        if (sender instanceof Player player) {
            String tokens = SQLUtils.getTokens(player.getUniqueId());
            double tokensDouble = Double.parseDouble(tokens);
            player.sendMessage(ChatColor.GREEN + "You have " + ChatColor.YELLOW + String.format("%.0f", tokensDouble) + ChatColor.GREEN + " tokens.");
        }
    }

    @CommandHook("give")
    public void give(CommandSender commandSender, Player target, Double amount) {
        UUID targetUUID = target.getUniqueId();
        commandSender.sendMessage(ChatColor.GREEN + "Giving " + target.getName() + " " + String.format("%.0f", amount) + " tokens");
        SQLUtils.addTokens(targetUUID, amount);
    }

    @CommandHook("pay")
    public void pay(CommandSender commandSender, Player target, Double amount) {
        if (commandSender instanceof Player player) {
            UUID targetUUID = target.getUniqueId();
            commandSender.sendMessage(ChatColor.GREEN + "Paying " + target.getName() + " " + String.format("%.0f", amount) + " tokens!");
            SQLUtils.removeTokens(player.getUniqueId(), amount);
            SQLUtils.addTokens(targetUUID, amount);
        }
    }

    @CommandHook("set")
    public void set(CommandSender commandSender, Player target, Double amount) {
        UUID targetUUID = target.getUniqueId();
        commandSender.sendMessage(String.format(ChatColor.GREEN + "Setting %s's tokens to %.0f!", target.getName(), amount));
        SQLUtils.setTokens(targetUUID, amount);
    }

    @CommandHook("remove")
    public void remove(CommandSender commandSender, Player target, Double amount) {
        UUID targetUUID = target.getUniqueId();
        String tokens = SQLUtils.getTokens(targetUUID);
        BigInteger tokensBig = BigInteger.valueOf(Long.parseLong(tokens));
        if (tokensBig.signum() < amount) {
            commandSender.sendMessage(ChatColor.RED + "That player doesn't have that many tokens.");
        } else {
            commandSender.sendMessage(String.format(ChatColor.GREEN + "Removing %.0f tokens from %s!", amount, target.getName()));
            SQLUtils.removeTokens(targetUUID, amount);
        }
    }
}
