package entity.counter;

import entity.base.Container;
import entity.base.Ingredient;
import entity.base.Item;
import logic.Player;

public class Counter {

    private String name;
    private Item placedContent;

    public Counter() {
        setName("Counter");
    }

    public Counter(Item content) {
        setName("Counter");
        setPlacedContent(content);
    }

    protected Counter(String name) {
        setName(name);
    }

    public Item getPlacedContent() {
        return placedContent;
    }

    public void setPlacedContent(Item placedContent) {
        this.placedContent = placedContent;
    }

    public boolean isPlacedContentEmpty() {
        return placedContent == null;
    }

    public void interact(Player p) {
        if (isPlacedContentEmpty()) {
            // If the Counter is empty, place down the item from Player's hand
            placedContent = p.placeItem();
        } else {
            if (p.isHandEmpty()) {
                // If the Player's hand is empty, hold the current Item
                p.setHoldingItem(placedContent);
                placedContent = null;
            } else if (p.getHoldingItem() instanceof Container) {
                // If the Player is holding Container
                if (placedContent instanceof Ingredient) {
                    // If the content is Ingredient, put it into the Player's
                    // Container

                    Container c = (Container) p.getHoldingItem();
                    if (c.addContent((Ingredient) placedContent)) {
                        placedContent = null;
                    }
                } else if (placedContent instanceof Container) {
                    /*
                     * If the content is also Container, transfer the content
                     * from Player's hand to
                     * The current Container instead
                     */
                    Container c = (Container) p.getHoldingItem();
                    ((Container) placedContent).transferContent(c);
                }
            } else if (p.getHoldingItem() instanceof Ingredient
                    && placedContent instanceof Container) {
                /*
                 * If the player is holding Ingredient, and there is a Container
                 * on the counter,
                 * put said Ingredient into the Container instead
                 */
                Ingredient item = (Ingredient) p.getHoldingItem();
                if (((Container) placedContent).addContent(item)) {
                    p.setHoldingItem(null);
                }
            }
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        if (this.getPlacedContent() == null) {
            return this.getName() + " - Empty";
        }
        return this.getName() + " - " + this.getPlacedContent();
    }
}
