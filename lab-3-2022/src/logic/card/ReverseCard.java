package logic.card;

import logic.game.CardColor;
import logic.game.CardSymbol;
import logic.game.GameLogic;

public class ReverseCard extends EffectCard {
    // Constructors
    public ReverseCard(CardColor color) {
        super(color, CardSymbol.REVERSE);
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

        var newDirection = game.getPlayDirection().getOpposite();
        game.setPlayDirection(newDirection);

        return String.format("Set direction to %s", newDirection);
    }
}
