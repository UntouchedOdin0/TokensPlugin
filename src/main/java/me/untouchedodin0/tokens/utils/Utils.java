package me.untouchedodin0.tokens.utils;

public class Utils {

    /**
     * Converts a number to roman numerals, between 1 and 10
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
}
