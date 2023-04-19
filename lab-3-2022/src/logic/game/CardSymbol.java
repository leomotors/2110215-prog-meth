package logic.game;

import utils.Randomizer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public enum CardSymbol {
    ZERO,
    ONE,
    TWO,
    THREE,
    FOUR,
    FIVE,
    SIX,
    SEVEN,
    EIGHT,
    NINE,

    SKIP,
    REVERSE,
    CHANGE_COLOR,
    DRAW_TWO,
    DRAW_FOUR;

    private static final Random RNG = Randomizer.getRandomizer();

    public static CardSymbol randomSymbol() {
        CardSymbol[] symbols = values();
        return symbols[RNG.nextInt(symbols.length)];
    }

    public static ArrayList<CardSymbol> getNumberSymbols() {
        return new ArrayList<>(Arrays.asList(ZERO, ONE, TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE));
    }

    @Override
    public String toString() {
        return super.toString().replace("_", " ");
    }
}
