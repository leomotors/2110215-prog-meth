package logic.card;

import logic.game.CardColor;
import logic.game.CardSymbol;
import logic.game.GameLogic;

public class DrawTwoCard extends EffectCard {
    // Constructors
    public DrawTwoCard(CardColor color) {
        super(color, CardSymbol.DRAW_TWO);
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

        game.incrementDrawAmount(2);

        int currentPlayer;
        do {
            game.goToNextPlayer();
            currentPlayer = game.getCurrentPlayer();
        } while (game.getPlayerHand(currentPlayer).isEmpty());

        var drawCard = game.getCurrentPlayerHand().stream()
                .filter(card -> card.getSymbol().equals(CardSymbol.DRAW_TWO)
                        || card.getSymbol().equals(CardSymbol.DRAW_FOUR))
                .findFirst()
                .orElse(null);

        if (drawCard != null) {
            var message = String.format(
                    "Player %d played %s. %d cards remaining.",
                    currentPlayer, drawCard,
                    game.getCurrentPlayerHand().size() - 1);

            return message + "\n" + drawCard.play();
        } else {
            game.draw(game.getDrawAmount());

            var message = String.format("Player %d drew %d cards. %d cards remaining.",
                    currentPlayer, game.getDrawAmount(), game.getCurrentPlayerHand().size());

            game.setDrawAmount(0);
            return message;
        }
    }
}
