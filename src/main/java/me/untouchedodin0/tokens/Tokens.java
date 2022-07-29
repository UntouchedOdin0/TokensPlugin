package me.untouchedodin0.tokens;

import me.untouchedodin0.tokens.commands.TokensCommand;
import me.untouchedodin0.tokens.enchantment.Mighty;
import me.untouchedodin0.tokens.utils.EnchantRegistry;
import me.untouchedodin0.tokens.utils.addon.Enchantment;
import me.untouchedodin0.tokens.utils.loader.JarLoader;
import org.bukkit.plugin.java.JavaPlugin;
import redempt.redlib.commandmanager.CommandParser;
//import redempt.redlib.enchants.EnchantRegistry;

import java.io.File;
import java.util.Objects;

public class Tokens extends JavaPlugin {

    public static Tokens tokens;
    EnchantRegistry enchantRegistry;
    EnchantAPI enchantAPI;

    @Override
    public void onEnable() {
        getLogger().info("Loading Tokens...");
        saveDefaultConfig();
        tokens = this;
        this.enchantRegistry = EnchantRegistry.get(this);
        this.enchantAPI = new EnchantAPI();

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
    }


    public static Tokens getTokens() {
        return tokens;
    }

    public EnchantAPI getEnchantAPI() {
        return enchantAPI;
    }
}
