package test.grader;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import exception.NameBlankException;
import logic.Item;
import logic.Market;

public class MarketTest {

    @Test
    void testConstructor() {
        Market m = new Market();
        assertEquals(0, m.getAllItems().size());

        Item item1, item2, item3, item4;
        try {
            item1 = new Item("Boomstick", 555);
            item2 = new Item("Boomstick", 444);
            item3 = new Item("Sword", 333);
            item4 = new Item("Sword", 222);

            ArrayList<Item> items = new ArrayList<Item>();
            items.add(item1);
            items.add(item2);
            items.add(item3);
            items.add(item4);
            Market m2 = new Market(items);
            assertEquals(2, m2.getAllItems().size());
            assertEquals(555, m2.getAllItems().get(0).getPrice());
            assertEquals("Boomstick", m2.getAllItems().get(0).getItemName());
            assertEquals(333, m2.getAllItems().get(1).getPrice());
            assertEquals("Sword", m2.getAllItems().get(1).getItemName());

        } catch (NameBlankException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    @Test
    void testAddAllItems() {
        Market market = new Market();
        Market market2 = new Market();
        try {
            Item item1 = new Item("Boomstick", 2420);
            Item item2 = new Item("Boomstick", 1111);
            ArrayList<Item> items = new ArrayList<Item>();
            items.add(item1);
            market.addAllItems(items);

            assertEquals(1, market.getAllItems().size());
            assertTrue(market.getAllItems().get(0).equals(item1));
            items.clear();

            items.add(item2);
            market.addAllItems(items);
            assertEquals(1, market.getAllItems().size());
            assertEquals(2420, market.getAllItems().get(0).getPrice());
            items.clear();

            items.add(item2);
            items.add(item1);
            market2.addAllItems(items);
            assertEquals(1, market2.getAllItems().size());
            assertEquals(1111, market2.getAllItems().get(0).getPrice());

        } catch (NameBlankException e) {
            fail();
        }
    }

}
