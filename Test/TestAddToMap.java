import org.junit.Assert;
import org.junit.Test;
import java.util.HashMap;
import java.util.Map;

public class TestAddToMap {
    @Test
    public void testBasicAdd() {
        Map<String, Integer> addrMap= new HashMap<>();
        Assert.assertEquals(0x10010000 + 12, Main.addToMap("even:", "Some string", 0x10010000, addrMap));

        Map<String, Integer> testMap = new HashMap<>();
        testMap.put("even", 0x10010000);
        Assert.assertEquals(testMap.get("even"), addrMap.get("even"));

    }
}
