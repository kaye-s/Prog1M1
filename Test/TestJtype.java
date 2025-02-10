import org.junit.Assert;
import org.junit.Test;
import java.util.HashMap;
import java.util.Map;




public class TestJtype {
    @Test
    public void testJTypeHex() {
        Map<String, Integer> map = new HashMap<String, Integer>();
        map = Main.createMap(map);

        String[] args = {"j", "0x74"};
        int result = 0x08000074;
        Assert.assertEquals(result, Main.jType(args, map));
    }

    @Test
    public void testJTypeDec() {
        Map<String, Integer> map = new HashMap<String, Integer>();
        map = Main.createMap(map);

        String[] args = {"j", "116"};
        int result = 0x08000074;
        Assert.assertEquals(result, Main.jType(args, map));
    }
}
