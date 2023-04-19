package entity.counter;

import logic.LogicUtil;
import logic.Player;

public class Crate extends Counter {
    // Fields and Constructors
    private String myIngredient;

    public Crate(String s) {
        super(String.format("%s Crate", s));
        this.setMyIngredient(s);
    }

    // Methods
    @Override
    public void interact(Player p) {
        if (!p.isHandEmpty() || !this.isPlacedContentEmpty()) {
            super.interact(p);
            return;
        }

        try {
            var item = LogicUtil
                    .createIngredientFromName(this.getMyIngredient());
            p.setHoldingItem(item);
        } catch (Exception e) {
            // Do nothing, playerHand is already null
        }
    }

    // Remaining Getters Setters
    public String getMyIngredient() {
        return this.myIngredient;
    }

    public void setMyIngredient(String s) {
        this.myIngredient = s;
    }
}
