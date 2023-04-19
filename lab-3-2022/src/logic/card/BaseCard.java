package logic.card;

import logic.game.CardSymbol;
import logic.game.CardColor;

public abstract class BaseCard {
    // Fields
    private CardColor color;
    private CardSymbol symbol;

    // Constructors
    public BaseCard(CardColor color, CardSymbol symbol) {
        this.setColor(color);
        this.setSymbol(symbol);
    }

    // Methods
    public abstract String toString();

    public abstract boolean canPlay();

    public abstract String play();

    // Getters Setters
    public CardColor getColor() {
        return this.color;
    }

    public void setColor(CardColor color) {
        this.color = color;
    }

    public CardSymbol getSymbol() {
        return this.symbol;
    }

    public void setSymbol(CardSymbol symbol) {
        this.symbol = symbol;
    }
}
