package me.untouchedodin0.tokens;

import me.untouchedodin0.tokens.commands.TokensCommand;
import me.untouchedodin0.tokens.enchantment.Mighty;
import me.untouchedodin0.tokens.listener.PickaxeRightClickEvent;
import me.untouchedodin0.tokens.listener.PlayerJoinListener;
import me.untouchedodin0.tokens.utils.SQLUtils;
import me.untouchedodin0.tokens.utils.addon.Enchantment;
import me.untouchedodin0.tokens.utils.loader.JarLoader;
import org.bukkit.plugin.java.JavaPlugin;
import redempt.redlib.commandmanager.CommandParser;
import redempt.redlib.enchants.EnchantRegistry;
import redempt.redlib.sql.SQLHelper;

import java.io.File;
import java.sql.Connection;
import java.util.Objects;

public class Tokens extends JavaPlugin {

    public static Tokens tokens;
    EnchantRegistry enchantRegistry;
    EnchantAPI enchantAPI;
    Connection connection;
    SQLHelper sqlHelper;
    SQLUtils sqlUtils;

    @Override
    public void onEnable() {
        getLogger().info("Loading Tokens...");
        saveDefaultConfig();
        tokens = this;
        connection = SQLHelper.openSQLite(getDataFolder().toPath().resolve("database.sql"));
        this.sqlHelper = new SQLHelper(connection);
        this.enchantAPI = new EnchantAPI();
        this.sqlUtils = new SQLUtils(sqlHelper);
        this.enchantRegistry = new EnchantRegistry(this);

        getLogger().info("connection: " + connection);
        sqlHelper.executeUpdate("CREATE TABLE IF NOT EXISTS tokens (uuid TEXT UNIQUE, tokens STRING)");

        File addonsFolder = new File(getDataFolder() + "/addons");
        boolean createdAddonsFolder = addonsFolder.mkdirs();

        new CommandParser(getResource("commands.rdcml"))
                .parse()
                .register("tokens", new TokensCommand());

        JarLoader jarLoader = new JarLoader();
//
//        for (File file : Objects.requireNonNull(addonsFolder.listFiles((file, name) -> name.endsWith(".jar")))) {
//            getLogger().info("Loading file: " + file.getPath());
//            jarLoader.load(file, JarLoadingClass.class);
//        }

        getLogger().info("jarLoader: " + jarLoader);
        Mighty mighty = new Mighty();
        getLogger().info("mighty: " + mighty);
        mighty.onInit();

        for (File file : Objects.requireNonNull(addonsFolder.listFiles((file, name) -> name.endsWith(".jar")))) {
            getLogger().info("Loading file: " + file.getName());
            jarLoader.load(file, Enchantment.class);
        }

        enchantRegistry.registerAll(tokens);
//        this.enchantRegistry = EnchantRegistry.get(tokens);

        getServer().getPluginManager().registerEvents(new PlayerJoinListener(), this);
        getServer().getPluginManager().registerEvents(new PickaxeRightClickEvent(enchantRegistry), this);
    }


    public static Tokens getTokens() {
        return tokens;
    }

    public EnchantAPI getEnchantAPI() {
        return enchantAPI;
    }

    public EnchantRegistry getEnchantRegistry() {
        return enchantRegistry;
    }

    public SQLUtils getSqlUtils() {
        return sqlUtils;
    }
}
