package test.grader.ingredient;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import entity.ingredient.Meat;

class MeatTest {

    Meat test;

    @BeforeEach
    protected void setUpBeforeEachTest() {
        test = new Meat();

    }

    @Test
    void testConstructor() {
        assertEquals("Meat", test.getName());
        assertEquals(0, test.getCookedPercentage());
        assertEquals(false, test.isChopped());
        assertEquals(false, test.isEdible());
    }

    @Test
    void testCookNormal() {
        test.cook();
        assertEquals(10, test.getCookedPercentage());
        assertEquals(false, test.isEdible());

        while (test.getCookedPercentage() <= 50) {
            test.cook();
        }

        assertEquals(60, test.getCookedPercentage());
        assertEquals("Medium Rare Steak", test.getName());
        assertEquals(true, test.isEdible());

        while (test.getCookedPercentage() <= 80) {
            test.cook();
        }

        assertEquals(90, test.getCookedPercentage());
        assertEquals("Well Done Steak", test.getName());
        assertEquals(true, test.isEdible());

        while (test.getCookedPercentage() <= 100) {
            test.cook();
        }

        assertEquals(110, test.getCookedPercentage());
        assertEquals("Burnt Steak", test.getName());
        assertEquals(false, test.isEdible());
    }

    @Test
    void testCookChopped() {
        test.chop();
        assertEquals(false, test.isEdible());

        test.cook();
        assertEquals(15, test.getCookedPercentage());
        assertEquals(false, test.isEdible());

        while (test.getCookedPercentage() < 80) {
            test.cook();
        }

        assertEquals(90, test.getCookedPercentage());
        assertEquals("Cooked Burger", test.getName());
        assertEquals(true, test.isEdible());

        while (test.getCookedPercentage() < 100) {
            test.cook();
        }

        assertEquals(105, test.getCookedPercentage());
        assertEquals("Burnt Burger", test.getName());
        assertEquals(false, test.isEdible());
    }

    @Test
    void testChop() {
        test.chop();
        assertEquals("Minced Meat", test.getName());
        assertEquals(true, test.isChopped());
        assertEquals(false, test.isEdible());
    }

    @Test
    void testIsBurnt() {
        assertEquals(false, test.isBurnt());
        for (int i = 0; i <= 100; i += 10) {
            test.cook();
        }
        assertEquals(true, test.isBurnt());
    }

    @Test
    void testToString() {
        // Non-Chopped Version
        assertEquals("Meat (0%)", test.toString());

        test.cook();
        assertEquals("Raw Meat (10%)", test.toString());

        while (test.getCookedPercentage() <= 50) {
            test.cook();
        }

        assertEquals("Medium Rare Steak (60%)", test.toString());

        while (test.getCookedPercentage() <= 80) {
            test.cook();
        }

        assertEquals("Well Done Steak (90%)", test.toString());

        while (test.getCookedPercentage() <= 100) {
            test.cook();
        }

        assertEquals("Burnt Steak (110%)", test.toString());
    }

    @Test
    void testToStringChopped() {
        test.chop();
        assertEquals("Minced Meat (0%)", test.toString());

        test.cook();
        assertEquals("Raw Burger (15%)", test.toString());

        while (test.getCookedPercentage() <= 80) {
            test.cook();
        }

        assertEquals("Cooked Burger (90%)", test.toString());

        while (test.getCookedPercentage() <= 100) {
            test.cook();
        }

        assertEquals("Burnt Burger (105%)", test.toString());

    }

}
