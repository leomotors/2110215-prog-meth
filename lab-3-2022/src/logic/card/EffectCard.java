package logic.card;

import logic.game.CardColor;
import logic.game.CardSymbol;
import logic.game.GameLogic;

public abstract class EffectCard extends BaseCard {
    // Constructors
    public EffectCard(CardColor color, CardSymbol symbol) {
        super(color, symbol);
    }

    // Methods
    public abstract String performEffect();

    @Override
    public String play() {
        var game = GameLogic.getInstance();

        game.setTopCard(this);
        game.getCurrentPlayerHand().remove(this);

        return this.performEffect();
    }
}
