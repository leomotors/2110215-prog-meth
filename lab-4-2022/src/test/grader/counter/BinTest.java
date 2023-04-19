package test.grader.counter;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import entity.container.Pan;
import entity.counter.Bin;
import entity.ingredient.Bread;
import entity.ingredient.Meat;
import test.grader.counter.base.BaseCounterTest;

class BinTest extends BaseCounterTest {

    Bin test;

    @BeforeEach
    protected void setUpBeforeEachTest() {
        super.setUpBeforeEachTest();
        test = new Bin();
    }

    @Override
    @Test
    protected void testConstructor() {
        assertEquals("Bin", test.getName());
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
    protected void testInteractIngredient() {
        p.setHoldingItem(new Bread());

        assertEquals(false, p.isHandEmpty());
        test.interact(p);
        assertEquals(true, p.isHandEmpty());
    }

    @Test
    protected void testInteractContainer() {

        Pan pan = new Pan();
        pan.addContent(new Meat());

        p.setHoldingItem(pan);

        assertEquals(false, p.isHandEmpty());
        assertEquals(false, pan.isEmpty());
        test.interact(p);
        assertEquals(false, p.isHandEmpty());
        assertEquals(true, pan.isEmpty());
    }

}
