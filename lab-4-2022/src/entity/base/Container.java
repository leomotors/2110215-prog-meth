package entity.base;

import java.util.ArrayList;

public abstract class Container extends Item {

    private ArrayList<Ingredient> content;
    private int maxCapacity;
    private int capacity;

    protected Container(String name, int capacity) {
        super(name);
        content = new ArrayList<Ingredient>();
        maxCapacity = capacity;
        setCapacity(capacity);
    }

    public abstract boolean verifyContent(Ingredient i);

    public boolean addContent(Ingredient i) {
        if (verifyContent(i)) {
            if (capacity <= 0) {
                return false;
            }
            content.add(i);
            capacity -= 1;
            return true;
        }
        return false;
    }

    public ArrayList<Ingredient> getContent() {
        return content;
    }

    public void clearContent() {
        content.clear();
        capacity = maxCapacity;
    }

    public boolean isEmpty() {
        return capacity == maxCapacity;
    }

    public void transferContent(Container c) {
        ArrayList<Ingredient> otherContent = c.getContent();

        if (otherContent.size() > capacity) {
            return;
        } // Overloaded, cannot transfer

        for (Ingredient i : otherContent) {
            if (!this.verifyContent(i)) {
                // One of the element is not compatible, do not transfer
                return;
            }
            this.addContent(i);
        }
        c.clearContent();

    }

    public void setContent(ArrayList<Ingredient> content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return this.getName() + " " + content;
    }

    public int getMaxCapacity() {
        return maxCapacity;
    }

    public void setMaxCapacity(int maxCapacity) {
        this.maxCapacity = maxCapacity;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity > maxCapacity ? maxCapacity : capacity;
    }

}
