import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.Scanner; // Import the Scanner class to read text files
import java.io.FileWriter;

public class Main {
    public static String final_result = "";
    public static int neg16 = 65535;
    public static int neg26 = 67108863;

    public static int dataStart = 0x10010000;

    public static void main(String[] args) {
        Map<String, Integer> map = new HashMap<>();
        map = createMap(map);
        try {
            File input = new File(args[0]);
            //Convert filename to filename - .txt
            String filename = args[0].substring(0, args[0].lastIndexOf("."));
            Scanner myReader = new Scanner(input);
            FileWriter dataWriter = new FileWriter(filename + ".data");
            //CALL DATA METHOD
            Map<String, Integer> addrMap = data(myReader, dataWriter);

            FileWriter textWriter = new FileWriter(filename + ".text");
            //CALL TEXT METHOD
            //text(myReader, textWriter, map, addrMap)

            //int result = stringToHex(data, map);
            //writer.write(String.format("%08x", result) + "\n");

            dataWriter.close();
            textWriter.close();
            myReader.close();
        } catch (Exception e) {
        System.out.println("An error occurred.");
        e.printStackTrace();
    }



       // System.out.println(String.format("%08x", result));
       // Main.final_result = String.format("%08x", result);
    }

    public static Map<String, Integer> data(Scanner myReader, FileWriter writer) {
        //Create writer (name of file.data)
        Map<String, Integer> addrMap = new HashMap<>();

        //Starting address
        int curAddr = dataStart;

        //Array to hold string elements
        ArrayList<Integer> charList = new ArrayList<>();

        while (myReader.hasNextLine()) {
            String data = myReader.nextLine();
            data = data.trim();
            System.out.println(data + "\n");
            if (data.equals("\n") || data.isEmpty()) {
                continue;
            }
            if (data.equals(".data") || data.isEmpty()) {
                continue;
            }
            if (data.charAt(0) == '#') {
                continue;
            }
            if(data.equals(".text"))
                break;

            //Find first label
            //parseLabel(combinedString) returns array [label, string]
                //Parse label (no colon) and string
            String[] array = parseLabel(data);

            //addToMap(label, string, int curAddr, addrMap) return new int curAddr
                //add label, curAddr to addrMap
                //Updates curAddr + (length of previous string + 1)
            curAddr = addToMap(array[0], array[1], curAddr, addrMap);

            String str = array[1] + '\0'; //[label, string]

            //Store each character as an int in an arrayList [11, 22, 33, 44, 55, 66, 77, 88]
            for(int i = 0; i < str.length(); ++i) {
                charList.add((int)str.charAt(i));
            }
            //STORE EVERYTHING
        }
        //Entire array of strings
        ArrayList<String> stringList = toLittleE(charList);

        //Print to file From Arraylist
        //For loop
        //  print element
        //  print newline
        try {
            for (int i = 0; i < stringList.size(); ++i) {
                writer.write(stringList.get(i) + '\n');
            }
            //Print to file 0s.
            //For loop
            //  print(8 0's
            //  print newline
            for (int i = stringList.size(); i < 1024; ++i) {
                writer.write("00000000" + '\n');
            }
        } catch(Exception e){
            System.out.println("An error occurred.");
        }

        return addrMap;
    }

    public static String[] parseLabel(String combinedString) {
        combinedString = combinedString.substring(0, combinedString.lastIndexOf("\""));

        //Find colon and create label (removing whitespace
        int colon = combinedString.indexOf(':');
        String label = combinedString.substring(0, colon);
        label = label.trim();

        //Find location of string and create rest, removing quotes
        // .asciiz "String
        String rest = combinedString.substring(colon+1);

        rest = rest.substring(rest.indexOf("\"")+1);
        return new String[]{label, rest};
    }

    public static int addToMap(String label, String s, int curAddr, Map<String, Integer> addrMap) {
        //addToMap(label, string, int curAddr, addrMap) return new int curAddr
            //add label, curAddr to addrMap
            //Updates curAddr + (length of previous string + 1)
        addrMap.put(label, curAddr);
        return (curAddr + s.length() + 1);
    }

