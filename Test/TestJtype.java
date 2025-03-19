import org.junit.Assert;
import org.junit.Test;
import java.util.HashMap;
import java.util.Map;




public class TestJtype {
//    @Test
//    public void testJTypeHex() {
//        Map<String, Integer> map = new HashMap<String, Integer>();
//        Map<String, Integer> addrMap = new HashMap<String, Integer>();
//        map = Main.createMap(map);
//
//        String[] args = {"j", "0x74"};
//        int result = 0x08000074;
//        Assert.assertEquals(result, Main.jType(args, map, addrMap));
//    }
//
//    @Test
//    public void testJTypeDec() {
//        Map<String, Integer> map = new HashMap<String, Integer>();
//        map = Main.createMap(map);
//        Map<String, Integer> addrMap = new HashMap<String, Integer>();
//
//        String[] args = {"j", "116"};
//        int result = 0x08000074;
//        Assert.assertEquals(result, Main.jType(args, map, addrMap));
//    }
    @Test
    public void testJTypeLabel() {
        Map<String, Integer> map = new HashMap<String, Integer>();
        map = Main.createMap(map);
        Map<String, Integer> addrMap = new HashMap<String, Integer>();
        addrMap.put("label", 0x00400034);

        String[] args = {"j", "label"};
        int result = 0x08400034;
        System.out.println(String.format("%08x", Main.jType(args, map, addrMap)));
        Assert.assertEquals(result, Main.jType(args, map, addrMap));

    }
}
