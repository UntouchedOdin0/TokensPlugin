package me.untouchedodin0.tokens.commands;

import me.untouchedodin0.tokens.Tokens;
import me.untouchedodin0.tokens.utils.SQLUtils;
import me.untouchedodin0.tokens.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;
import redempt.redlib.commandmanager.CommandHook;
import redempt.redlib.enchants.CustomEnchant;
import redempt.redlib.enchants.EnchantRegistry;
import redempt.redlib.misc.LocationUtils;
import redempt.redlib.misc.Task;

import java.math.BigInteger;
import java.util.List;
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

    @CommandHook("spawntornado")
    public void spawnTornado(Player player) {
        Location location = player.getLocation();
        player.sendMessage("a");
        List<Location> tornadoLocations = Utils.getCone(location, 15, 5);

        tornadoLocations.forEach(location1 -> {
            player.sendMessage("" + LocationUtils.toString(location1));
            location1.getBlock().setType(Material.EMERALD_BLOCK);
//
//            new ParticleBuilder(ParticleEffect.CLOUD, location1).setSpeed(0.1f)
//                            .display();
//
//            ParticleEffect.CLOUD.display(location1);
        });

        Task.syncRepeating(() -> {

            int ticks = 0;

            tornadoLocations.forEach(location1 -> {
                Vector vector = location1.toVector();
                Vector added = vector.rotateAroundX(1);
                location1.setDirection(added);
                Bukkit.broadcastMessage("angle: " + added.toString());
            });
        }, 0L, 20L);
//        player.sendMessage("" + tornadoLocations);
//        player.sendMessage(LocationUtils.toString(location));
    }

    @CommandHook("enchanttestcommand")
    public void test(Player player) {
        ItemStack itemStack = player.getInventory().getItemInMainHand();
        EnchantRegistry enchantRegistry = Tokens.getTokens().getEnchantRegistry();
        player.sendMessage("" + enchantRegistry);
        CustomEnchant test = enchantRegistry.getByName("Test");
        player.sendMessage("" + test);
        test.apply(itemStack, 1);
    }

    @CommandHook("enchantincreasecommand")
    public void increase(Player player) {
        ItemStack itemStack = player.getInventory().getItemInMainHand();
        EnchantRegistry enchantRegistry = Tokens.getTokens().getEnchantRegistry();
        CustomEnchant test = enchantRegistry.getByName("Test");
        ItemStack upgraded = Utils.increaseLevel(itemStack, test, 1);
        player.getInventory().setItemInMainHand(upgraded);
    }
}