    public static ArrayList<String> toLittleE(ArrayList<Integer> array) {
        //  Convert array to little eindian
        //  [11,22,33,44] -> [44332211]
        //  Last Element in newList must be 8 (00 buffered)
        int remain = 4- array.size() % 4;
        while (remain > 0 && remain <4){
            array.add(0);
            --remain;
        }
        String elem1;
        String elem2;
        String elem3;
        String elem4;

        ArrayList<String> newArray = new ArrayList<>();
        for(int i = 0; i <= array.size()-4; i = i+4) {
            elem1 = String.format("%02x", array.get(i));
            elem2 = String.format("%02x", array.get(i+1));
            elem3 = String.format("%02x", array.get(i+2));
            elem4 = String.format("%02x", array.get(i+3));
            newArray.add(elem4+elem3+elem2+elem1);
        }


        return newArray;
    }

    public static int stringToHex(String s, Map<String, Integer> map){
        String[] input = parseString(s);
        int result = 0;

        if(input[0].equals("syscall")) {
            result = map.get("syscall");
        }
        if(input[0].equals("j")) {
            //result = jType(input, map);
            result = -1;
        }
        if(input[0].equals("add") || input[0].equals("and") || input[0].equals("or") || input[0].equals("slt") || input[0].equals("sub")) {
            result = rType(input, map);
        }
        if(input[0].equals("addiu") || input[0].equals("andi") || input[0].equals("ori")) {
            result = iTypeReg(input, map);
        }
        if(input[0].equals("beq") || input[0].equals("bne") || input[0].equals("lui")) {
            //result = iTypeBranch(input, map);
            result = -1;
        }
        if(input[0].equals("sw") || input[0].equals("lw")) {
            result = funkyType(input, map);
        }

        return result;

    }
    public static int toNum(String s) {
        int num;
        if(s.length() > 2 && s.charAt(1) == 'x') {
            num = Integer.parseInt(s.substring(2), 16);
        } else {
            num = Integer.parseInt(s);
        }

        return num;
    }
    public static int jType(String[] args, Map<String, Integer> map) {
        int opcode = map.get("j");
        int instIndex = toNum(args[1]);
        int inst = 0;
        if (instIndex < 0){
            instIndex = instIndex & neg26;
        }

        inst = inst | (instIndex << 0);
        inst = inst | (opcode << 26);
        return inst;
    }

    public static int rType(String[] args, Map<String, Integer> map) {
        int opcode = 0;
        int rs = map.get(args[2]);
        int rt = map.get(args[3]);
        int rd = map.get(args[1]);
        int funct = map.get(args[0]);
        int inst = 0;

        inst = inst | (funct << 0);
        inst = inst | (rd << 11);
        inst = inst | (rt << 16);
        inst = inst | (rs << 21);
        inst = inst | (opcode << 26);
        return inst;
    }

    public static int iTypeReg(String[] args, Map<String, Integer> map) {
        int opcode = map.get(args[0]);
        int rs = map.get(args[2]);
        int rt = map.get(args[1]);
        int imm = toNum(args[3]);
        int inst = 0;
        if (imm < 0){
            imm = imm & neg16;
        }

        inst = inst | (imm << 0);
        inst = inst | (rt << 16);
        inst = inst | (rs << 21);
        inst = inst | (opcode << 26);
        return inst;
    }

    public static int iTypeBranch(String[] args, Map<String, Integer> map) {
        int opcode = map.get(args[0]);
        int rs;
        int rt;
        int imm;
        int inst = 0;

        if(args[0].equals("lui")) {
            rs = 0;
            rt = map.get(args[1]);
            imm = toNum(args[2]);

        } else{
            rs = map.get(args[1]);
            rt = map.get(args[2]);
            imm = toNum(args[3]);
        }
        if (imm < 0){
            imm = imm & neg16;
        }

        inst = inst | (imm << 0);
        inst = inst | (rt << 16);
        inst = inst | (rs << 21);
        inst = inst | (opcode << 26);
        return inst;
    }

