package entity.ingredient;

import entity.base.Choppable;
import entity.base.Cookable;
import entity.base.Ingredient;
import logic.StringUtil;

public class Meat extends Ingredient implements Choppable, Cookable {
    // Fields and Constructors
    private boolean chopState;
    private int cookedPercentage;

    public Meat() {
        super("Meat");
        this.chopState = false;
        this.cookedPercentage = 0;
    }

    // Methods
    @Override
    public void chop() {
        if (!this.isChopped() && this.cookedPercentage == 0) {
            this.chopState = true;
            this.setName("Minced Meat");
        }
    }

    @Override
    public boolean isChopped() {
        return this.chopState;
    }

    @Override
    public void cook() {
        this.setCookedPercentage(
                this.getCookedPercentage() + (this.isChopped() ? 15 : 10));
        var percentage = this.getCookedPercentage();

        if (!this.isChopped()) {
            if (percentage <= 50) {
                this.setCookedStatus("Raw Meat", false);
            } else if (percentage <= 80) {
                this.setCookedStatus("Medium Rare Steak", true);
            } else if (percentage <= 100) {
                this.setCookedStatus("Well Done Steak", true);
            } else {
                this.setCookedStatus("Burnt Steak", false);
            }
        } else {
            if (percentage <= 80) {
                this.setCookedStatus("Raw Burger", false);
            } else if (percentage <= 100) {
                this.setCookedStatus("Cooked Burger", true);
            } else {
                this.setCookedStatus("Burnt Burger", false);
            }
        }

    }

    private void setCookedStatus(String name, boolean isEdible) {
        this.setName(name);
        this.setEdible(isEdible);
    }

    @Override
    public boolean isBurnt() {
        return this.cookedPercentage > 100;
    }

    @Override
    public String toString() {
        return StringUtil.formatNamePercentage(this.getName(),
                this.cookedPercentage);
    }

    // Getters Setters
    public int getCookedPercentage() {
        return this.cookedPercentage;
    }

    public void setCookedPercentage(int cookedPercentage) {
        this.cookedPercentage = Math.max(0, cookedPercentage);
    }
}
