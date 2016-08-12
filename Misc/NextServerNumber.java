import java.util.Arrays;
import java.util.HashMap;
import java.util.TreeSet;

/**
 * Created by kevint on 8/11/2016.
 */
public class NextServerNumber {
    public static void main(String[] args) {
        System.out.println(allocate("hello"));
        System.out.println(allocate("hello"));
        System.out.println(allocate("hello"));
        System.out.println(deallocate("hello2"));
        System.out.println(allocate("hello"));
        System.out.println(allocate("hello"));

        Integer[] test1 = { 1, 3 };
        System.out.println("test1, expect 2: " + nextServerNumber(test1));

        Integer[] test2 = { 1 };
        System.out.println("test2, expect 2: " + nextServerNumber(test2));

        Integer[] test3 = { 1, 2, 3 };
        System.out.println("test3, expect 4: " + nextServerNumber(test3));

        Integer[] test4 = { 3, 2, 1 };
        System.out.println("test4, expect 4: " + nextServerNumber(test4));

        Integer[] test5 = { };
        System.out.println("test5, expect 1: " + nextServerNumber(test5));

        Integer[] test6 = { 5, 4, 4, 1, 2, 3};
        System.out.println("test6, expect 6: " + nextServerNumber(test6));

        Integer[] test7 = { 5, 4, 4, 1, 1, 3};
        System.out.println("test7, expect 2: " + nextServerNumber(test7));

        Integer[] test8 = { 2 };
        System.out.println("test8, expect 1: " + nextServerNumber(test8));

        Integer[] test9 = { -1, 2 };
        System.out.println("test8, expect 1: " + nextServerNumber(test8));
    }

    public static HashMap<String, TreeSet<Integer>> map = new HashMap<>();

    public static String allocate(String serverType) {
        if (serverType.charAt(serverType.length() - 1) >= '0'
                && serverType.charAt(serverType.length() - 1) <= '9') {
            return "Error: Server type as number at the end";
        }

        int serverNumber = 1;
        if (!map.containsKey(serverType)) {
            TreeSet<Integer> newSet = new TreeSet<>();
            newSet.add(serverNumber);
            map.put(serverType, newSet);
        } else {
            TreeSet<Integer> existingSet = map.get(serverType);
            Integer[] serverNumbers = existingSet.toArray(new Integer[existingSet.size()]);
            serverNumber = nextServerNumber(serverNumbers);
            existingSet.add(serverNumber);
        }

        return serverType + serverNumber;
    }

    public static String deallocate(String serverName) {
        if (serverName.charAt(serverName.length() - 1) < '0'
                || serverName.charAt(serverName.length() - 1) > '9') {
            return "Error: ServerName needs number specified";
        }

        String cleanServerName = getName(serverName);
        if (!map.containsKey(cleanServerName)) {
            return "Error: No such server found";
        }

        int serverNumber = getNumber(serverName);
        map.get(cleanServerName).remove(serverNumber);
        return null;
    }

    public static String getName(String serverName) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < serverName.length(); i++) {
            char currChar = serverName.charAt(i);
            if (currChar >= '0'
                    && currChar <= '9') {
                break;
            } else {
                sb.append(currChar);
            }
        }

        return sb.toString();
    }

    public static int getNumber(String serverName) {
        int number = 0;
        int factor = 1;
//        String strNum = "";
        for (int i = serverName.length() - 1; i >= 0; i--) {
            char currChar = serverName.charAt(i);
            if (serverName.charAt(i) < '0'
                    || serverName.charAt(i) > '9') {
                break;
            }
//            strNum = currChar + strNum;

            int currInt = Character.getNumericValue(currChar);
            number += currInt * factor;
            factor *= 10;
        }

        return number;
//        return Integer.parseInt(strNum);
    }

    public static int nextServerNumber(Integer[] nums) {
        if (nums == null || nums.length == 0) return 1;

        Arrays.sort(nums);

        int serverNum = 1;
        int index = 0;
        while (index < nums.length) {
            // Takes are of negative numbers. Also allows us to skip
            // past the duplicate numbers and reach the next number in
            // order.
            if (nums[index] < serverNum) {
                index++;
                continue;
            }

            if (nums[index] != serverNum) {
                return serverNum;
            }
            index++;
            serverNum++;
        }

        return nums[nums.length - 1] + 1;
    }
}
