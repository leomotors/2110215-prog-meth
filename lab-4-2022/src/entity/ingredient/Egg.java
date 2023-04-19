package entity.ingredient;

import entity.base.Cookable;
import entity.base.Ingredient;
import logic.StringUtil;

public class Egg extends Ingredient implements Cookable {
    // Fields and Constructors
    private int cookedPercentage;

    public Egg() {
        super("Egg");
        this.cookedPercentage = 0;
    }

    // Methods
    @Override
    public void cook() {
        this.setCookedPercentage(this.getCookedPercentage() + 12);
        var percentage = this.getCookedPercentage();

        if (percentage <= 50) {
            this.setCookedStatus("Raw Egg", false);
        } else if (percentage <= 80) {
            this.setCookedStatus("Sunny Side Egg", true);
        } else if (percentage <= 100) {
            this.setCookedStatus("Fried Egg", true);
        } else {
            this.setCookedStatus("Burnt Egg", false);
        }
    }

    private void setCookedStatus(String name, boolean isEdible) {
        this.setName(name);
        this.setEdible(isEdible);
    }

    @Override
    public boolean isBurnt() {
        return this.getCookedPercentage() > 100;
    }

    @Override
    public String toString() {
        return StringUtil.formatNamePercentage(this.getName(),
                this.getCookedPercentage());
    }

    // Getters Setters
    public int getCookedPercentage() {
        return this.cookedPercentage;
    }

    public void setCookedPercentage(int cookedPercentage) {
        this.cookedPercentage = Math.max(0, cookedPercentage);
    }
}
