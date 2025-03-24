import org.junit.Assert;
import org.junit.Test;
import java.util.HashMap;
import java.util.Map;

public class TestParseLabelInstruction {
    @Test
    public void testParseLabelInstruction() {
        String arg = "label:";
        String[] result = {"label:", ""};
        Assert.assertEquals(result, Main.parseLabelInstruction(arg));
    }
    @Test
    public void testParseLabelInstructionComment() {
        String arg = "label: #Comment";
        String[] result = {"label:", ""};
        Assert.assertEquals(result, Main.parseLabelInstruction(arg));
    }
    @Test
    public void testParseLabelInstructionCommentSpace() {
        String arg = "label: \t\t     #Comment";
        String[] result = {"label:", ""};
        Assert.assertEquals(result, Main.parseLabelInstruction(arg));
    }
    @Test
    public void testParseLabelInstructionTrue() {
        String arg = "label: beq $t0, $t1, exit";
        String[] result = {"label:", "beq $t0, $t1, exit"};
        Assert.assertEquals(result, Main.parseLabelInstruction(arg));
    }
    @Test
    public void testParseLabelInstructionSpace() {
        String arg = "label: \t\t\t    beq $t0, $t1, exit";
        String[] result = {"label:", "beq $t0, $t1, exit"};
        Assert.assertEquals(result, Main.parseLabelInstruction(arg));
    }
    @Test
    public void testParseLabelInstructionCommentTrue() {
        String arg = "label: beq $t0, $t1, exit #Comment";
        String[] result = {"label:", "beq $t0, $t1, exit #Comment"};
        Assert.assertEquals(result, Main.parseLabelInstruction(arg));
    }
}