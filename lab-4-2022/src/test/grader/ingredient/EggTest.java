package test.grader.ingredient;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import entity.ingredient.Egg;

class EggTest {

    Egg test;

    @BeforeEach
    protected void setUpBeforeEachTest() {
        test = new Egg();

    }

    @Test
    void testConstructor() {
        assertEquals("Egg", test.getName());
        assertEquals(0, test.getCookedPercentage());
    }

    @Test
    void testCook() {
        test.cook();
        assertEquals(12, test.getCookedPercentage());
        assertEquals("Raw Egg", test.getName());
        assertFalse(test.isEdible());

        while (test.getCookedPercentage() < 50) {
            test.cook();
        }

        assertEquals(60, test.getCookedPercentage());
        assertEquals("Sunny Side Egg", test.getName());
        assertTrue(test.isEdible());

        while (test.getCookedPercentage() < 80) {
            test.cook();
        }

        assertEquals(84, test.getCookedPercentage());
        assertEquals("Fried Egg", test.getName());
        assertTrue(test.isEdible());

        while (test.getCookedPercentage() < 100) {
            test.cook();
        }

        assertEquals(108, test.getCookedPercentage());
        assertEquals("Burnt Egg", test.getName());
        assertFalse(test.isEdible());
    }

    @Test
    void testIsBurnt() {
        assertEquals(false, test.isBurnt());
        for (int i = 0; i <= 100; i += 12) {
            test.cook();
        }
        assertEquals(true, test.isBurnt());
    }

    @Test
    void testToString() {
        assertEquals("Egg (0%)", test.toString());

        test.cook();
        assertEquals("Raw Egg (12%)", test.toString());

        while (test.getCookedPercentage() < 50) {
            test.cook();
        }
        assertEquals("Sunny Side Egg (60%)", test.toString());

        while (test.getCookedPercentage() < 80) {
            test.cook();
        }
        assertEquals("Fried Egg (84%)", test.toString());

        while (test.getCookedPercentage() < 100) {
            test.cook();
        }
        assertEquals("Burnt Egg (108%)", test.toString());

    }

}
