package test.grader.counter;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import entity.container.Dish;
import entity.counter.DishWasher;
import entity.ingredient.Bread;
import test.grader.counter.base.BaseCounterTest;

class DishWasherTest extends BaseCounterTest {

    DishWasher test;

    @BeforeEach
    protected void setUpBeforeEachTest() {
        super.setUpBeforeEachTest();
        test = new DishWasher();
    }

    @Override
    @Test
    protected void testConstructor() {
        assertEquals("Dish Washer", test.getName());
        assertEquals(null, test.getPlacedContent());
    }

    @Override
    @Test
    protected void testInteract() {
        assertEquals(true, p.isHandEmpty());
        test.interact(p);
        assertEquals(true, p.isHandEmpty());
    }

    @Test
    protected void testInteractNonDish() {
        p.setHoldingItem(new Bread());
        assertEquals(false, p.isHandEmpty());
        test.interact(p);
        assertEquals(false, p.isHandEmpty());
        assertEquals("Bread", p.getHoldingItem().getName());
    }

    @Test
    protected void testInteractDish() {
        p.setHoldingItem(new Dish());
        assertEquals(false, p.isHandEmpty());
        test.interact(p);
        assertEquals(false, p.isHandEmpty());
        assertEquals("Dish", p.getHoldingItem().getName());
    }

    @Test
    protected void testInteractDirtyDish() {
        Dish d = new Dish();
        d.setDirty(100);
        p.setHoldingItem(d);
        assertEquals(false, p.isHandEmpty());
        test.interact(p);
        assertEquals(true, p.isHandEmpty());
        assertEquals("Dirty Dish", test.getPlacedContent().getName());
    }

    @Test
    void testUpdate() {
        Dish d = new Dish(100);
        test.setPlacedContent(d);

        assertEquals(100, d.getDirty());
        test.update();
        assertEquals(85, d.getDirty());
    }

}
