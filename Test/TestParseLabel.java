import org.junit.Assert;
import org.junit.Test;
import java.util.HashMap;
import java.util.Map;

public class TestParseLabel {
    @Test
    public void testParseLabelStandard() {
        String arg = "hello_world: .asciiz \"hello world\"";
        String[] result = {"hello_world", "hello world"};
        Assert.assertEquals(result, Main.parseLabel(arg));
    }

    @Test
    public void testParseLabelStandardQuotes() {
        String arg = "hello_world: .asciiz \"hello world\"";
        String[] result = {"hello_world", "hello world"};
        Assert.assertEquals(result, Main.parseLabel(arg));
    }

    @Test
    public void testParseLabelWithSpaces() {
        String arg = "hello_world:    .asciiz    \"hello world\"";
        String[] result = {"hello_world", "hello world"};
        Assert.assertEquals(result, Main.parseLabel(arg));
    }

    @Test
    public void testParseLabelWithSpacesAndTabs() {
        String arg = "hello_world:  \t\t  .asciiz \t \t   \"hello world\"";
        String[] result = {"hello_world", "hello world"};
        Assert.assertEquals(result, Main.parseLabel(arg));
    }

    @Test
    public void testParseLabelWithTabs() {
        String arg = "hello_world:\t.asciiz\t\t\"hello world\"";
        String[] result = {"hello_world", "hello world"};
        Assert.assertEquals(result, Main.parseLabel(arg));
    }

    @Test
    public void testParseLabelWithSpacesBetweenColon() {
        String arg = "hello_world   : \t.asciiz \t\t\"hello world\"";
        String[] result = {"hello_world", "hello world"};
        Assert.assertEquals(result, Main.parseLabel(arg));
    }

    @Test
    public void testParseLabelWithTabsBetweenColon() {
        String arg = "hello_world\t\t: \t.asciiz\t\t\"hello world\"";
        String[] result = {"hello_world", "hello world"};
        Assert.assertEquals(result, Main.parseLabel(arg));
    }

    @Test
    public void testParseLabelWithSpacesAndTabsBetweenColon() {
        String arg = "hello_world  \t\t \t :\t.asciiz\t    \t\"hello world\"";
        String[] result = {"hello_world", "hello world"};
        Assert.assertEquals(result, Main.parseLabel(arg));
    }

    @Test
    public void testParseLabelWithSpacesAndTabsBetweenColonAndBeforeAndAfter() {
        String arg = "\t\t\t    hello_world  \t\t \t :\t.asciiz\t    \t\"hello world\"\t\t\t\t\t   \t\t\t";
        String[] result = {"hello_world", "hello world"};
        Assert.assertEquals(result, Main.parseLabel(arg));
    }
    @Test
    public void testParseLabelWithSpacesComment() {
        String arg = "\t\t\t    hello_world  \t\t \t :\t.asciiz\t    \t\"hello world\"# Comment";
        String[] result = {"hello_world", "hello world"};
        Assert.assertEquals(result, Main.parseLabel(arg));
    }

    @Test
    public void testParseLabelWithSpacesHash() {
        String arg = "\t\t\t    hello_world  \t\t \t :\t.asciiz\t    \t\"hello wor#ld\"# Comment";
        String[] result = {"hello_world", "hello wor#ld"};
        Assert.assertEquals(result, Main.parseLabel(arg));
    }

    @Test
    public void testParseLabelWithSpacesHashAndCom() {
        String arg = "\t\t\t    hello_world  \t\t \t :\t.asciiz\t    \t\"hello wor#ld\"";
        String[] result = {"hello_world", "hello wor#ld"};
        Assert.assertEquals(result, Main.parseLabel(arg));
    }

    @Test
    public void testParseLabelWithSpacesCommentTwoHash() {
        String arg = "\t\t\t    hello_world  \t\t \t :\t.asciiz\t    \t\"hello wor#ld\"#####COmment";
        String[] result = {"hello_world", "hello wor#ld"};
        Assert.assertEquals(result, Main.parseLabel(arg));
    }

    @Test
    public void testParseLabelWithSpacesCommentManyHash() {
        String arg = "\t\t\t    hello_world  \t\t \t :\t.asciiz\t    \t\"hello wor#ld\"#####COmment###";
        String[] result = {"hello_world", "hello wor#ld"};
        Assert.assertEquals(result, Main.parseLabel(arg));
    }

    @Test
    public void testParseLabelWithSpacesColon() {
        String arg = "\t\t\t    hello_world  \t\t \t :\t.asciiz\t    \t\"hello wor:ld\"";
        String[] result = {"hello_world", "hello wor:ld"};
        Assert.assertEquals(result, Main.parseLabel(arg));
    }
}
