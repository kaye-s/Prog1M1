import org.junit.Assert;
import org.junit.Test;
import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;

public class TestToLittleE {
    //toLittleE(ArrayList) returns arrayList
    //  Convert array to little eindian
    //  [11,22,33,44] -> [44332211]
    //  Last Element in newList must be 8 (00 buffered)
    @Test
    public void testSimple() {
        ArrayList<Integer> in = new ArrayList<Integer>();
        in.add(0x11);
        in.add(0x22);
        in.add(0x33);
        in.add(0x44);

        ArrayList<String> out = Main.toLittleE(in);
        Assert.assertEquals(1, out.size());
        Assert.assertEquals("44332211", out.get(0));
    }

    @Test
    public void testTwoElements() {
        ArrayList<Integer> in = new ArrayList<Integer>();
        in.add(0x11);
        in.add(0x22);
        in.add(0x33);
        in.add(0x44);
        in.add(0x55);
        in.add(0x66);
        in.add(0x77);
        in.add(0x88);

        ArrayList<String> out = Main.toLittleE(in);
        Assert.assertEquals(2, out.size());
        Assert.assertEquals("44332211", out.get(0));
        Assert.assertEquals("88776655", out.get(1));
    }

    @Test
    public void testLetterSimple() {
        ArrayList<Integer> in = new ArrayList<Integer>();
        in.add(0xaa);
        in.add(0xbb);
        in.add(0xcc);
        in.add(0xdd);

        ArrayList<String> out = Main.toLittleE(in);
        Assert.assertEquals(1, out.size());
        Assert.assertEquals("ddeebbaa", out.get(0));
    }

    @Test
    public void testTwoLetter() {
        ArrayList<Integer> in = new ArrayList<Integer>();
        in.add(0xaa);
        in.add(0xbb);
        in.add(0xcc);
        in.add(0xdd);
        in.add(0xee);
        in.add(0xff);
        in.add(0xfa);
        in.add(0xfb);

        ArrayList<String> out = Main.toLittleE(in);
        Assert.assertEquals(2, out.size());
        Assert.assertEquals("ddeebbaa", out.get(0));
        Assert.assertEquals("fbfaffee", out.get(1));
    }

    @Test
    public void testBufOne() {
        ArrayList<Integer> in = new ArrayList<Integer>();
        in.add(0x11);

        ArrayList<String> out = Main.toLittleE(in);
        Assert.assertEquals(1, out.size());
        Assert.assertEquals("00000011", out.get(0));
    }

    @Test
    public void testBufTwo() {
        ArrayList<Integer> in = new ArrayList<Integer>();
        in.add(0x11);
        in.add(0x22);

        ArrayList<String> out = Main.toLittleE(in);
        Assert.assertEquals(1, out.size());
        Assert.assertEquals("00002211", out.get(0));
    }

    @Test
    public void testBufThree() {
        ArrayList<Integer> in = new ArrayList<Integer>();
        in.add(0x11);
        in.add(0x22);
        in.add(0x33);

        ArrayList<String> out = Main.toLittleE(in);
        Assert.assertEquals(1, out.size());
        Assert.assertEquals("00332211", out.get(0));
    }

    @Test
    public void testSimpleAndBufOne() {
        ArrayList<Integer> in = new ArrayList<Integer>();
        in.add(0x11);
        in.add(0x22);
        in.add(0x33);
        in.add(0x44);
        in.add(0x55);

        ArrayList<String> out = Main.toLittleE(in);
        Assert.assertEquals(2, out.size());
        Assert.assertEquals("44332211", out.get(0));
        Assert.assertEquals("00000055", out.get(1));
    }

    @Test
    public void testSimpleAndBufTwo() {
        ArrayList<Integer> in = new ArrayList<Integer>();
        in.add(0x11);
        in.add(0x22);
        in.add(0x33);
        in.add(0x44);
        in.add(0x55);
        in.add(0x66);

        ArrayList<String> out = Main.toLittleE(in);
        Assert.assertEquals(2, out.size());
        Assert.assertEquals("44332211", out.get(0));
        Assert.assertEquals("00006655", out.get(1));
    }

    @Test
    public void testSimpleAndBufThree() {
        ArrayList<Integer> in = new ArrayList<Integer>();
        in.add(0x11);
        in.add(0x22);
        in.add(0x33);
        in.add(0x44);
        in.add(0x55);
        in.add(0x66);
        in.add(0x77);

        ArrayList<String> out = Main.toLittleE(in);
        Assert.assertEquals(2, out.size());
        Assert.assertEquals("44332211", out.get(0));
        Assert.assertEquals("00776655", out.get(1));
    }

    @Test
    public void testZeroes() {
        ArrayList<Integer> in = new ArrayList<Integer>();
        in.add(0x11);
        in.add(0x22);
        in.add(0x00);
        in.add(0x44);
        in.add(0x00);
        in.add(0x66);
        in.add(0x77);

        ArrayList<String> out = Main.toLittleE(in);
        Assert.assertEquals(2, out.size());
        Assert.assertEquals("44002211", out.get(0));
        Assert.assertEquals("00776600", out.get(1));
    }
}