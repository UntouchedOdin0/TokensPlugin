package me.untouchedodin0.tokens;

import me.untouchedodin0.tokens.utils.jar.JarLoader;
import me.untouchedodin0.tokens.utils.jar.LoadingClass;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Tokens extends JavaPlugin {

    List<LoadingClass> loadedClasses = new ArrayList<>();

    @Override
    public void onEnable() {
        getLogger().info("Loading Tokens...");
        saveDefaultConfig();
        File addonsFolder = new File(getDataFolder() + "/addons");
        boolean createdAddonsFolder = addonsFolder.mkdirs();
        JarLoader jarLoader = new JarLoader();

        getLogger().info("loadedClasses: " + loadedClasses);
        for (File file : Objects.requireNonNull(addonsFolder.listFiles((file, name) -> name.endsWith(".jar")))) {
            getLogger().info("Loading file: " + file.getName());
            loadedClasses.add(jarLoader.load(file, LoadingClass.class));
        }
        getLogger().info("loadedClasses: " + loadedClasses);
    }
}
