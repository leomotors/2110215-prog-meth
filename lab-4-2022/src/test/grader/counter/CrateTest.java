package test.grader.counter;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import entity.counter.Crate;
import entity.ingredient.Bread;
import test.grader.counter.base.BaseCounterTest;

class CrateTest extends BaseCounterTest {

    Crate test;

    @BeforeEach
    protected void setUpBeforeEachTest() {
        super.setUpBeforeEachTest();
        test = new Crate("Bread");
    }

    @Override
    @Test
    protected void testConstructor() {
        assertEquals("Bread Crate", test.getName());
        assertEquals(null, test.getPlacedContent());
        assertEquals("Bread", test.getMyIngredient()); // addition
    }

    @Override
    @Test
    protected void testInteract() {
        assertEquals(true, p.isHandEmpty());
        test.interact(p);
        assertEquals(false, p.isHandEmpty());
        assertEquals("Bread", p.getHoldingItem().getName());
    }

    @Test
    protected void testInteractHandFull() {
        p.setHoldingItem(new Bread());

        assertEquals(false, p.isHandEmpty());
        test.interact(p);
        assertEquals(true, p.isHandEmpty());
        assertEquals("Bread", test.getPlacedContent().getName());
    }

    @Test
    protected void testInteractInvalid() {
        test = new Crate("Amogus");

        assertEquals(true, p.isHandEmpty());
        test.interact(p);
        assertEquals(true, p.isHandEmpty());
    }

}
