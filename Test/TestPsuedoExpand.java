import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.Scanner; // Import the Scanner class to read text files
import java.io.FileWriter;
import org.junit.Assert;
import org.junit.Test;

public class TestPsuedoExpand {
    Map<String, Integer> addrMap = new HashMap<>();
    @Test
    public void testlibasic() {
        String psuedo = "li $t0, 0x12345678";
        String[] expandTrue = {"lui $at, 0x1234", "ori $t0, $at, 0x5678"};
        String[] expand = Main.psuedoExpand(psuedo, addrMap);
        Assert.assertEquals(expand.length, expandTrue.length);
        Assert.assertEquals(expandTrue[0], expand[0]);
        Assert.assertEquals(expandTrue[1], expand[1]);
    }

    @Test
    public void testlifour() {
        String psuedo = "li $t0, 0x1234";
        String[] expandTrue = {"addiu $t0, $0, 0x1234", ""};
        String[] expand = Main.psuedoExpand(psuedo, addrMap);
        Assert.assertEquals(expand.length, expandTrue.length);
        Assert.assertEquals(expandTrue[0], expand[0]);
        Assert.assertEquals(expandTrue[1], expand[1]);
    }
    @Test
    public void testlifive() {
        String psuedo = "li $t0, 0x12345";
        String[] expandTrue = {"lui $at, 0x0001", "ori $t0, $at, 0x2345"};
        String[] expand = Main.psuedoExpand(psuedo, addrMap);
        Assert.assertEquals(expand.length, expandTrue.length);
        Assert.assertEquals(expandTrue[0], expand[0]);
        Assert.assertEquals(expandTrue[1], expand[1]);
    }

    @Test
    public void testlizero() {
        String psuedo = "li $t0, 0";
        String[] expandTrue = {"addiu $t0, $0, 0x0000", ""};
        String[] expand = Main.psuedoExpand(psuedo, addrMap);
        Assert.assertEquals(expand.length, expandTrue.length);
        Assert.assertEquals(expandTrue[0], expand[0]);
        Assert.assertEquals(expandTrue[1], expand[1]);
    }

    @Test
    public void testlitwo() {
        String psuedo = "li $t0, 18";
        String[] expandTrue = {"addiu $t0, $0, 0x0012", ""};
        String[] expand = Main.psuedoExpand(psuedo, addrMap);
        Assert.assertEquals(expand.length, expandTrue.length);
        Assert.assertEquals(expandTrue[0], expand[0]);
        Assert.assertEquals(expandTrue[1], expand[1]);
    }

    @Test
    public void testmovebasic() {
        String psuedo = "move $t0, $t1";
        String[] expandTrue = {"add $t0, $t1, $0", ""};
        String[] expand = Main.psuedoExpand(psuedo, addrMap);
        Assert.assertEquals(expand.length, expandTrue.length);
        Assert.assertEquals(expandTrue[0], expand[0]);
        Assert.assertEquals(expandTrue[1], expand[1]);
    }

    @Test
    public void testlabasic() {
        String psuedo = "la $t0, label";
        addrMap.put("label", 0x10010000);
        String[] expandTrue = {"lui $at, 0x1001", "ori $t0, $at, 0x0000"};
        String[] expand = Main.psuedoExpand(psuedo, addrMap);
        Assert.assertEquals(expand.length, expandTrue.length);
        Assert.assertEquals(expandTrue[0], expand[0]);
        Assert.assertEquals(expandTrue[1], expand[1]);
    }

    @Test
    public void testlanotstart() {
        String psuedo = "la $t0, label";
        addrMap.put("label", 0x10010004);
        String[] expandTrue = {"lui $at, 0x1001", "ori $t0, $at, 0x0004"};
        String[] expand = Main.psuedoExpand(psuedo, addrMap);
        Assert.assertEquals(expand.length, expandTrue.length);
        Assert.assertEquals(expandTrue[0], expand[0]);
        Assert.assertEquals(expandTrue[1], expand[1]);
    }

    @Test
    public void testlaFUNKY() {
        String psuedo = "la $t0, label";
        addrMap.put("label", 0x00400016);
        String[] expandTrue = {"lui $at, 0x0040", "ori $t0, $at, 0x0016"};
        String[] expand = Main.psuedoExpand(psuedo, addrMap);
        Assert.assertEquals(expand.length, expandTrue.length);
        Assert.assertEquals(expandTrue[0], expand[0]);
        Assert.assertEquals(expandTrue[1], expand[1]);
    }

    @Test
    public void testlanomap() {
        String psuedo = "la $t0, label";
        String[] expandTrue = {"lui $at, 0x0040", "ori $t0, $at, label"};
        String[] expand = Main.psuedoExpand(psuedo, addrMap);
        Assert.assertEquals(expand.length, expandTrue.length);
        Assert.assertEquals(expandTrue[0], expand[0]);
        Assert.assertEquals(expandTrue[1], expand[1]);
    }

    @Test
    public void bltbasic() {
        String psuedo = "blt $t0, $t1, label";
        String[] expandTrue = {"slt $1, $t0, $t1", "bne $1, $0, label"};
        String[] expand = Main.psuedoExpand(psuedo, addrMap);
        Assert.assertEquals(expand.length, expandTrue.length);
        Assert.assertEquals(expandTrue[0], expand[0]);
        Assert.assertEquals(expandTrue[1], expand[1]);
    }



}
