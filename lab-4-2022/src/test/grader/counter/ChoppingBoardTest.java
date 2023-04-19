package test.grader.counter;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import entity.container.Dish;
import entity.counter.ChoppingBoard;
import entity.ingredient.Bread;
import entity.ingredient.Lettuce;
import test.grader.counter.base.BaseCounterTest;

class ChoppingBoardTest extends BaseCounterTest {

    ChoppingBoard test;

    @BeforeEach
    protected void setUpBeforeEachTest() {
        super.setUpBeforeEachTest();
        test = new ChoppingBoard();
    }

    @Override
    @Test
    protected void testConstructor() {
        assertEquals("Chopping Board", test.getName());
        assertEquals(null, test.getPlacedContent());
    }

    @Override
    @Test
    protected void testInteract() {
        p.setHoldingItem(new Bread());
        test.interact(p);
        assertEquals(true, p.isHandEmpty());
        assertEquals("Bread", test.getPlacedContent().getName());
    }

    @Test
    protected void testInteractChoppable() {
        p.setHoldingItem(new Lettuce());
        test.interact(p);
        assertEquals(true, p.isHandEmpty());
        assertEquals("Chopped Lettuce", test.getPlacedContent().getName());
    }

    @Test
    protected void testInteractNonIngredient() {
        p.setHoldingItem(new Dish());
        test.interact(p);
        assertEquals(false, p.isHandEmpty());
        assertEquals("Dish", p.getHoldingItem().getName());
        assertEquals(null, test.getPlacedContent());
    }

}
