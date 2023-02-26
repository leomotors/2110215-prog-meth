package test.grader;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import logic.unit.BaseCompetitor;
import logic.unit.Sorcerer;
import logic.unit.Tiger;
import logic.unit.ToughMan;

public class TigerTest {
    @Test
    public void testConstructor() {
        Tiger tig = new Tiger("testName1");
        assertEquals("testName1", tig.getName());
        assertEquals(7, tig.getHp());
        assertEquals(5, tig.getPower());

        Tiger tig2 = new Tiger("testName2", 6, 1);
        assertEquals("testName2", tig2.getName());
        assertEquals(6, tig2.getHp());
        assertEquals(1, tig2.getPower());
    }

    @Test
    public void testBadConstructor() {
        Tiger tig = new Tiger("test1", 6, -1);
        assertEquals("test1", tig.getName());
        assertEquals(6, tig.getHp());
        assertEquals(1, tig.getPower());

        Tiger tig2 = new Tiger("test2", -3, 2);
        assertEquals("test2", tig2.getName());
        assertEquals(0, tig2.getHp());
        assertEquals(2, tig2.getPower());

        Tiger tig3 = new Tiger("test3", -4, -6);
        assertEquals("test3", tig3.getName());
        assertEquals(0, tig3.getHp());
        assertEquals(1, tig3.getPower());
    }

    @Test
    public void testTrain() {
        Tiger tig = new Tiger("Tiger", 7, 5);
        tig.train(3);
        assertEquals(8, tig.getPower());
        tig.train(-2);
        assertEquals(8, tig.getPower());
    }

    @Test
    public void testAttack() {
        Tiger tig = new Tiger("Tiger", 6, 2);
        BaseCompetitor comp = new BaseCompetitor("Comp", 5, 1);
        tig.attack(comp);
        assertEquals(3, comp.getHp());
        tig.attack(comp);
        assertEquals(1, comp.getHp());
        tig.attack(comp);
        assertEquals(0, comp.getHp());

        Sorcerer sorc = new Sorcerer("Sorc", 4, 2);
        tig.attack(sorc);
        assertEquals(1, sorc.getHp());
        tig.attack(sorc);
        assertEquals(0, sorc.getHp());

        Tiger tig2 = new Tiger("Tiger", 5, 1);
        tig.attack(tig2);
        assertEquals(3, tig2.getHp());
        tig.attack(tig2);
        assertEquals(1, tig2.getHp());
        tig.attack(tig2);
        assertEquals(0, tig2.getHp());

        ToughMan tm = new ToughMan("ToughMan", 2, 4);
        tig.attack(tm);
        assertEquals(1, tm.getHp());
        tig.attack(tm);
        assertEquals(0, tm.getHp());
        tig.attack(tm);
        assertEquals(0, tm.getHp());
    }
}
