package logic;

import exception.NameBlankException;

public class Item {
    // Fields
    private String itemName;
    private int price;

    static final int MIN_PRICE = 0;

    // Constructors
    public Item(String itemName) throws NameBlankException {
        this.setItemName(itemName);
        this.setPrice(MIN_PRICE);
    }

    public Item(String itemName, int price) throws NameBlankException {
        this.setItemName(itemName);
        this.setPrice(price);
    }

    // Methods
    @Override
    public boolean equals(Object other) {
        return other instanceof Item && this.getItemName().equals(((Item) other).getItemName());
    }

    @Override
    public String toString() {
        return String.format("%s $%d", this.getItemName(), this.getPrice());
    }

    // Getters Setters
    public String getItemName() {
        return this.itemName;
    }

    public void setItemName(String itemName) throws NameBlankException {
        if (itemName.isBlank()) {
            throw new NameBlankException();
        }

        this.itemName = itemName;
    }

    public int getPrice() {
        return this.price;
    }

    public void setPrice(int price) {
        this.price = Math.max(MIN_PRICE, price);
    }
}
