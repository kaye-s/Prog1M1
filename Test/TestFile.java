import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner; // Import the Scanner class to read text files
import org.junit.Assert;
import org.junit.Test;



public class TestFile {
    @Test
    public void testMain() {
        try {
            File myObj = new File("C:\\Users\\imbri\\IdeaProjects\\Prog1M1\\Test\\test_instructions.txt");
            Scanner myReader = new Scanner(myObj);
            int count = 0;
            Map<String, Integer> map = new HashMap<String, Integer>();
            map = Main.createMap(map);
            while (myReader.hasNextLine()) {
                ++count;
                String data = myReader.nextLine();
                String result = data.substring(0, 8);
                String input = data.substring(8);

                System.out.println("-----------------------------");
                System.out.println("Test Num: " + count);
                System.out.println("Input: " + input);
                System.out.println("Expected: " + result);
                System.out.print("Actual: ");
//               Main.main(input);
                int resInt = Main.stringToHex(input, map);
                String resString = String.format("%08x", resInt);
                System.out.println(resString);
                Assert.assertEquals(result, resString);
                System.out.println("-----------------------------");
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}