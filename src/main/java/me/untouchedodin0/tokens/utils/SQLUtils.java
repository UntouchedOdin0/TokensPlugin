package me.untouchedodin0.tokens.utils;

import me.untouchedodin0.tokens.utils.events.TokenGainEvent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import redempt.redlib.misc.Task;
import redempt.redlib.sql.SQLHelper;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

public class SQLUtils {

    public static SQLHelper sqlHelper;

    public SQLUtils(SQLHelper sqlHelper) {
        SQLUtils.sqlHelper = sqlHelper;
    }

    public static void setupPlayer(UUID uuid) {
        sqlHelper.execute("INSERT OR IGNORE INTO tokens (uuid, tokens) VALUES ('" + uuid.toString() + "', 0)");
    }

    public static String getTokens(UUID uuid) {
        if (sqlHelper == null) return "0";
        return sqlHelper.querySingleResultString("SELECT tokens FROM tokens WHERE uuid= '" + uuid.toString() + "'");
    }

    public static void setTokens(UUID uuid, Double amount) {
        sqlHelper.execute("UPDATE tokens SET tokens = " + amount + " WHERE uuid = '" + uuid.toString() + "'");
    }

    public static void addTokens(UUID uuid , Double amount) {
        Double tokens = Double.valueOf(getTokens(uuid));
        Double added = tokens + amount;
        setTokens(uuid, added);

        TokenGainEvent tokenGainEvent = new TokenGainEvent(uuid, amount);
        Task.syncDelayed(() -> {
            Bukkit.getPluginManager().callEvent(tokenGainEvent);
        });
    }

    public static void addTokens(UUID uuid , Double amount, int merchantLevel) {
        Double tokens = Double.valueOf(getTokens(uuid));
        Double added = tokens + amount;
        setTokens(uuid, added);

        TokenGainEvent tokenGainEvent = new TokenGainEvent(uuid, amount);
        Task.syncDelayed(() -> {
            Bukkit.getPluginManager().callEvent(tokenGainEvent);
        });
    }

    public static void removeTokens(UUID uuid , Double amount) {
        Double tokens = Double.valueOf(getTokens(uuid));
        Double removed = tokens - amount;
        setTokens(uuid, removed);
//        TokenGainEvent tokenGainEvent = new TokenGainEvent(uuid, amount);
//        Bukkit.getPluginManager().callEvent(tokenGainEvent);
    }

    public static SQLHelper.Results getTop() {
        return sqlHelper.queryResults("SELECT * FROM tokens");
    }

    public static void clearTokens(UUID uuid) {
        setTokens(uuid, 0.0);
    }

    public static void sendTop(Player player, int amount) {
        SQLHelper.Results results = getTop();
        Map<Object, Object> map = new HashMap<>();
        AtomicInteger counter = new AtomicInteger(1);

        for (int i = 1; i < amount; i++) {
            Object uuid = results.get(1);
            Object tokens = results.get(2);
            map.putIfAbsent(uuid, tokens);
        }

        map.forEach((uuid, tokens) -> {
            String uuidString = uuid.toString();
            String playerName = Bukkit.getOfflinePlayer(UUID.fromString(uuidString)).getName();
            String tokensString = tokens.toString();
            int slot = counter.getAndIncrement();
            player.sendMessage(ChatColor.GREEN + String.format("#%d - %s - %s tokens", slot, playerName, tokensString));
        });
    }
}