import org.junit.Assert;
import org.junit.Test;
import java.util.HashMap;
import java.util.Map;

public class TestMap {
    @Test
    public void testMapOneValue() {
        Map<String, Integer> map = new HashMap<String, Integer>();
        map = Main.createMap(map);

        Map<String, Integer> manualMap = new HashMap<String, Integer>();
        manualMap.put("add", 32);

        Assert.assertEquals(map.get("add"), manualMap.get("add"));
    }

    @Test
    public void testMapMultipleValue() {
        Map<String, Integer> map = new HashMap<String, Integer>();
        map = Main.createMap(map);

        Map<String, Integer> manualMap = new HashMap<String, Integer>();
        manualMap.put("add", 32);
        manualMap.put("addiu", 9);

        Assert.assertEquals(map.get("add"), manualMap.get("add"));
        Assert.assertEquals(map.get("addiu"), manualMap.get("addiu"));
    }

    @Test
    public void testMapAllOps() {
        Map<String, Integer> map = new HashMap<String, Integer>();
        map = Main.createMap(map);

        Map<String, Integer> manualMap = new HashMap<String, Integer>();
        manualMap.put("add", 32);
        manualMap.put("addiu", 9);
        manualMap.put("and", 36);
        manualMap.put("andi", 12);
        manualMap.put("beq", 4);
        manualMap.put("bne", 5);
        manualMap.put("j", 2);
        manualMap.put("lui", 15);
        manualMap.put("lw", 35);
        manualMap.put("or", 37);
        manualMap.put("ori", 13);
        manualMap.put("slt", 42);
        manualMap.put("sub", 34);
        manualMap.put("sw", 43);
        manualMap.put("syscall", 12);


        Assert.assertEquals(map.get("add"), manualMap.get("add"));
        Assert.assertEquals(map.get("addiu"), manualMap.get("addiu"));
        Assert.assertEquals(map.get("and"), manualMap.get("and"));
        Assert.assertEquals(map.get("andi"), manualMap.get("andi"));
        Assert.assertEquals(map.get("beq"), manualMap.get("beq"));
        Assert.assertEquals(map.get("bne"), manualMap.get("bne"));
        Assert.assertEquals(map.get("j"), manualMap.get("j"));
        Assert.assertEquals(map.get("lui"), manualMap.get("lui"));
        Assert.assertEquals(map.get("lw"), manualMap.get("lw"));
        Assert.assertEquals(map.get("or"), manualMap.get("or"));
        Assert.assertEquals(map.get("ori"), manualMap.get("ori"));
        Assert.assertEquals(map.get("slt"), manualMap.get("slt"));
        Assert.assertEquals(map.get("sub"), manualMap.get("sub"));
        Assert.assertEquals(map.get("sw"), manualMap.get("sw"));
        Assert.assertEquals(map.get("syscall"), manualMap.get("syscall"));
    }

    @Test
    public void testMapOneRegister() {
        Map<String, Integer> map = new HashMap<String, Integer>();
        map = Main.createMap(map);

        Map<String, Integer> manualMap = new HashMap<String, Integer>();
        manualMap.put("$zero", 0);

        Assert.assertEquals(map.get("$zero"), manualMap.get("$zero"));
    }

    @Test
    public void testMapMultRegisters() {
        Map<String, Integer> map = new HashMap<String, Integer>();
        map = Main.createMap(map);

        Map<String, Integer> manualMap = new HashMap<String, Integer>();
        manualMap.put("$zero", 0);
        manualMap.put("$at", 1);

        Assert.assertEquals(map.get("$zero"), manualMap.get("$zero"));
        Assert.assertEquals(map.get("$at"), manualMap.get("$at"));
    }

