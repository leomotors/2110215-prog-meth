package utils;

import java.util.Random;

public class Randomizer {
    private static Random randomizer = null;

    public static Random getRandomizer() {
        if (randomizer == null) {
            randomizer = new Random();
        }
        return randomizer;
    }
}
