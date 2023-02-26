package test.grader;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

//import static org.junit.Assert.assertEquals;
//
//import org.junit.Test;

import logic.unit.BaseCompetitor;
import logic.unit.Sorcerer;
import logic.unit.Tiger;
import logic.unit.ToughMan;

public class BaseCompetitorTest {
    @Test
    public void testConstructor() {
        BaseCompetitor comp = new BaseCompetitor("testName1");
        assertEquals("testName1", comp.getName());
        assertEquals(5, comp.getHp());
        assertEquals(3, comp.getPower());

        BaseCompetitor comp2 = new BaseCompetitor("testName2", 10, 13);
        assertEquals("testName2", comp2.getName());
        assertEquals(10, comp2.getHp());
        assertEquals(13, comp2.getPower());
    }

    @Test
    public void testBadConstructor() {
        BaseCompetitor comp = new BaseCompetitor("", -4, 5);
        assertEquals("", comp.getName());
        assertEquals(0, comp.getHp());
        assertEquals(5, comp.getPower());

        BaseCompetitor comp2 = new BaseCompetitor("", 4, -2);
        assertEquals("", comp2.getName());
        assertEquals(4, comp2.getHp());
        assertEquals(1, comp2.getPower());

        BaseCompetitor comp3 = new BaseCompetitor("", -6, -2);
        assertEquals("", comp3.getName());
        assertEquals(0, comp3.getHp());
        assertEquals(1, comp3.getPower());
    }

    @Test
    public void testAttack() {
        BaseCompetitor comp = new BaseCompetitor("Competitor1");
        BaseCompetitor comp2 = new BaseCompetitor("Competitor2", 5, 2);
        comp.attack(comp2);
        assertEquals(2, comp2.getHp());
        comp.attack(comp2);
        assertEquals(0, comp2.getHp());

        BaseCompetitor comp3 = new BaseCompetitor("Competitor3", 4, 2);
        BaseCompetitor comp4 = new BaseCompetitor("Competitor4", 5, 3);
        comp3.attack(comp4);
        assertEquals(3, comp4.getHp());
        comp4.attack(comp3);
        assertEquals(1, comp3.getHp());

        BaseCompetitor comp5 = new BaseCompetitor("Competitor5");
        Sorcerer sorc = new Sorcerer("Sorc");
        comp5.attack(sorc);
        assertEquals(1, sorc.getHp());
        comp5.attack(sorc);
        assertEquals(0, sorc.getHp());

        Tiger tig = new Tiger("Tiger");
        comp5.attack(tig);
        assertEquals(4, tig.getHp());
        comp5.attack(tig);
        assertEquals(1, tig.getHp());
        comp5.attack(tig);
        assertEquals(0, tig.getHp());

        ToughMan tm = new ToughMan("Tough");
        comp5.attack(tm);
        assertEquals(5, tm.getHp());
        comp5.attack(tm);
        assertEquals(2, tm.getHp());
        comp5.attack(tm);
        assertEquals(0, tm.getHp());
    }

    @Test
    public void testGetType() {
        BaseCompetitor comp = new BaseCompetitor("");
        assertEquals("BaseCompetitor", comp.getType());
        Sorcerer sorc = new Sorcerer("");
        assertEquals("Sorcerer", sorc.getType());
        Tiger tig = new Tiger("");
        assertEquals("Tiger", tig.getType());
        ToughMan tm = new ToughMan("");
        assertEquals("ToughMan", tm.getType());
    }

    @Test
    public void testSetName() {
        BaseCompetitor comp = new BaseCompetitor("test1");
        comp.setName("testName");
        assertEquals("testName", comp.getName());
    }

    @Test
    public void testSetHp() {
        BaseCompetitor comp = new BaseCompetitor("test1");
        comp.setHp(10);
        assertEquals(10, comp.getHp());
        comp.setHp(-4);
        assertEquals(0, comp.getHp());
    }

    @Test
    public void testSetPower() {
        BaseCompetitor comp = new BaseCompetitor("test1");
        comp.setPower(5);
        assertEquals(5, comp.getPower());
        comp.setPower(-2);
        assertEquals(1, comp.getPower());
    }
}
