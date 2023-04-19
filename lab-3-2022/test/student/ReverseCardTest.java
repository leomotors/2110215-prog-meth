package student;

import logic.card.BaseCard;
import logic.card.NumberCard;
import logic.card.ReverseCard;
import logic.game.CardColor;
import logic.game.CardSymbol;
import logic.game.GameLogic;
import logic.game.PlayDirection;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.function.Consumer;

public class ReverseCardTest {
    ReverseCard c1;
    NumberCard c2;
    ReverseCard c3;
    ReverseCard c4;
    NumberCard c5;

    @BeforeEach
    void setup() {
        c1 = new ReverseCard(CardColor.RED);
        c2 = new NumberCard(CardColor.RED, CardSymbol.NINE);
        c3 = new ReverseCard(CardColor.GREEN);
        c4 = new ReverseCard(CardColor.RED);
        c5 = new NumberCard(CardColor.BLUE, CardSymbol.FIVE);
    }

    @AfterEach
    void tearDown() {
        GameLogic.clearInstance();
    }

    @Test
    void testConstructor() {
        assertEquals(CardColor.RED, c1.getColor());
        assertEquals(CardSymbol.REVERSE, c1.getSymbol());

        assertEquals(CardColor.GREEN, c3.getColor());
        assertEquals(CardSymbol.REVERSE, c3.getSymbol());

        assertEquals(CardColor.RED, c4.getColor());
        assertEquals(CardSymbol.REVERSE, c4.getSymbol());
    }

    @Test
    void testToString() {
        assertEquals("RED REVERSE", c1.toString());
        assertEquals("GREEN REVERSE", c3.toString());
        assertEquals("RED REVERSE", c4.toString());
    }

    private void assertCanPlay(BaseCard... cards) {
        for (var card : cards)
            assertTrue(card.canPlay());
    }

    private void assertCannotPlay(BaseCard... cards) {
        for (var card : cards)
            assertFalse(card.canPlay());
    }

    @Test
    void testCanPlay() {
        var game = GameLogic.getInstance();

        game.setTopCard(c1);
        this.assertCanPlay(c1, c2, c3, c4);
        this.assertCannotPlay(c5);

        game.setTopCard(c2);
        this.assertCanPlay(c1, c2, c4);
        this.assertCannotPlay(c3, c5);

        game.setTopCard(c3);
        this.assertCanPlay(c1, c3, c4);
        this.assertCannotPlay(c2, c5);

        game.setTopCard(c4);
        this.assertCanPlay(c1, c2, c3, c4);
        this.assertCannotPlay(c5);

        game.setTopCard(c5);
        this.assertCanPlay(c5);
        this.assertCannotPlay(c1, c2, c3, c4);
    }

    @Test
    void testPerformEffect() {
        GameLogic gameInstance = GameLogic.getInstance(3);

        String message = c1.performEffect();

        assertEquals(PlayDirection.BACKWARD, gameInstance.getPlayDirection());
        assertEquals("Set direction to BACKWARD", message);

        message = c3.performEffect();

        assertEquals(PlayDirection.FORWARD, gameInstance.getPlayDirection());
        assertEquals("Set direction to FORWARD", message);
    }

    @Test
    void testPlay() {
        GameLogic gameInstance = GameLogic.getInstance(3);
        gameInstance.getPlayerHand(0).add(c1);
        gameInstance.getPlayerHand(1).add(c2);
        gameInstance.getPlayerHand(2).add(c3);

        String message = c1.play();

        assertEquals(c1, gameInstance.getTopCard());
        assertEquals(0, gameInstance.getPlayerHand(0).size());
        assertEquals(PlayDirection.BACKWARD, gameInstance.getPlayDirection());
        assertEquals("Set direction to BACKWARD", message);
    }

}
