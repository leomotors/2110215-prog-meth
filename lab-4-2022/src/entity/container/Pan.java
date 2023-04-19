package entity.container;

import entity.base.Container;
import entity.base.Cookable;
import entity.base.Ingredient;

public class Pan extends Container {
    // Constructors
    public Pan() {
        super("Pan", 1);
    }

    // Methods
    @Override
    public boolean verifyContent(Ingredient i) {
        return i instanceof Cookable;
    }

    public void cook() {
        if (!this.isEmpty()) {
            this.getContent().stream().forEach(ingredient -> {
                if (ingredient instanceof Cookable cookable) {
                    cookable.cook();
                }
            });
        }
    }
}
