package me.untouchedodin0.tokens.utils;

import org.bukkit.Location;
import org.bukkit.inventory.ItemStack;
import redempt.redlib.enchants.CustomEnchant;

import java.util.ArrayList;
import java.util.List;

public class Utils {

    public static int angle = 0;

    /**
     * Converts a number to roman numerals, between 1 and 10
     *
     * @param num The number to convert
     * @return The roman numerals representation of the number
     */
    public static String toRomanNumerals(int num) {
        return switch (num) {
            case 1 -> "I";
            case 2 -> "II";
            case 3 -> "III";
            case 4 -> "IV";
            case 5 -> "V";
            case 6 -> "VI";
            case 7 -> "VII";
            case 8 -> "VIII";
            case 9 -> "IX";
            case 10 -> "X";
            default -> num + "";
        };
    }

    /**
     * Converts roman numeral string, between 1 and 10, back to a number
     *
     * @param romanNumerals The roman numerals string
     * @return The number represented by the roman numerals
     */
    public static int fromRomanNumerals(String romanNumerals) {
        switch (romanNumerals) {
            case "I":
                return 1;
            case "II":
                return 2;
            case "III":
                return 3;
            case "IV":
                return 4;
            case "V":
                return 5;
            case "VI":
                return 6;
            case "VII":
                return 7;
            case "VIII":
                return 8;
            case "IX":
                return 9;
            case "X":
                return 10;
            default:
                for (char c : romanNumerals.toCharArray()) {
                    if (c > '9' || c < '0') {
                        return 0;
                    }
                }
                return Integer.parseInt(romanNumerals);
        }
    }

    // Credits to the madlad finnbon.
    public static List<Location> getCone(Location location, int maxHeight, double maxRadius) {

        List<Location> locations = new ArrayList<>();

        int max_height = 15; // The max height of the tornado.
        double max_radius = 10; // The max radius of the tornado.
        int lines = 4; // The amount of particle lines it exsists of.
        double height_increasement = 0.5; // The increasement in height per particle.

        // This is the increasement in the radius per y level.
        // As you can see, at the bottom, the radius is 0.
        // Y is 0, and the radius increasement is 0.67 (not exactly, but you get it).
        // 0 x 0.33 = 0. At the top the radius is 10.
        // The height there is 15. 15 * 0.67 = 10. (Again, not exactly).

        double radius_increasement = maxRadius / maxHeight;

        for (int l = 0; l < lines; l++) {
            for (double y = 0; y < max_height; y+= height_increasement) {
                double radius = y * radius_increasement;
                double x = Math.cos(Math.toRadians(360.0 / lines * l * 25 - angle)) * radius;
                double z = Math.sin(Math.toRadians(360.0 / lines * l + y * 25 - angle)) * radius;
                locations.add(location.clone().add(x, y, z));
            }
        }

        return locations;
    }

    public static ItemStack increaseLevel(ItemStack itemStack, CustomEnchant customEnchant, int amount) {
        if (customEnchant.getLevel(itemStack) + amount > customEnchant.getMaxLevel()) return itemStack;
        return customEnchant.apply(itemStack, customEnchant.getLevel(itemStack) + amount);
    }
}
