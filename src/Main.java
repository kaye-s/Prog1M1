import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Main {
    public static String final_result = "";
    public static int neg16 = 65535;
    public static int neg26 = 67108863;

    public static void main(String[] args) {
        //args[1] = input string?
        Map<String, Integer> map = new HashMap<String, Integer>();
        map = createMap(map);
        String[] input = parseString(args[0]);
        //System.out.println(Arrays.toString(input));
        int result = 0;

        if(input[0].equals("syscall")) {
            result = map.get("syscall");
        }
        if(input[0].equals("j")) {
            result = jType(input, map);
        }
        if(input[0].equals("add") || input[0].equals("and") || input[0].equals("or") || input[0].equals("slt") || input[0].equals("sub")) {
            result = rType(input, map);
        }
        if(input[0].equals("addiu") || input[0].equals("andi") || input[0].equals("ori")) {
            result = iTypeReg(input, map);
        }
        if(input[0].equals("beq") || input[0].equals("bne") || input[0].equals("lui")) {
            result = iTypeBranch(input, map);
        }
        if(input[0].equals("sw") || input[0].equals("lw")) {
            result = funkyType(input, map);
        }

        System.out.println(String.format("%08x", result));
        Main.final_result = String.format("%08x", result);
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
        String[] args = input.split(" ");
        for (int i = 0; i < args.length; ++i) {
            args[i] = args[i].trim();
        }

        ArrayList<String> temp = new ArrayList<String>();
        for(int i = 0; i < args.length; ++i) {
            if(!(args[i].isEmpty())) {
                temp.add(args[i]);
            }
        }

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