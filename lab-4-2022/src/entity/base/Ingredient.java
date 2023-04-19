package entity.base;

public abstract class Ingredient extends Item {

    private boolean isEdible;

    protected Ingredient(String name) {
        super(name);
        setEdible(false);
    }

    public boolean isEdible() {
        return isEdible;
    }

    public void setEdible(boolean isEdible) {
        this.isEdible = isEdible;
    }
}
