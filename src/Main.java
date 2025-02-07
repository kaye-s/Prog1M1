import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        //args[1] = input string?

        System.out.println("Hello World");

        String input = "add $t0, $t1, $t2";
    }
    public static String[] parseString(String input) {
        //Getting rid of Comments
        if(input.contains("#")) {
            int count = 0;
            while(input.charAt(count) != '#') {
                ++count;
            }
            input = input.substring(0, count);
        }

        //Getting rid of whitespace
        input = input.trim();
        //"add      $t0,    $t1,     $t2"


        //Creating the array of strings
        String[] args = input.split(" ");
        for (int i = 0; i < args.length; ++i) {
            args[i] = args[i].trim();
        }
        ArrayList<String> temp = new ArrayList<String>();
        for(int i = 0; i < args.length; ++i) {
            if(!(args[i].contains(" "))) {
                temp.add(args[i]);
            }
        }
        System.out.println(temp.size());
        args = temp.toArray(new String[0]);

        //"and", " ", " ", " ", "$t0 . . .

        //Getting rid of commas
        //OP + 3
        //add $t0, $t1, $t2
        //args[] = {add $t0, $t1, $t2}
        if(args.length == 4) {
            args[1] = args[1].substring(0, args[1].length() - 1);
            args[2] = args[2].substring(0, args[2].length() - 1);
        }

        //OP + 2
        //li $t0, 41
        //args[] = {li $t0, 41}
        if(args.length == 3) {
            args[1] = args[1].substring(0, args[1].length() - 1);
        }
        return args;
    }
}