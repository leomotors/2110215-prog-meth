package logic.game;

import utils.Randomizer;

import java.util.Random;

public enum CardColor {
    RED,
    YELLOW,
    GREEN,
    BLUE;

    private static final Random RNG = Randomizer.getRandomizer();

    public static CardColor randomColor() {
        CardColor[] colors = values();
        return colors[RNG.nextInt(colors.length)];
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
