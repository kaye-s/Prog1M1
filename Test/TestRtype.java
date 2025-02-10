import org.junit.Assert;
import org.junit.Test;
import java.util.HashMap;
import java.util.Map;




public class TestRtype {
    @Test
    public void testRTypeAdd() {
        Map<String, Integer> map = new HashMap<String, Integer>();
        map = Main.createMap(map);

        String[] args = {"add", "$a3", "$s4", "$t4"};
        int result = 0x028c3820;
        Assert.assertEquals(result, Main.rType(args, map));
    }

    @Test
    public void testRTypeAnd() {
        Map<String, Integer> map = new HashMap<String, Integer>();
        map = Main.createMap(map);

        String[] args = {"and", "$t7", "$t1", "$a1"};
        int result = 0x01257824;
        Assert.assertEquals(result, Main.rType(args, map));
    }
}
