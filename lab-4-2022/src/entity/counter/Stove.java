package entity.counter;

import entity.base.Item;
import entity.base.Updatable;
import entity.container.Pan;
import logic.Player;

public class Stove extends Counter implements Updatable {
    // Constructors
    public Stove() {
        super("Stove");
    }

    public Stove(Item content) {
        super("Stove");
        this.setPlacedContent(content);
    }

    // Methods
    @Override
    public void interact(Player p) {
        if (!this.isPlacedContentEmpty()) {
            super.interact(p);
            return;
        }

        if (p.getHoldingItem() instanceof Pan pan) {
            super.interact(p);
        }
    }

    @Override
    public void update() {
        if (this.getPlacedContent() instanceof Pan pan) {
            pan.cook();
        }
    }
}