    @Test
    public void testMapAllRegisters() {
        Map<String, Integer> map = new HashMap<String, Integer>();
        map = Main.createMap(map);

        Map<String, Integer> manualMap = new HashMap<String, Integer>();
        manualMap.put("$zero", 0);
        manualMap.put("$at", 1);
        manualMap.put("$v0", 2);
        manualMap.put("$v1", 3);
        manualMap.put("$a0", 4);
        manualMap.put("$a1", 5);
        manualMap.put("$a2", 6);
        manualMap.put("$a3", 7);
        manualMap.put("$t0", 8);
        manualMap.put("$t1", 9);
        manualMap.put("$t2", 10);
        manualMap.put("$t3", 11);
        manualMap.put("$t4", 12);
        manualMap.put("$t5", 13);
        manualMap.put("$t6", 14);
        manualMap.put("$t7", 15);
        manualMap.put("$s0", 16);
        manualMap.put("$s1", 17);
        manualMap.put("$s2", 18);
        manualMap.put("$s3", 19);
        manualMap.put("$s4", 20);
        manualMap.put("$s5", 21);
        manualMap.put("$s6", 22);
        manualMap.put("$s7", 23);
        manualMap.put("$t8", 24);
        manualMap.put("$t9", 25);
        manualMap.put("$k0", 26);
        manualMap.put("$k1", 27);
        manualMap.put("$gp", 28);
        manualMap.put("$sp", 29);
        manualMap.put("$fp", 30);
        manualMap.put("$ra", 31);

        Assert.assertEquals(map.get("$zero"), manualMap.get("$zero"));
        Assert.assertEquals(map.get("$at"), manualMap.get("$at"));
        Assert.assertEquals(map.get("$v0"), manualMap.get("$v0"));
        Assert.assertEquals(map.get("$v1"), manualMap.get("$v1"));
        Assert.assertEquals(map.get("$a0"), manualMap.get("$a0"));
        Assert.assertEquals(map.get("$a1"), manualMap.get("$a1"));
        Assert.assertEquals(map.get("$a2"), manualMap.get("$a2"));
        Assert.assertEquals(map.get("$a3"), manualMap.get("$a3"));
        Assert.assertEquals(map.get("$t0"), manualMap.get("$t0"));
        Assert.assertEquals(map.get("$t1"), manualMap.get("$t1"));
        Assert.assertEquals(map.get("$t2"), manualMap.get("$t2"));
        Assert.assertEquals(map.get("$t3"), manualMap.get("$t3"));
        Assert.assertEquals(map.get("$t4"), manualMap.get("$t4"));
        Assert.assertEquals(map.get("$t5"), manualMap.get("$t5"));
        Assert.assertEquals(map.get("$t6"), manualMap.get("$t6"));
        Assert.assertEquals(map.get("$t7"), manualMap.get("$t7"));
        Assert.assertEquals(map.get("$s0"), manualMap.get("$s0"));
        Assert.assertEquals(map.get("$s1"), manualMap.get("$s1"));
        Assert.assertEquals(map.get("$s2"), manualMap.get("$s2"));
        Assert.assertEquals(map.get("$s3"), manualMap.get("$s3"));
        Assert.assertEquals(map.get("$s4"), manualMap.get("$s4"));
        Assert.assertEquals(map.get("$s5"), manualMap.get("$s5"));
        Assert.assertEquals(map.get("$s6"), manualMap.get("$s6"));
        Assert.assertEquals(map.get("$s7"), manualMap.get("$s7"));
        Assert.assertEquals(map.get("$t8"), manualMap.get("$t8"));
        Assert.assertEquals(map.get("$t9"), manualMap.get("$t9"));
        Assert.assertEquals(map.get("$k0"), manualMap.get("$k0"));
        Assert.assertEquals(map.get("$k1"), manualMap.get("$k1"));
        Assert.assertEquals(map.get("$gp"), manualMap.get("$gp"));
        Assert.assertEquals(map.get("$sp"), manualMap.get("$sp"));
        Assert.assertEquals(map.get("$fp"), manualMap.get("$fp"));
        Assert.assertEquals(map.get("$ra"), manualMap.get("$ra"));

    }
}