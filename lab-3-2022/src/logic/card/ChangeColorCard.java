package logic.card;

import logic.game.CardColor;
import logic.game.CardSymbol;
import logic.game.GameLogic;

public class ChangeColorCard extends EffectCard {
    // Constructors
    public ChangeColorCard() {
        super(null, CardSymbol.CHANGE_COLOR);
    }

    // Methods
    @Override
    public String toString() {
        if (this.getColor() == null) {
            return "CHANGE COLOR";
        }

        return String.format(
                "CHANGE COLOR (%s color selected)", this.getColor());
    }

    @Override
    public boolean canPlay() {
        return true;
    }

    @Override
    public String performEffect() {
        var playerHand = GameLogic.getInstance().getCurrentPlayerHand();

        var newColor = playerHand.stream()
                .filter(card -> !card.equals(this))
                .findFirst()
                .map(card -> card.getColor())
                .orElse(CardColor.RED);

        this.setColor(newColor);
        return String.format("Set color to %s", newColor);
    }
}
