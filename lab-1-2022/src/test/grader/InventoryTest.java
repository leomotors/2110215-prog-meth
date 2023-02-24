package test.grader;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.Test;

import exception.NameBlankException;
import logic.Inventory;
import logic.Item;
import logic.ItemCounter;

public class InventoryTest {

    @Test
    public void testConstructor() {
        Inventory inv1 = new Inventory("testName1");
        assertEquals("testName1", inv1.getPlayerName());
        assertEquals(0, inv1.getMoney());
        assertEquals(0, inv1.getItems().size());

        Inventory inv2 = new Inventory("testName2", 10);
        assertEquals("testName2", inv2.getPlayerName());
        assertEquals(10, inv2.getMoney());
        assertEquals(0, inv2.getItems().size());

        try {
            Item item1 = new Item("item1");
            Item item2 = new Item("item2");
            ItemCounter ic1 = new ItemCounter(item1);
            ItemCounter ic2 = new ItemCounter(item2);
            ArrayList<ItemCounter> items = new ArrayList<ItemCounter>();
            items.add(ic1);
            items.add(ic2);

            Inventory inv3 = new Inventory("testName3", 15, items);
            assertEquals("testName3", inv3.getPlayerName());
            assertEquals(15, inv3.getMoney());
            assertEquals(2, inv3.getItems().size());
            assertTrue(inv3.getItems().get(0).getItem().equals(item1));
            assertTrue(inv3.getItems().get(1).getItem().equals(item2));
            assertEquals(items, inv3.getItems());

        } catch (NameBlankException e) {
            fail();
        }
    }

    @Test
    public void testBadConstructor() {
        Inventory inv1 = new Inventory("");
        assertEquals("Untitled Player", inv1.getPlayerName());

        Inventory inv2 = new Inventory("", -10);
        assertEquals("Untitled Player", inv2.getPlayerName());
        assertEquals(0, inv2.getMoney());

        ArrayList<ItemCounter> a = new ArrayList<ItemCounter>();
        Inventory inv3 = new Inventory("", -10, a);
        assertEquals("Untitled Player", inv3.getPlayerName());
        assertEquals(0, inv2.getMoney());
        assertEquals(a, inv2.getItems());

    }

    // @Test //already provided -> do not need test
    // public void testToString() {
    // try {
    // Item item1 = new Item("item1", 100);
    // Item item2 = new Item("item2", 50);
    // ItemCounter ic1 = new ItemCounter(item1, 1);
    // ItemCounter ic2 = new ItemCounter(item2, 2);
    // ArrayList<ItemCounter> items = new ArrayList<ItemCounter>();
    // items.add(ic1);
    // items.add(ic2);
    //
    // Inventory inv3 = new Inventory("testName3", 15, items);
    // String expected = "\n";
    // for (int i=0; i<inv3.getItems().size(); i++) {
    // expected += i+1;
    // expected += ". ";
    // expected += inv3.getItems().get(i).toString();
    // expected += "\n";
    // }
    // assertTrue(expected.equals(inv3.toString()));
    // } catch (NameBlankException e) {
    // fail();
    // }
    // }

    @Test
    public void testExistsInInventory() {
        try {
            Item item1 = new Item("item1");
            Item item2 = new Item("item2");
            Item itemNotIn = new Item("NotIn");
            ItemCounter ic1 = new ItemCounter(item1);
            ItemCounter ic2 = new ItemCounter(item2);
            ic2.setCount(1);
            ArrayList<ItemCounter> items = new ArrayList<ItemCounter>();
            items.add(ic1);
            items.add(ic2);

            Inventory inv1 = new Inventory("testName1", 15, items);

            assertTrue(inv1.existsInInventory(item1));
            assertTrue(inv1.existsInInventory(item2));
            assertFalse(inv1.existsInInventory(itemNotIn));

        } catch (NameBlankException e) {
            fail();
        }
    }

    @Test
    public void testAddItem() {
        try {
            Item item1 = new Item("item1");
            Item item2 = new Item("item2");
            Item item3 = new Item("item3");
            ItemCounter ic1 = new ItemCounter(item1);
            ItemCounter ic2 = new ItemCounter(item2);
            ArrayList<ItemCounter> items = new ArrayList<ItemCounter>();
            items.add(ic1);
            items.add(ic2);

            Inventory inv1 = new Inventory("testName1", 105, items);
            inv1.addItem(item2, 10);
            assertEquals(11, inv1.getItems().get(1).getCount());
            assertEquals(2, inv1.getItems().size());

            inv1.addItem(item3, 15);
            assertEquals(15, inv1.getItems().get(2).getCount());
            assertEquals(3, inv1.getItems().size());

        } catch (NameBlankException e) {
            fail();
        }
    }

    // @Test The method removeItem() is already provided
    // public void testRemoveItem() {
    // try {
    // Item item1 = new Item("item1");
    // Item item2 = new Item("item2");
    // Item item3 = new Item("item3");
    // ItemCounter ic1 = new ItemCounter(item1, 100);
    // ItemCounter ic2 = new ItemCounter(item2, 200);
    // ArrayList<ItemCounter> items = new ArrayList<ItemCounter>();
    // items.add(ic1);
    // items.add(ic2);
    //
    // Inventory inv1 = new Inventory("testName1", 105, items);
    // inv1.removeItem(item2, 10);
    // assertEquals(190, inv1.getItems().get(1).getCount());
    // assertEquals(2, inv1.getItems().size());
    //
    // inv1.removeItem(item1, 2000);
    // assertEquals(1, inv1.getItems().size());
    // assertFalse(inv1.existsInInventory(item1));
    //
    // inv1.removeItem(item3, 1);
    // assertEquals(1, inv1.getItems().size());
    //
    // } catch (NameBlankException e) {
    // fail();
    // }
    // }

    @Test
    public void testSetPlayerName() {
        Inventory inv1 = new Inventory("x");
        inv1.setPlayerName("test");
        assertTrue(inv1.getPlayerName().equals("test"));
    }

    @Test
    public void testSetMoney() {
        Inventory inv1 = new Inventory("x");
        inv1.setMoney(100);
        assertEquals(100, inv1.getMoney());

        inv1.setMoney(-1);
        assertEquals(0, inv1.getMoney());
    }

    @Test
    public void testSetItems() {
        Inventory inv1 = new Inventory("testName1");
        try {
            Item item1 = new Item("item1");
            Item item2 = new Item("item2");
            ItemCounter ic1 = new ItemCounter(item1);
            ItemCounter ic2 = new ItemCounter(item2);
            ArrayList<ItemCounter> items = new ArrayList<ItemCounter>();
            items.add(ic1);
            items.add(ic2);

            inv1.setItems(items);
            assertTrue(inv1.getItems().equals(items));
        } catch (NameBlankException e) {
            fail();
        }
    }

}
