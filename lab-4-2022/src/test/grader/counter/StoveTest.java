package test.grader.counter;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import entity.container.Pan;
import entity.counter.Stove;
import entity.ingredient.Bread;
import entity.ingredient.Egg;
import test.grader.counter.base.BaseCounterTest;

class StoveTest extends BaseCounterTest {

    Stove test;

    @BeforeEach
    protected void setUpBeforeEachTest() {
        super.setUpBeforeEachTest();
        test = new Stove();
    }

    @Override
    @Test
    protected void testConstructor() {
        assertEquals("Stove", test.getName());
        assertEquals(null, test.getPlacedContent());
    }

    @Test
    protected void testConstructor1Param() { // add 1 parameter constructor test
        Stove test2 = new Stove(new Bread());
        assertEquals("Stove", test2.getName());
        assertEquals("Bread", test2.getPlacedContent().getName());
    }

    @Override
    @Test
    protected void testInteract() {
        assertEquals(true, p.isHandEmpty());
        test.interact(p);
        assertEquals(true, p.isHandEmpty());
    }

    @Test
    protected void testInteractPan() {
        p.setHoldingItem(new Pan());
        assertEquals(false, p.isHandEmpty());
        test.interact(p);
        assertEquals(true, p.isHandEmpty());
        assertEquals("Pan", test.getPlacedContent().getName());
    }

    @Test
    protected void testInteractNonPan() {
        p.setHoldingItem(new Bread());
        assertEquals(false, p.isHandEmpty());
        test.interact(p);
        assertEquals(false, p.isHandEmpty());
        assertEquals(null, test.getPlacedContent());
    }

    @Test
    protected void testUpdate() {

        Pan pan = new Pan();
        pan.addContent(new Egg());

        test.setPlacedContent(pan);

        Egg egg = (Egg) ((Pan) test.getPlacedContent()).getContent().get(0);

        assertEquals(0, egg.getCookedPercentage());
        test.update();
        assertEquals(12, egg.getCookedPercentage());
    }

}
