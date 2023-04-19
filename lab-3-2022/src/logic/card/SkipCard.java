package logic.card;

import logic.game.CardColor;
import logic.game.CardSymbol;
import logic.game.GameLogic;

public class SkipCard extends EffectCard {
    // Constructors
    public SkipCard(CardColor color) {
        super(color, CardSymbol.SKIP);
    }

    // Methods
    @Override
    public String toString() {
        return String.format("%s %s", this.getColor(), this.getSymbol());
    }

    @Override
    public boolean canPlay() {
        var topCard = GameLogic.getInstance().getTopCard();

        return topCard.getColor().equals(this.getColor())
                || topCard.getSymbol().equals(this.getSymbol());
    }

    @Override
    public String performEffect() {
        var game = GameLogic.getInstance();

        int currentPlayer;
        do {
            game.goToNextPlayer();
            currentPlayer = game.getCurrentPlayer();
        } while (game.getPlayerHand(currentPlayer).isEmpty());

        return String.format("Skipped player %d", currentPlayer);
    }
}
