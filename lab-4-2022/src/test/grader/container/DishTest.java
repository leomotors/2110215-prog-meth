package test.grader.container;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import entity.container.Dish;
import entity.ingredient.Bread;
import entity.ingredient.Meat;

class DishTest {

    Dish test;

    @BeforeEach
    protected void setUpBeforeEachTest() {
        test = new Dish();
    }

    @Test
    void testNoParameterConstructor() {
        assertEquals("Dish", test.getName());
        assertEquals(4, test.getCapacity());
        assertEquals(4, test.getMaxCapacity());
        assertEquals(0, test.getContent().size());
        assertEquals(0, test.getDirty());
    }

    @Test
    void testParameterConstructor() {
        test = new Dish(22);
        assertEquals("Dirty Dish", test.getName());
        assertEquals(4, test.getCapacity());
        assertEquals(4, test.getMaxCapacity());
        assertEquals(0, test.getContent().size());
        assertEquals(22, test.getDirty());

        test = new Dish(-22);
        assertEquals("Dish", test.getName());
        assertEquals(4, test.getCapacity());
        assertEquals(4, test.getMaxCapacity());
        assertEquals(0, test.getContent().size());
        assertEquals(0, test.getDirty());
    }

    @Test
    void testVerifyContent() {
        assertEquals(true, test.verifyContent(new Bread()));
    }

    @Test
    void testVerifyContentInedible() {
        assertEquals(false, test.verifyContent(new Meat()));
    }

    @Test
    void testVerifyContentDirty() {
        test.setDirty(10);
        assertEquals(false, test.verifyContent(new Bread()));
    }

    @Test
    void testIsDirty() {
        assertEquals(false, test.isDirty());
        test.setDirty(1);
        assertEquals(true, test.isDirty());
    }

    @Test
    void testClean() {
        test.setDirty(100);
        assertEquals(100, test.getDirty());
        assertEquals("Dirty Dish", test.getName());

        test.clean(50);
        assertEquals(50, test.getDirty());

        test.clean(70);
        assertEquals(0, test.getDirty());
        assertEquals("Dish", test.getName());
    }

    @Test
    void testToString() {
        assertEquals("Dish []", test.toString());
    }

    @Test
    void testToStringDirty() {
        test.setDirty(100);
        assertEquals("Dirty Dish (100%)", test.toString());
    }
}
