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

public class DrawFourCardTest {
    DrawFourCard c1;
    NumberCard c2;
    DrawTwoCard c3;
    DrawFourCard c4;
    DrawFourCard c5;

    @BeforeEach
    void setup() {
        c1 = new DrawFourCard();
        c2 = new NumberCard(CardColor.YELLOW, CardSymbol.ZERO);
        c3 = new DrawTwoCard(CardColor.GREEN);
        c4 = new DrawFourCard();
        c5 = new DrawFourCard();
    }

    @AfterEach
    void tearDown() {
        GameLogic.clearInstance();
    }

    @Test
    void testConstructor() {
        assertNull(c1.getColor());
        assertEquals(CardSymbol.DRAW_FOUR, c1.getSymbol());
    }

    @Test
    void testToString() {
        assertEquals("DRAW FOUR", c1.toString());
        c1.setColor(CardColor.YELLOW);
        assertEquals("DRAW FOUR (YELLOW color selected)", c1.toString());
    }

    @Test
    void testCanPlay() {
        GameLogic gameInstance = GameLogic.getInstance(1);

        gameInstance.setTopCard(c2);
        assertTrue(c1.canPlay());

        gameInstance.setTopCard(c3);
        assertTrue(c1.canPlay());

        gameInstance.setTopCard(c4);
        assertTrue(c1.canPlay());
    }

    @Test
    void testPerformEffect1() {
        // Next player hand non empty, has +4 / player 0 first card is yellow, player 1
        // first card is green
        GameLogic gameInstance = GameLogic.getInstance(2);
        for (int i = 0; i < 8; i++)
            gameInstance.getDeck().add(new NumberCard(CardColor.randomColor(), CardSymbol.randomSymbol()));
        gameInstance.getPlayerHand(0).add(c2);
        gameInstance.getPlayerHand(1).add(c4);
        gameInstance.getPlayerHand(1).add(c3);

        String message = c1.performEffect();

        assertEquals(0, gameInstance.getDrawAmount());
        assertEquals("Set color to YELLOW\n" +
                "Player 1 played DRAW FOUR. 1 cards remaining.\n" +
                "Set color to GREEN\n" +
                "Player 0 drew 8 cards. 9 cards remaining.", message);
    }

    @Test
    void testPerformEffect2() {
        // Next player hand empty, skip to the player after, has +4 / player 0 first
        // card has no color, player 1 has no cards left
        ChangeColorCard c6 = new ChangeColorCard();

        GameLogic gameInstance = GameLogic.getInstance(3);
        for (int i = 0; i < 8; i++)
            gameInstance.getDeck().add(new NumberCard(CardColor.randomColor(), CardSymbol.randomSymbol()));
        gameInstance.getPlayerHand(0).add(c6);
        gameInstance.getPlayerHand(0).add(c2);
        gameInstance.getPlayerHand(2).add(c4);

        String message = c1.performEffect();

        assertEquals(0, gameInstance.getDrawAmount());
        assertEquals("Set color to RED\n" +
                "Player 2 played DRAW FOUR. 0 cards remaining.\n" +
                "Set color to RED\n" +
                "Player 0 drew 8 cards. 10 cards remaining.", message);
    }

    @Test
    void testPerformEffect3() {
        // Next player hand doesn't have +4
        GameLogic gameInstance = GameLogic.getInstance(2);
        for (int i = 0; i < 4; i++)
            gameInstance.getDeck().add(new NumberCard(CardColor.randomColor(), CardSymbol.randomSymbol()));
        gameInstance.getPlayerHand(0).add(c2);
        gameInstance.getPlayerHand(1).add(c3);

        String message = c1.performEffect();

        assertEquals(0, gameInstance.getDrawAmount());
        assertEquals("Set color to YELLOW\nPlayer 1 drew 4 cards. 5 cards remaining.", message);
    }

    void createHand(ArrayList<BaseCard> hand, BaseCard card) {
        hand.clear();
        hand.add(new DrawFourCard());

        if (card != null)
            hand.add(card);
    }

    @Test
    void testPlay() {
        // +4 +4 +4 +4
        var game = GameLogic.getInstance(4);

        this.createHand(game.getPlayerHand(0), c2);
        this.createHand(game.getPlayerHand(1), c3);
        this.createHand(
                game.getPlayerHand(2),
                new NumberCard(CardColor.BLUE, CardSymbol.SIX));
        this.createHand(game.getPlayerHand(3), null);

        // Fill deck with cards to draw
        for (int i = 0; i < 69; i++) {
            game.getDeck().add(new NumberCard(
                    CardColor.randomColor(), CardSymbol.randomSymbol()));
        }

        var message = game.getPlayerHand(0).get(0).play();

        assertEquals("Set color to YELLOW\n" +
                "Player 1 played DRAW FOUR. 1 cards remaining.\n" +
                "Set color to GREEN\n" +
                "Player 2 played DRAW FOUR. 1 cards remaining.\n" +
                "Set color to BLUE\n" +
                "Player 3 played DRAW FOUR. 0 cards remaining.\n" +
                "Set color to RED\n" +
                "Player 0 drew 16 cards. 17 cards remaining.", message);
    }
}
