package me.untouchedodin0.tokens;

import me.untouchedodin0.tokens.commands.TokensCommand;
import me.untouchedodin0.tokens.enchantment.Explosive;
import org.bukkit.plugin.java.JavaPlugin;
import redempt.redlib.commandmanager.CommandParser;
import redempt.redlib.enchants.EnchantRegistry;

import java.io.File;

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
//
//        JarLoader jarLoader = new JarLoader();
//
//        for (File file : Objects.requireNonNull(addonsFolder.listFiles((file, name) -> name.endsWith(".jar")))) {
//            getLogger().info("Loading file: " + file.getPath());
//            jarLoader.load(file, JarLoadingClass.class);
//        }

        Explosive explosive = new Explosive();
        getLogger().info("explosive: " + explosive);
        explosive.onLoad();

//        JarLoader jarLoader = new JarLoader();

//        getLogger().info("loadedClasses: " + loadedClasses);
//        for (File file : Objects.requireNonNull(addonsFolder.listFiles((file, name) -> name.endsWith(".jar")))) {
//            getLogger().info("file: " + file);
//            try {
//                URL url = file.toURI().toURL();
//                getLogger().info("url: " + url);
//                URLClassLoader classLoader = (URLClassLoader) ClassLoader.getSystemClassLoader();
//                Method method = URLClassLoader.class.getDeclaredMethod("addURL", URL.class);
//                method.setAccessible(true);
//                method.invoke(Thread.currentThread().getContextClassLoader(), url);
//
//                getLogger().info("classLoader: " + classLoader);
//            } catch (MalformedURLException | NoSuchMethodException | InvocationTargetException |
//                     IllegalAccessException e) {
//                throw new RuntimeException(e);
//            }
//        }

//        getLogger().info("loadedClasses: " + loadedClasses);
    }


    public static Tokens getTokens() {
        return tokens;
    }

    public EnchantAPI getEnchantAPI() {
        return enchantAPI;
    }
}
