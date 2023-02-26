package test.grader;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import logic.unit.BaseCompetitor;
import logic.unit.Sorcerer;
import logic.unit.Tiger;
import logic.unit.ToughMan;

public class ToughManTest {
    @Test
    public void testConstructor() {
        ToughMan tm = new ToughMan("testName1");
        assertEquals("testName1", tm.getName());
        assertEquals(8, tm.getHp());
        assertEquals(4, tm.getPower());
        assertEquals(8, tm.getMaxHp());

        ToughMan tm2 = new ToughMan("testName2", 4, 3);
        assertEquals("testName2", tm2.getName());
        assertEquals(4, tm2.getHp());
        assertEquals(3, tm2.getPower());
        assertEquals(4, tm2.getMaxHp());
    }

    @Test
    public void testBadConstructor() {
        ToughMan tm = new ToughMan("test1", 4, -3);
        assertEquals("test1", tm.getName());
        assertEquals(4, tm.getHp());
        assertEquals(1, tm.getPower());
        assertEquals(4, tm.getMaxHp());
        ToughMan tm2 = new ToughMan("test2", -2, 4);
        assertEquals("test2", tm2.getName());
        assertEquals(0, tm2.getHp());
        assertEquals(4, tm2.getPower());
        assertEquals(0, tm2.getMaxHp());
        ToughMan tm3 = new ToughMan("test3", -1, -3);
        assertEquals("test3", tm3.getName());
        assertEquals(0, tm3.getHp());
        assertEquals(1, tm3.getPower());
        assertEquals(0, tm3.getMaxHp());
    }

    @Test
    public void testHeal() {
        ToughMan tm = new ToughMan("test", 8, 4);
        tm.setHp(3);
        tm.heal(2);
        assertEquals(5, tm.getHp());
        tm.heal(-2);
        assertEquals(5, tm.getHp());
        tm.heal(6);
        assertEquals(8, tm.getHp());
    }

    @Test
    public void testAttack() {
        ToughMan tm = new ToughMan("ToughMan", 5, 3);
        BaseCompetitor comp = new BaseCompetitor("Comp", 5, 5);
        tm.attack(comp);
        assertEquals(2, comp.getHp());
        tm.attack(comp);
        assertEquals(0, comp.getHp());

        Sorcerer sorc = new Sorcerer("Sorc", 2, 2);
        tm.attack(sorc);
        assertEquals(1, sorc.getHp());
        tm.attack(sorc);
        assertEquals(0, sorc.getHp());
        tm.attack(sorc);
        assertEquals(0, sorc.getHp());

        Tiger tig = new Tiger("Tiger", 7, 5);
        tm.attack(tig);
        assertEquals(3, tig.getHp());
        tm.attack(tig);
        assertEquals(0, tig.getHp());

        ToughMan tm2 = new ToughMan("ToughMan2", 8, 4);
        tm.attack(tm2);
        assertEquals(5, tm2.getHp());
        tm.attack(tm2);
        assertEquals(2, tm2.getHp());
        tm.attack(tm2);
        assertEquals(0, tm2.getHp());
    }

    @Test
    public void testSetMaxHp() {
        ToughMan tm = new ToughMan("test", 8, 4);
        tm.setMaxHp(10);
        assertEquals(10, tm.getMaxHp());
        assertEquals(8, tm.getHp());

        tm.setMaxHp(3);
        assertEquals(3, tm.getMaxHp());
        assertEquals(3, tm.getHp());

        tm.setMaxHp(-5);
        assertEquals(0, tm.getMaxHp());
        assertEquals(0, tm.getHp());
    }
}
