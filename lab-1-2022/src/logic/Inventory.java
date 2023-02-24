package logic;

import java.util.ArrayList;

public class Inventory {
    // Fields
    private String playerName;
    private int money;
    private ArrayList<ItemCounter> items;

    static final int MIN_MONEY = 0;

    // Constructors
    public Inventory(String playerName) {
        this.setPlayerName(playerName);
        this.setMoney(MIN_MONEY);
        this.setItems(new ArrayList<ItemCounter>());
    }

    public Inventory(String playerName, int money) {
        this.setPlayerName(playerName);
        this.setMoney(money);
        this.setItems(new ArrayList<ItemCounter>());
    }

    public Inventory(String playerName, int money, ArrayList<ItemCounter> items) {
        this.setPlayerName(playerName);
        this.setMoney(money);
        this.setItems(items);
    }

    // Methods
    @Override
    public String toString() {
        if (items.size() == 0) {
            return "EMPTY INVENTORY";
        }

        String out = "\n";
        for (int i = 0; i < items.size(); i++) {
            out += i + 1;
            out += ". ";
            out += items.get(i).toString();
            out += "\n";
        }

        return out;
    }

    public boolean existsInInventory(Item item) {
        return this.items.stream().anyMatch(
                it -> it.getItem().equals(item) && it.getCount() >= 1);
    }

    public void addItem(Item newItem, int count) {
        if (count <= 0)
            return;

        ItemCounter ic = this.items.stream().filter(
                it -> it.getItem().equals(newItem)).findFirst().orElse(null);

        if (ic == null) {
            this.items.add(new ItemCounter(newItem, count));
        } else {
            ic.setCount(ic.getCount() + count);
        }
    }

    public void removeItem(Item toRemove, int count) {
        if (count <= 0)
            return;

        ItemCounter removeIfNeg = null;

        for (ItemCounter ic : items) {
            if (ic.getItem().equals(toRemove)) {
                // Remove the card equal to count.
                ic.setCount(ic.getCount() - count);
                removeIfNeg = ic;
            }
        }

        // If removeIfNeg isn't null (meaning something got removed) then we need to
        // check if it is negative.
        if (removeIfNeg != null) {
            // If it goes into the negative, then remove this entry from the deck entirely.
            // You cannot modify a for loop while it's inside, so this has to be done
            // outside.
            if (removeIfNeg.getCount() <= 0) {
                items.remove(removeIfNeg);
            }
        }

    }

    // Getters Setters
    public String getPlayerName() {
        return this.playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName.isBlank() ? "Untitled Player" : playerName;
    }

    public int getMoney() {
        return this.money;
    }

    public void setMoney(int money) {
        this.money = Math.max(MIN_MONEY, money);
    }

    public ArrayList<ItemCounter> getItems() {
        return this.items;
    }

    public void setItems(ArrayList<ItemCounter> items) {
        this.items = items;
    }
}
