package entity.counter;

import entity.base.Choppable;
import entity.base.Ingredient;
import logic.Player;

public class ChoppingBoard extends Counter {
    // Constructors
    public ChoppingBoard() {
        super("Chopping Board");
    }

    // Methods
    @Override
    public void interact(Player p) {
        if (!this.isPlacedContentEmpty()) {
            super.interact(p);
            return;
        }

        if (p.getHoldingItem() instanceof Ingredient ingredient) {
            super.interact(p);

            if (ingredient instanceof Choppable choppable) {
                choppable.chop();
            }
        }
    }
}
