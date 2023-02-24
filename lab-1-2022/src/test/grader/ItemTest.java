package test.grader;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import exception.NameBlankException;
import logic.Item;

public class ItemTest {

    @Test
    void testConstructor() {
        Item item1;
        try {
            item1 = new Item("Garbage");
            Item item2 = new Item("Orb", 500);

            assertEquals("Garbage", item1.getItemName());
            assertEquals(0, item1.getPrice());

            assertEquals("Orb", item2.getItemName());
            assertEquals(500, item2.getPrice());
        } catch (NameBlankException e) {
            fail();
        }
    }

    @Test
    void testBadConstructor() {
        try {
            Item item1 = new Item("King", -100);
            assertEquals("King", item1.getItemName());
            assertEquals(0, item1.getPrice());

            Exception exception1 = assertThrows(NameBlankException.class, () -> {
                Item item2 = new Item("", 100);
            });
            String expectedMessage = "Item name cannot be blank! This item will not be added.";
            String actualMessage = exception1.getMessage();
            // assertTrue(actualMessage.contains(expectedMessage));
            assertTrue(expectedMessage.equals(actualMessage));

            Exception exception2 = assertThrows(NameBlankException.class, () -> {
                Item item3 = new Item(" ");
            });
            expectedMessage = "Item name cannot be blank! This item will not be added.";
            actualMessage = exception2.getMessage();
            // assertTrue(actualMessage.contains(expectedMessage));
            assertTrue(expectedMessage.equals(actualMessage));
        } catch (NameBlankException e) {
            fail();
        }
    }

    @Test
    void testEquals() {
        try {
            Item item1 = new Item("Scroll", 50);
            Item item2 = new Item("Scroll", 500);
            Item item3 = new Item("Resurrection Scroll", 500);

            assertTrue(item1.equals(item2));
            assertTrue(item2.equals(item1));
            assertFalse(item1.equals(item3));
            assertFalse(item3.equals(item1));
            assertFalse(item2.equals(item3));
            assertFalse(item3.equals(item2));

            Object a = new Object();

            assertFalse(item1.equals(a)); // equals method with Object parameter
        } catch (NameBlankException e) {
            fail();
        }
    }

    @Test
    void testToString() {
        try {
            Item item1 = new Item("Golden Shard", 1000);
            assertEquals("Golden Shard $1000", item1.toString());
        } catch (NameBlankException e) {
            fail();
        }
    }

    @Test
    void testSetItemName() {
        try {
            Item item1 = new Item("item1");
            item1.setItemName("Magic Wand");
            assertEquals("Magic Wand", item1.getItemName());

            Exception exception = assertThrows(NameBlankException.class, () -> {
                Item item2 = new Item("item2");
                item2.setItemName("   ");
            });
            String expectedMessage = "Item name cannot be blank! This item will not be added.";
            String actualMessage = exception.getMessage();
            assertTrue(actualMessage.equals(expectedMessage));
        } catch (NameBlankException e) {
            fail();
        }
    }

    @Test
    void testSetPrice() {
        try {
            Item item1 = new Item("Ruby", 8);
            assertEquals(8, item1.getPrice());

            item1.setPrice(100);
            assertEquals(100, item1.getPrice());

            item1.setPrice(-100);
            assertEquals(0, item1.getPrice());
        } catch (NameBlankException e) {
            fail();
        }
    }

}
