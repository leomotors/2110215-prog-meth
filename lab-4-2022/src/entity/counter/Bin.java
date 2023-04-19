package entity.counter;

import entity.base.Container;
import entity.base.Ingredient;
import logic.Player;

public class Bin extends Counter {
    // Constructors
    public Bin() {
        super("Bin");
    }

    // Methods
    @Override
    public void interact(Player p) {
        var playerHand = p.getHoldingItem();

        if (p.isHandEmpty()) {
            return;
        } else if (playerHand instanceof Ingredient) {
            p.setHoldingItem(null);
        } else if (playerHand instanceof Container container) {
            container.clearContent();
        }
    }
}
