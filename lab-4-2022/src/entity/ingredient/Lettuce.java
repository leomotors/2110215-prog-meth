package entity.ingredient;

import entity.base.Choppable;
import entity.base.Ingredient;

public class Lettuce extends Ingredient implements Choppable {
    // Fields and Constructors
    private boolean chopState;

    public Lettuce() {
        super("Lettuce");
        this.setEdible(true);
        this.chopState = false;
    }

    // Methods
    @Override
    public void chop() {
        if (!this.isChopped()) {
            this.chopState = true;
            this.setName("Chopped Lettuce");
        }
    }

    @Override
    public boolean isChopped() {
        return this.chopState;
    }
}
