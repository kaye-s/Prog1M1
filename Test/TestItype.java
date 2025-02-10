import org.junit.Assert;
import org.junit.Test;
import java.util.HashMap;
import java.util.Map;

public class TestItype {
    @Test
    public void testITypeRegAndiNeg() {
        Map<String, Integer> map = new HashMap<String, Integer>();
        map = Main.createMap(map);

        String[] args = {"andi", "$s5", "$t7", "-79"};
        int result = 0x31f5ffb1;
        Assert.assertEquals(result, Main.iTypeReg(args, map));
    }
    @Test
    public void testITypeRegAndiPos() {
        Map<String, Integer> map = new HashMap<String, Integer>();
        map = Main.createMap(map);

        String[] args = {"andi", "$sp", "$k1", "0x4e"};
        int result = 0x337d004e;
        Assert.assertEquals(result, Main.iTypeReg(args, map));
    }

    @Test
    public void testITypeBranchBne() {
        Map<String, Integer> map = new HashMap<String, Integer>();
        map = Main.createMap(map);

        String[] args = {"bne", "$s0", "$k1", "0x79"};
        int result = 0x161b0079;
        Assert.assertEquals(result, Main.iTypeBranch(args, map));
    }

    @Test
    public void testITypeBranchLui() {
        Map<String, Integer> map = new HashMap<String, Integer>();
        map = Main.createMap(map);

        String[] args = {"lui", "$a0", "0x7"};
        int result = 0x3c040007;
        Assert.assertEquals(result, Main.iTypeBranch(args, map));
    }
}
