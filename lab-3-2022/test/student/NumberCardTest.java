package student;

import logic.card.BaseCard;
import logic.card.NumberCard;
import logic.game.CardSymbol;
import logic.game.CardColor;
import logic.game.GameLogic;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class NumberCardTest {

    NumberCard c1;
    NumberCard c2;
    NumberCard c3;
    NumberCard c4;
    NumberCard c5;

    @BeforeEach
    void setup() {
        c1 = new NumberCard(CardColor.RED, CardSymbol.ONE);
        c2 = new NumberCard(CardColor.RED, CardSymbol.NINE);
        c3 = new NumberCard(CardColor.GREEN, CardSymbol.ONE);
        c4 = new NumberCard(CardColor.RED, CardSymbol.ONE);
        c5 = new NumberCard(CardColor.BLUE, CardSymbol.FIVE);
    }

    @AfterEach
    void tearDown() {
        GameLogic.clearInstance();
    }

    @Test
    void testConstructor() {
        assertEquals(CardColor.RED, c1.getColor());
        assertEquals(CardSymbol.ONE, c1.getSymbol());

        assertEquals(CardColor.RED, c2.getColor());
        assertEquals(CardSymbol.NINE, c2.getSymbol());

        assertEquals(CardColor.GREEN, c3.getColor());
        assertEquals(CardSymbol.ONE, c3.getSymbol());

        assertEquals(CardColor.RED, c4.getColor());
        assertEquals(CardSymbol.ONE, c4.getSymbol());

        assertEquals(CardColor.BLUE, c5.getColor());
        assertEquals(CardSymbol.FIVE, c5.getSymbol());
    }

    @Test
    void testToString() {
        assertEquals("RED ONE", c1.toString());
        assertEquals("RED NINE", c2.toString());
        assertEquals("GREEN ONE", c3.toString());
        assertEquals("RED ONE", c4.toString());
        assertEquals("BLUE FIVE", c5.toString());
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
    void testPlay() {
        GameLogic gameInstance = GameLogic.getInstance(1);
        gameInstance.getCurrentPlayerHand().add(c1);

        String message = c1.play();

        assertEquals(c1, gameInstance.getTopCard());
        assertEquals(0, gameInstance.getCurrentPlayerHand().size());
        assertNull(message);
    }

}
