package test.grader.ingredient;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import entity.ingredient.Lettuce;

class LettuceTest {

    Lettuce test;

    @BeforeEach
    protected void setUpBeforeEachTest() {
        test = new Lettuce();

    }

    @Test
    void testConstructor() {
        assertEquals("Lettuce", test.getName());
        assertEquals(false, test.isChopped());
        assertEquals(true, test.isEdible());
    }

    @Test
    void testChop() {
        test.chop();
        assertEquals("Chopped Lettuce", test.getName());
        assertEquals(true, test.isChopped());
        assertEquals(true, test.isEdible());
    }

}
