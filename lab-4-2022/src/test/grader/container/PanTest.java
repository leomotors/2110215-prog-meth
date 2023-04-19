package test.grader.container;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import entity.container.Pan;
import entity.ingredient.Bread;
import entity.ingredient.Egg;
import entity.ingredient.Lettuce;
import entity.ingredient.Meat;

class PanTest {

    Pan test;

    @BeforeEach
    protected void setUpBeforeEachTest() {
        test = new Pan();
    }

    @Test
    void testConstructor() {
        assertEquals("Pan", test.getName());
        assertEquals(1, test.getCapacity());
        assertEquals(1, test.getMaxCapacity());
        assertEquals(0, test.getContent().size());
    }

    @Test
    void testVerifyContent() {
        assertEquals(true, test.verifyContent(new Meat()));
    }

    @Test
    void testVerifyContentNonCookable() {
        assertEquals(false, test.verifyContent(new Bread()));
    }

    @Test
    void testCook() {
        Meat m = new Meat();
        Lettuce l = new Lettuce();
        Egg e = new Egg();
        test.setMaxCapacity(3); // default Pan can hold only 1 ingredient so I
                                // expand it
        test.setCapacity(3);
        test.addContent(m);
        test.addContent(l); // add lettuce (not cookable, which will not get
                            // added) and egg
        test.addContent(e);
        assertEquals(0, m.getCookedPercentage());

        test.cook();
        assertEquals(10, m.getCookedPercentage());
        assertEquals(12, e.getCookedPercentage()); // test egg too
    }

}
