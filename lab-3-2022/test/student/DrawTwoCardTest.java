package student;

import logic.card.*;
import logic.game.CardColor;
import logic.game.CardSymbol;
import logic.game.GameLogic;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

public class DrawTwoCardTest {
    DrawTwoCard c1;
    NumberCard c2;
    DrawTwoCard c3;
    DrawTwoCard c4;
    NumberCard c5;
    DrawFourCard c6;

    @BeforeEach
    void setup() {
        c1 = new DrawTwoCard(CardColor.RED);
        c2 = new NumberCard(CardColor.RED, CardSymbol.NINE);
        c3 = new DrawTwoCard(CardColor.GREEN);
        c4 = new DrawTwoCard(CardColor.RED);
        c5 = new NumberCard(CardColor.BLUE, CardSymbol.FIVE);
        c6 = new DrawFourCard();
    }

    @AfterEach
    void tearDown() {
        GameLogic.clearInstance();
    }

    @Test
    void testConstructor() {
        assertEquals(CardColor.RED, c1.getColor());
        assertEquals(CardSymbol.DRAW_TWO, c1.getSymbol());

        assertEquals(CardColor.GREEN, c3.getColor());
        assertEquals(CardSymbol.DRAW_TWO, c3.getSymbol());

        assertEquals(CardColor.RED, c4.getColor());
        assertEquals(CardSymbol.DRAW_TWO, c4.getSymbol());
    }

    @Test
    void testToString() {
        assertEquals("RED DRAW TWO", c1.toString());
        assertEquals("GREEN DRAW TWO", c3.toString());
        assertEquals("RED DRAW TWO", c4.toString());
    }

    @Test
    void testCanPlay() {
        GameLogic gameInstance = GameLogic.getInstance(1);

        // Match color
        gameInstance.setTopCard(c2);
        assertTrue(c1.canPlay());

        // Match symbol
        gameInstance.setTopCard(c3);
        assertTrue(c1.canPlay());

        // Match both
        gameInstance.setTopCard(c4);
        assertTrue(c1.canPlay());

        // No match
        gameInstance.setTopCard(c5);
        assertFalse(c1.canPlay());
    }

    @Test
    void testPerformEffect1() {
        // Next player hand non empty, has +2
        GameLogic gameInstance = GameLogic.getInstance(2);
        for (int i = 0; i < 4; i++)
            gameInstance.getDeck().add(new NumberCard(CardColor.randomColor(), CardSymbol.randomSymbol()));
        gameInstance.getPlayerHand(0).add(c2);
        gameInstance.getPlayerHand(1).add(c3);
        gameInstance.getPlayerHand(1).add(c5);

        String message = c1.performEffect();

        assertEquals(0, gameInstance.getDrawAmount());
        assertEquals("Player 1 played GREEN DRAW TWO. 1 cards remaining.\n" +
                "Player 0 drew 4 cards. 5 cards remaining.", message);
    }

    @Test
    void testPerformEffect2() {
        // Next player hand empty, skip to the player after, has +2
        GameLogic gameInstance = GameLogic.getInstance(3);
        for (int i = 0; i < 4; i++)
            gameInstance.getDeck().add(new NumberCard(CardColor.randomColor(), CardSymbol.randomSymbol()));
        gameInstance.getPlayerHand(0).add(c2);
        gameInstance.getPlayerHand(2).add(c3);
        gameInstance.getPlayerHand(2).add(c5);

        String message = c1.performEffect();

        assertEquals(0, gameInstance.getDrawAmount());
        assertEquals("Player 2 played GREEN DRAW TWO. 1 cards remaining.\n" +
                "Player 0 drew 4 cards. 5 cards remaining.", message);
    }

    @Test
    void testPerformEffect3() {
        // Next player hand has +4 before +2
        GameLogic gameInstance = GameLogic.getInstance(3);
        for (int i = 0; i < 6; i++)
            gameInstance.getDeck().add(new NumberCard(CardColor.randomColor(), CardSymbol.randomSymbol()));
        gameInstance.getPlayerHand(0).add(c2);
        gameInstance.getPlayerHand(2).add(c6);
        gameInstance.getPlayerHand(2).add(c3);

        String message = c1.performEffect();

        assertEquals(0, gameInstance.getDrawAmount());
        assertEquals("Player 2 played DRAW FOUR. 1 cards remaining.\n" +
                "Set color to GREEN\n" +
                "Player 0 drew 6 cards. 7 cards remaining.", message);
    }

    @Test
    void testPerformEffect4() {
        // Next player hand doesn't have +2
        GameLogic gameInstance = GameLogic.getInstance(2);
        for (int i = 0; i < 2; i++)
            gameInstance.getDeck().add(new NumberCard(CardColor.randomColor(), CardSymbol.randomSymbol()));
        gameInstance.getPlayerHand(1).add(c2);

        String message = c1.performEffect();

        assertEquals(0, gameInstance.getDrawAmount());
        assertEquals("Player 1 drew 2 cards. 3 cards remaining.", message);
    }

    void createHand(ArrayList<BaseCard> hand, BaseCard... card) {
        hand.clear();

        for (var c : card)
            hand.add(c);
    }

    @Test
    void testPlay() {
        // +2 +2 +2 +4
        var game = GameLogic.getInstance(4);
        this.createHand(game.getPlayerHand(0), c1, c2);
        this.createHand(game.getPlayerHand(1), c3);
        this.createHand(game.getPlayerHand(2), c4);
        this.createHand(game.getPlayerHand(3), c6);

        // Fill deck with cards to draw
        for (int i = 0; i < 69; i++) {
            game.getDeck().add(new NumberCard(
                    CardColor.randomColor(), CardSymbol.randomSymbol()));
        }

        var message = game.getCurrentPlayerHand().get(0).play();

        assertEquals("Player 1 played GREEN DRAW TWO. 0 cards remaining.\n" +
                "Player 2 played RED DRAW TWO. 0 cards remaining.\n" +
                "Player 3 played DRAW FOUR. 0 cards remaining.\n" +
                "Set color to RED\n" +
                "Player 0 drew 10 cards. 11 cards remaining.", message);
    }

}
