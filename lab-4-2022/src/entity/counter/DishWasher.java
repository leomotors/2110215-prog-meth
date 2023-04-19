package entity.counter;

import entity.base.Updatable;
import entity.container.Dish;
import logic.Player;

public class DishWasher extends Counter implements Updatable {
    // Constructors
    public DishWasher() {
        super("Dish Washer");
    }

    // Methods
    @Override
    public void interact(Player p) {
        if (!this.isPlacedContentEmpty()) {
            super.interact(p);
            return;
        }

        if (p.getHoldingItem() instanceof Dish dish && dish.isDirty()) {
            super.interact(p);
        }
    }

    @Override
    public void update() {
        if (this.getPlacedContent() instanceof Dish dish) {
            dish.clean(15);
        }
    }
}
