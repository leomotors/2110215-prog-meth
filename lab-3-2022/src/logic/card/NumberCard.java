package logic.card;

import logic.game.CardSymbol;
import logic.game.CardColor;
import logic.game.GameLogic;

public class NumberCard extends BaseCard {
    // Constructors
    public NumberCard(CardColor color, CardSymbol symbol) {
        super(color, symbol);
    }

    // Methods
    @Override
    public String toString() {
        return String.format("%s %s", this.getColor(), this.getSymbol());
    }

    @Override
    public boolean canPlay() {
        var topCard = GameLogic.getInstance().getTopCard();

        return this.getColor().equals(topCard.getColor())
                || this.getSymbol().equals(topCard.getSymbol());
    }

    @Override
    public String play() {
        var game = GameLogic.getInstance();

        game.setTopCard(this);
        game.getCurrentPlayerHand().remove(this);

        return null;
    }
}
