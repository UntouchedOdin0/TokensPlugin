package me.untouchedodin0.tokens.utils;

import me.untouchedodin0.tokens.utils.events.TokenGainEvent;
import org.bukkit.Bukkit;
import redempt.redlib.misc.Task;
import redempt.redlib.sql.SQLHelper;

import java.util.UUID;

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

    public static void addTokens(UUID uuid, Double amount) {
        Double tokens = Double.valueOf(getTokens(uuid));
        Double added = tokens + amount;
        setTokens(uuid, added);

        TokenGainEvent tokenGainEvent = new TokenGainEvent(uuid, amount);
        Task.syncDelayed(() -> {
            Bukkit.getPluginManager().callEvent(tokenGainEvent);
        });
    }

    public static void addTokens(UUID uuid, Double amount, int merchantLevel) {
        Double tokens = Double.valueOf(getTokens(uuid));
        Double added = tokens + amount;
        setTokens(uuid, added);

        TokenGainEvent tokenGainEvent = new TokenGainEvent(uuid, amount);
        Task.syncDelayed(() -> {
            Bukkit.getPluginManager().callEvent(tokenGainEvent);
        });
    }

    public static void removeTokens(UUID uuid, Double amount) {
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
}