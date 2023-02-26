package test.grader;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import logic.unit.BaseCompetitor;
import logic.unit.Sorcerer;
import logic.unit.Tiger;
import logic.unit.ToughMan;

public class SorcererTest {
    @Test
    public void testConstructor() {
        Sorcerer sorc = new Sorcerer("testName1");
        assertEquals("testName1", sorc.getName());
        assertEquals(4, sorc.getHp());
        assertEquals(2, sorc.getPower());

        Sorcerer sorc2 = new Sorcerer("testName2", 5, 4);
        assertEquals("testName2", sorc2.getName());
        assertEquals(5, sorc2.getHp());
        assertEquals(4, sorc2.getPower());
    }

    @Test
    public void testBadConstructor() {
        Sorcerer sorc = new Sorcerer("test1", 5, -1);
        assertEquals("test1", sorc.getName());
        assertEquals(5, sorc.getHp());
        assertEquals(1, sorc.getPower());

        Sorcerer sorc2 = new Sorcerer("test2", -3, 4);
        assertEquals("test2", sorc2.getName());
        assertEquals(0, sorc2.getHp());
        assertEquals(4, sorc2.getPower());

        Sorcerer sorc3 = new Sorcerer("test3", -2, -3);
        assertEquals("test3", sorc3.getName());
        assertEquals(0, sorc3.getHp());
        assertEquals(1, sorc3.getPower());
    }

    @Test
    public void testLowerAttack() {
        Sorcerer sorc = new Sorcerer("Sorc", 4, 2);
        BaseCompetitor comp = new BaseCompetitor("Comp", 5, 3);
        sorc.lowerPower(comp, -2);
        assertEquals(3, comp.getPower());
        sorc.lowerPower(comp, 2);
        assertEquals(1, comp.getPower());
        sorc.lowerPower(comp, 5);
        assertEquals(1, comp.getPower());

        Sorcerer sorc2 = new Sorcerer("Sorc2", 4, 2);
        sorc.lowerPower(sorc2, -2);
        assertEquals(2, sorc2.getPower());
        sorc.lowerPower(sorc2, 2);
        assertEquals(1, sorc2.getPower());

        Tiger tig = new Tiger("Tiger", 7, 5);
        sorc.lowerPower(tig, -2);
        assertEquals(5, tig.getPower());
        sorc.lowerPower(tig, 1);
        assertEquals(4, tig.getPower());
        sorc.lowerPower(tig, 5);
        assertEquals(1, tig.getPower());

        ToughMan tm = new ToughMan("ToughMan", 8, 4);
        sorc.lowerPower(tm, -2);
        assertEquals(4, tm.getPower());
        sorc.lowerPower(tm, 4);
        assertEquals(1, tm.getPower());
    }

    @Test
    public void testAttack() {
        Sorcerer sorc = new Sorcerer("Sorc", 4, 2);
        BaseCompetitor comp = new BaseCompetitor("Comp", 3, 1);
        sorc.attack(comp);
        assertEquals(1, comp.getHp());
        sorc.attack(comp);
        assertEquals(0, comp.getHp());

        Sorcerer sorc2 = new Sorcerer("Sorc2", 4, 2);
        sorc.attack(sorc2);
        assertEquals(2, sorc2.getHp());
        sorc.attack(sorc2);
        assertEquals(0, sorc2.getHp());
        sorc.attack(sorc2);
        assertEquals(0, sorc2.getHp());

        Tiger tig = new Tiger("Tiger", 3, 1);
        sorc.attack(tig);
        assertEquals(2, tig.getHp());
        sorc.attack(tig);
        assertEquals(1, tig.getHp());
        sorc.attack(tig);
        assertEquals(0, tig.getHp());
        sorc.attack(tig);
        assertEquals(0, tig.getHp());

        ToughMan tm = new ToughMan("ToughMan", 8, 4);
        sorc.attack(tm);
        assertEquals(5, tm.getHp());
        sorc.attack(tm);
        assertEquals(2, tm.getHp());
        sorc.attack(tm);
        assertEquals(0, tm.getHp());
    }
}
