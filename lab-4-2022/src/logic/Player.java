package logic;

import entity.base.Item;

public class Player {
    private Item holdingItem;

    public Player() {
        holdingItem = null;
    }

    public Item getHoldingItem() {
        return holdingItem;
    }

    public void setHoldingItem(Item holdingItem) {
        this.holdingItem = holdingItem;
    }

    public boolean isHandEmpty() {
        return holdingItem == null;
    }

    public Item placeItem() {
        Item currentItem = holdingItem;
        setHoldingItem(null);
        return currentItem;
    }
}
