package test.grader;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import exception.NameBlankException;
import logic.Item;
import logic.ItemCounter;

public class ItemCounterTest {

    @Test
    void testConstructor() {

        try {
            Item item1 = new Item("Tester1", 999);
            ItemCounter ic1 = new ItemCounter(item1);
            assertEquals(1, ic1.getCount());
            assertTrue(ic1.getItem().equals(item1));

            Item item2 = new Item("Tester2", 19);
            ItemCounter ic2 = new ItemCounter(item2, 4);
            assertEquals(4, ic2.getCount());
            assertTrue(ic2.getItem().equals(item2));

        } catch (NameBlankException e) {
            fail();
        }
    }

    @Test
    void testBadConstructor() {
        try {
            Item item1 = new Item("Tester1", 999);
            ItemCounter ic1 = new ItemCounter(item1, -200);
            assertEquals(1, ic1.getCount());
            assertTrue(ic1.getItem().equals(item1));

            Item item2 = new Item("Tester2", 999);
            ItemCounter ic2 = new ItemCounter(item2, -1);
            assertEquals(1, ic2.getCount());
            assertTrue(ic2.getItem().equals(item2));
        } catch (NameBlankException e) {
            fail();
        }
    }

    @Test
    void testSetItem() {
        try {
            Item item0 = new Item("Tester0", 10);
            Item item1 = new Item("Tester1", 999);
            ItemCounter ic1 = new ItemCounter(item1, 11);
            ic1.setItem(item0);
            assertTrue(ic1.getItem().equals(item0));

            Item item2 = new Item("Tester2", 999);
            ItemCounter ic2 = new ItemCounter(item2, 1);
            ic2.setItem(item2);
            assertTrue(ic2.getItem().equals(item2));

        } catch (NameBlankException e) {
            fail();
        }
    }

    @Test
    void testSetCount() {
        try {
            Item item1 = new Item("Tester1", 999);
            ItemCounter ic1 = new ItemCounter(item1, 600);
            ic1.setCount(200);
            assertEquals(200, ic1.getCount());

            Item item2 = new Item("Tester2", 999);
            ItemCounter ic2 = new ItemCounter(item2, 40);
            ic2.setCount(-1);
            assertEquals(0, ic2.getCount());

        } catch (NameBlankException e) {
            fail();
        }
    }

    @Test
    void testToString() {
        Item item1;
        try {
            item1 = new Item("Tester1", 999);
            ItemCounter ic1 = new ItemCounter(item1, 600);
            String actual = ic1.toString();
            String expected = "Tester1 $999 x600";
            assertTrue(actual.equals(expected));

        } catch (NameBlankException e) {
            // TODO Auto-generated catch block
            fail();
        }

    }

}
