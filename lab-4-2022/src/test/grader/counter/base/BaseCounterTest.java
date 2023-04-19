package test.grader.counter.base;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import logic.Player;

public abstract class BaseCounterTest {

    protected Player p;

    @BeforeEach
    protected void setUpBeforeEachTest() {
        p = new Player();

    }

    @Test
    protected abstract void testConstructor();

    @Test
    protected abstract void testInteract();

}
