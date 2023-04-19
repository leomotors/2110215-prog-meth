package logic.card;

import logic.game.CardColor;
import logic.game.CardSymbol;
import logic.game.GameLogic;

public class DrawFourCard extends EffectCard {
    // Constructors
    public DrawFourCard() {
        super(null, CardSymbol.DRAW_FOUR);
    }

    // Methods
    @Override
    public String toString() {
        if (this.getColor() == null) {
            return "DRAW FOUR";
        }

        return String.format("DRAW FOUR (%s color selected)", this.getColor());
    }

    @Override
    public boolean canPlay() {
        return true;
    }

    @Override
    public String performEffect() {
        var game = GameLogic.getInstance();
        var playerHand = game.getCurrentPlayerHand();

        var newColor = playerHand.stream()
                .filter(card -> !card.equals(this))
                .findFirst()
                .map(card -> card.getColor())
                .orElse(CardColor.RED);

        this.setColor(newColor);
        var message = String.format("Set color to %s\n", newColor);

        game.incrementDrawAmount(4);

        int currentPlayer;
        do {
            game.goToNextPlayer();
            currentPlayer = game.getCurrentPlayer();
        } while (game.getPlayerHand(currentPlayer).isEmpty());

        var drawCard = game.getCurrentPlayerHand().stream()
                .filter(card -> card.getSymbol().equals(CardSymbol.DRAW_FOUR))
                .findFirst()
                .orElse(null);

        if (drawCard != null) {
            message += String.format(
                    "Player %d played %s. %d cards remaining.",
                    currentPlayer, drawCard,
                    game.getCurrentPlayerHand().size() - 1);

            return message + "\n" + drawCard.play();
        } else {
            game.draw(game.getDrawAmount());

            message += String.format("Player %d drew %d cards. %d cards remaining.",
                    currentPlayer, game.getDrawAmount(), game.getCurrentPlayerHand().size());

            game.setDrawAmount(0);
            return message;
        }
    }
}
