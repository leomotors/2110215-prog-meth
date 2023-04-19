package entity.container;

import entity.base.Container;
import entity.base.Ingredient;
import logic.StringUtil;

public class Dish extends Container {
    // Fields and Constructors
    private int dirty;

    public Dish() {
        super("Dish", 4);
        this.setDirty(0);
    }

    public Dish(int dirty) {
        super("Dish", 4);
        this.setDirty(dirty);
    }

    // Methods
    public boolean isDirty() {
        return this.getDirty() > 0;
    }

    @Override
    public boolean verifyContent(Ingredient i) {
        return !this.isDirty() && i.isEdible();
    }

    public void setDirty(int dirty) {
        this.dirty = Math.max(0, dirty);

        if (this.isDirty()) {
            this.setName("Dirty Dish");
        } else {
            this.setName("Dish");
        }
    }

    public void clean(int amount) {
        this.setDirty(this.getDirty() - amount);
    }

    @Override
    public String toString() {
        if (this.isDirty()) {
            return StringUtil.formatNamePercentage(this.getName(),
                    this.getDirty());
        }

        return super.toString();
    }

    // Remaining Getters Setters
    public int getDirty() {
        return this.dirty;
    }
}
