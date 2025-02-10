import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.Scanner; // Import the Scanner class to read text files
import org.junit.Assert;
import org.junit.Test;



public class TestFile {
    @Test
    public void testMain() {
        try {
            File myObj = new File("C:\\Users\\sofia\\IdeaProjects\\Prog1M1\\Test\\test_instructions.txt");
            Scanner myReader = new Scanner(myObj);
            int count = 0;
            while (myReader.hasNextLine()) {
                ++count;
                String data = myReader.nextLine();
                String result = data.substring(0, 8);
                String[] input = {data.substring(8)};

                System.out.println("-----------------------------");
                System.out.println("Test Num: " + count);
                System.out.println("Input: " + input[0]);
                System.out.println("Expected: " + result);
                System.out.print("Actual: ");
                Main.main(input);
                Assert.assertEquals(result, Main.final_result);
                System.out.println("-----------------------------");
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}