    public static int funkyType(String[] args, Map<String, Integer> map) {
        int opcode = map.get(args[0]);
        int rt = map.get(args[1]);

        // parse args[2] - offset(base)
        String offsetStr = "";
        String baseStr = "";

        for (int i = 0; i < args[2].length(); ++i){
            if (args[2].charAt(i) == '('){
                offsetStr = args[2].substring(0, i);
                baseStr = args[2].substring(i+1, args[2].length()-1);
            }
        }
        int offset = 0;
        if (!offsetStr.isEmpty()){
            offset = toNum(offsetStr);
            if (offset < 0){
                offset = offset & neg16;
            }
        }
        int base = map.get(baseStr);

        int inst = 0;

        inst = inst | (offset << 0);
        inst = inst | (rt << 16);
        inst = inst | (base << 21);
        inst = inst | (opcode << 26);
        return inst;
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
        String[] args = input.split(",");
        String mnemonic = "";
        String firstArg = "";
        for (int i = 0; i < args[0].length(); ++i){
            if (args[0].charAt(i) == '\s'){
                mnemonic = args[0].substring(0, i);
                firstArg = args[0].substring(i+1);
                break;
            }
        }
        for (int i = 1; i < args.length; ++i) {
            args[i] = args[i].trim();
        }

        //"and", " ", " ", " ", "$t0 . . .
        ArrayList<String> temp = new ArrayList<String>();


        if (!(mnemonic.isEmpty() || firstArg.isEmpty())){
            temp.add(mnemonic.trim());
            temp.add(firstArg.trim());
        }
        else {
            temp.add(args[0]);
        }

        for(int i = 1; i < args.length; ++i) {
//            if(!(args[i].isEmpty() || args[i].equals(","))) {
//                if (args[i].charAt(args[i].length()-1) == ','){
//                    args[i] = args[i].substring(0,args[i].length()-1);
//                }
                temp.add(args[i]);

        }

        return temp.toArray(new String[0]);

        //Getting rid of commas
        //OP + 3
        //add $t0, $t1, $t2
        //args[] = {add $t0, $t1, $t2}
//        if(args.length == 4) {
//            args[1] = args[1].substring(0, args[1].length() - 1);
//            args[2] = args[2].substring(0, args[2].length() - 1);
//        }
//
//        //OP + 2
//        //li $t0, 41
//        //args[] = {li $t0, 41}
//        if(args.length == 3) {
//            args[1] = args[1].substring(0, args[1].length() - 1);
//        }
    }

    public static Map<String, Integer> createMap(Map<String, Integer> map) {

        // put opcodes into map
        map.put("add", 32);
        map.put("addiu", 9);
        map.put("and", 36);
        map.put("andi", 12);
        map.put("beq", 4);
        map.put("bne", 5);
        map.put("j", 2);
        map.put("lui", 15);
        map.put("lw", 35);
        map.put("or", 37);
        map.put("ori", 13);
        map.put("slt", 42);
        map.put("sub", 34);
        map.put("sw", 43);
        map.put("syscall", 12);

        // put registers into map
        map.put("$zero", 0);
        map.put("$0", 0);
        map.put("$at", 1);
        map.put("$1", 1);
        map.put("$v0", 2);
        map.put("$2", 2);
        map.put("$v1", 3);
        map.put("$3", 3);
        map.put("$a0", 4);
        map.put("$4", 4);
        map.put("$a1", 5);
        map.put("$5", 5);
        map.put("$a2", 6);
        map.put("$6", 6);
        map.put("$a3", 7);
        map.put("$7", 7);
        map.put("$t0", 8);
        map.put("$8", 8);
        map.put("$t1", 9);
        map.put("$9", 9);
        map.put("$t2", 10);
        map.put("$10", 10);
        map.put("$t3", 11);
        map.put("$11", 11);
        map.put("$t4", 12);
        map.put("$12", 12);
        map.put("$t5", 13);
        map.put("$13", 13);
        map.put("$t6", 14);
        map.put("$14", 14);
        map.put("$t7", 15);
        map.put("$15", 15);
        map.put("$s0", 16);
        map.put("$16", 16);
        map.put("$s1", 17);
        map.put("$17", 17);
        map.put("$s2", 18);
        map.put("$18", 18);
        map.put("$s3", 19);
        map.put("$19", 19);
        map.put("$s4", 20);
        map.put("$20", 20);
        map.put("$s5", 21);
        map.put("$21", 21);
        map.put("$s6", 22);
        map.put("$22", 22);
        map.put("$s7", 23);
        map.put("$23", 23);
        map.put("$t8", 24);
        map.put("$24", 24);
        map.put("$t9", 25);
        map.put("$25", 25);
        map.put("$k0", 26);
        map.put("$26", 26);
        map.put("$k1", 27);
        map.put("$27", 27);
        map.put("$gp", 28);
        map.put("$28", 28);
        map.put("$sp", 29);
        map.put("$29", 29);
        map.put("$fp", 30);
        map.put("$30", 30);
        map.put("$ra", 31);
        map.put("$31", 31);

        return map;
    }
}