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

public class TestData {
    @Test
    public void testSimple() {
        Map<String, Integer> addrMap = new HashMap<>();
        try {
            String inputString = "C:\\Users\\sofia\\EvenOrOdd.asm";
            File input = new File(inputString);
            //Convert filename to filename - .txt
            String filename = inputString.substring(0, inputString.lastIndexOf("."));
            Scanner myReader = new Scanner(input);
            FileWriter dataWriter = new FileWriter(filename + ".data");
            addrMap = Main.data(myReader, dataWriter);
            dataWriter.close();
            myReader.close();
        } catch (Exception e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        Map<String, Integer> testMap = new HashMap<>();
        testMap.put("input_request", 0x10010000);
        testMap.put("even_output", 0x10010015);
        testMap.put("odd_output", 0x1001002b);
        Assert.assertEquals(testMap.get("input_request"), addrMap.get("input_request"));
        Assert.assertEquals(testMap.get("even_output"), addrMap.get("even_output"));
        Assert.assertEquals(testMap.get("odd_output"), addrMap.get("odd_output"));
    }
}