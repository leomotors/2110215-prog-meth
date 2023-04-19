package student;

import logic.card.BaseCard;
import logic.card.ChangeColorCard;
import logic.card.NumberCard;
import logic.card.SkipCard;
import logic.game.CardColor;
import logic.game.CardSymbol;
import logic.game.GameLogic;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class GameLogicTest {

    ArrayList<BaseCard> hand0;
    ArrayList<BaseCard> hand1;

    NumberCard c1;
    SkipCard c2;
    NumberCard c3;
    ChangeColorCard c4;

    @BeforeEach
    void setup() {
        c1 = new NumberCard(CardColor.YELLOW, CardSymbol.NINE);
        c2 = new SkipCard(CardColor.YELLOW);
        c3 = new NumberCard(CardColor.RED, CardSymbol.NINE);
        c4 = new ChangeColorCard();
    }

    @AfterEach
    void tearDown() {
        GameLogic.clearInstance();
    }

    @Test
    void testIsHandPlayableTrue() {
        var game = GameLogic.getInstance(2);
        game.setTopCard(c1);

        game.getPlayerHand(0).add(c2);
        assertTrue(game.isHandPlayable(0));

        game.getPlayerHand(1).add(c4);
        assertTrue(game.isHandPlayable(1));
    }

    @Test
    void testIsHandPlayableFalse() {
        var game = GameLogic.getInstance(2);
        game.setTopCard(c1);

        game.getPlayerHand(0).add(
                new NumberCard(CardColor.RED, CardSymbol.EIGHT));
        assertFalse(game.isHandPlayable(0));

        game.setTopCard(c3);
        game.getPlayerHand(1).add(c2);
        assertFalse(game.isHandPlayable(1));
    }

    @Test
    void testDrawLessThanDeck() {
        var game = GameLogic.getInstance();
        game.getDeck().add(c1);
        game.getDeck().add(c2);
        game.getDeck().add(c3);
        game.getDeck().add(c4);
        game.draw(2);

        assertEquals(2, game.getPlayerHand(0).size());
    }

    @Test
    void testDrawMoreThanDeck() {
        var game = GameLogic.getInstance();
        game.getDeck().add(c1);
        game.draw(69);

        assertEquals(1, game.getPlayerHand(0).size());
    }

}
