package logic;

public class ItemCounter {
    // Fields
    private Item item;
    private int count;

    static final int MIN_ITEM_COUNT = 0;
    static final int MIN_INITIAL_ITEM_COUNT = 1;

    // Constructors
    public ItemCounter(Item item) {
        this.setItem(item);
        this.setCount(MIN_INITIAL_ITEM_COUNT);
    }

    public ItemCounter(Item item, int count) {
        this.setItem(item);
        this.setCount(Math.max(MIN_INITIAL_ITEM_COUNT, count));
    }

    @Override
    public String toString() {
        return String.format("%s x%d", this.getItem(), this.getCount());
    }

    // Getters Setters
    public Item getItem() {
        return this.item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public int getCount() {
        return this.count;
    }

    public void setCount(int count) {
        this.count = Math.max(MIN_ITEM_COUNT, count);
    }
}
