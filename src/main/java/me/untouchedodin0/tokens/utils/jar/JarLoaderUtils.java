package me.untouchedodin0.tokens.utils.jar;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class JarLoaderUtils {

    public static List<LoadingClass> loadAll(File dir) {
        if (!dir.isDirectory()) {
            return Collections.emptyList();
        }
        if (!dir.exists()) {
            return Collections.emptyList();
        }
        List<LoadingClass> loaded = new ArrayList<>();
        JarLoader loader = new JarLoader();
        for (File file : Objects.requireNonNull(dir.listFiles((file, name) -> name.endsWith(".jar")))) {
            loaded.add(loader.load(file, LoadingClass.class));
        }
        return loaded;
    }
}
