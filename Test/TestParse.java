import org.junit.Assert;
import org.junit.Test;

public class TestParse {
    @Test
    public void testParseStringRFormat() {
        String input = "sub $t5, $s1, $s2";
        String[] args = {"sub", "$t5", "$s1", "$s2"};

        Assert.assertArrayEquals(args, Main.parseString(input));
    }

    @Test
    public void testParseStringRFormatComment() {
        String input = "or $v0, $s6, $t8# Comment";
        String[] args = {"or", "$v0", "$s6", "$t8"};

        Assert.assertArrayEquals(args, Main.parseString(input));
    }

    @Test
    public void testParseStringIFormat() {
        String input = "andi $s5, $t7, -79";
        String[] args = {"andi", "$s5", "$t7", "-79"};

        Assert.assertArrayEquals(args, Main.parseString(input));
    }

    @Test
    public void testParseStringSyscall() {
        String input = "syscall";
        String[] args = {"syscall"};

        Assert.assertArrayEquals(args, Main.parseString(input));
    }

    @Test
    public void testParseStringSW() {
        String input = "sw $t6, 114($s7)";
        String[] args = {"sw", "$t6", "114($s7)"};

        Assert.assertArrayEquals(args, Main.parseString(input));
    }

    @Test
    public void testParseStringLUI() {
        String input = "lui $v0, 0xda";
        String[] args = {"lui", "$v0", "0xda"};

        Assert.assertArrayEquals(args, Main.parseString(input));
    }

    @Test
    public void testParseStringZERO() {
        String input = "slt $zero, $t4, $t4";
        String[] args = {"slt", "$zero", "$t4", "$t4"};

        Assert.assertArrayEquals(args, Main.parseString(input));
    }

    @Test
    public void testParseStringJFormat() {
        String input = "j 0x74";
        String[] args = {"j", "0x74"};

        Assert.assertArrayEquals(args, Main.parseString(input));
    }

    @Test
    public void testParseStringLeadingSpaces() {
        String input = "         j 0x74";
        String[] args = {"j", "0x74"};

        Assert.assertArrayEquals(args, Main.parseString(input));
    }

    @Test
    public void testParseStringLeadingTabs() {
        String input = "\t\t\t\t\t\t\t j 0x74";
        String[] args = {"j", "0x74"};

        Assert.assertArrayEquals(args, Main.parseString(input));
    }

    @Test
    public void testParseStringLeadingSpacesAndTabs() {
        String input = "\t\t   \t\t\t    \t   \t j 0x74";
        String[] args = {"j", "0x74"};

        Assert.assertArrayEquals(args, Main.parseString(input));
    }

    @Test
    public void testParseStringRFormatSpaces() {
        String input = "sub    $t5,    $s1,     $s2";
        String[] args = {"sub", "$t5", "$s1", "$s2"};

        Assert.assertArrayEquals(args, Main.parseString(input));
    }

    @Test
    public void testParseStringRFormatTabs() {
        String input = "sub \t$t5, \t\t\t\t$s1, \t\t\t$s2";
        String[] args = {"sub", "$t5", "$s1", "$s2"};

        Assert.assertArrayEquals(args, Main.parseString(input));
    }

    @Test
    public void testParseStringRFormatSpacesAndTabs() {
        String input = "sub \t  $t5, \t   \t\t \t$s1,   \t \t\t  $s2";
        String[] args = {"sub", "$t5", "$s1", "$s2"};

        Assert.assertArrayEquals(args, Main.parseString(input));
    }

}