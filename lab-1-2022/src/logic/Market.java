package logic;

import java.util.ArrayList;

public class Market {
    // Fields
    ArrayList<Item> allItems;

    // Constructors
    public Market() {
        this.setAllItems(new ArrayList<Item>());
    }

    public Market(ArrayList<Item> items) {
        this.setAllItems(new ArrayList<Item>());
        this.addAllItems(items);
    }

    // Methods
    @Override
    public String toString() {
        String out = "\n";
        for (int i = 0; i < allItems.size(); i++) {
            out += i + 1;
            out += ". ";
            out += allItems.get(i).toString();
            out += "\n";
        }
        return out;
    }

    public void addAllItems(ArrayList<Item> items) {
        for (var item : items) {
            if (!this.allItems.contains(item)) {
                this.allItems.add(item);
            }
        }
    }

    // Getters Setters
    public ArrayList<Item> getAllItems() {
        return this.allItems;
    }

    public void setAllItems(ArrayList<Item> allItems) {
        this.allItems = allItems;
    }
}
