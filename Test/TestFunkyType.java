import org.junit.Assert;
import org.junit.Test;
import java.util.HashMap;
import java.util.Map;

public class TestFunkyType {
    @Test
    public void testFTypeLw() {
        Map<String, Integer> map = new HashMap<String, Integer>();
        map = Main.createMap(map);

        String[] args = {"lw", "$a0", "133($t9)"};
        int result = 0x8f240085;
        Assert.assertEquals(result, Main.funkyType(args, map));
    }

    @Test
    public void testFTypeSw() {
        Map<String, Integer> map = new HashMap<String, Integer>();
        map = Main.createMap(map);

        String[] args = {"sw", "$at", "39($zero)"};
        int result = 0xac010027;
        Assert.assertEquals(result, Main.funkyType(args, map));
    }

    @Test
    public void testFTypeSwWithoutOffset() {
        Map<String, Integer> map = new HashMap<String, Integer>();
        map = Main.createMap(map);

        String[] args = {"sw", "$s2", "($t6)"};
        int result = 0xadd20000;
        Assert.assertEquals(result, Main.funkyType(args, map));
    }

    @Test
    public void testFTypeSwFailedTest() {
        Map<String, Integer> map = new HashMap<String, Integer>();
        map = Main.createMap(map);

        String[] args = {"sw", "$t6", "114($s7)"};
        int result = 0xaeee0072;
        Assert.assertEquals(result, Main.funkyType(args, map));
    }

    @Test
    public void testFTypeSwNegativeOffset() {
        Map<String, Integer> map = new HashMap<String, Integer>();
        map = Main.createMap(map);

        String[] args = {"sw", "$s2", "-211($a1)"};
        int result = 0xacb2ff2d;
        Assert.assertEquals(result, Main.funkyType(args, map));
    }
}
