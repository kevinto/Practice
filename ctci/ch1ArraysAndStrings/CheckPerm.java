package ch1ArraysAndStrings;
/*
 * Problem 1.1
 * Problem 1.1 Statement:
 * Is Unique: Implement an algorithm to determine if a string has all unique characters. 
 *            What if you cannot use additional data structures?
 */
public class CheckPerm {
    public static void main (String[] args) {
        runTests();
    }
    
    public static boolean isPerm(String s1, String s2) {
        if (s1.length() != s2.length()) {
            return false;
        }
        
        int[] map = new int[26];
        for (int i = 0; i < s1.length(); i++) {
            char curChar = s1.charAt(i);
            int curInt = (int) curChar % 97;
            
            if (curInt < 0 || 25 < curInt) {
                return false;
            }
            else {
                map[curInt] += 1;
            }
        }
        
        for (int i = 0; i < s2.length(); i++) {
            char curChar = (char) s2.charAt(i);
            int curInt = ((int) curChar) % 97;
            
            if (curInt < 0 || 25 < curInt) {
                return false;
            }
            else {
                map[curInt] -= 1;
            }
        }
        
        for (int i = 0; i < map.length; i++) {
            if (map[i] != 0) {
                return false;
            }
        }
        
        return true;
    }
    
    public static void runTests() {
        boolean result = true;
        
        result &= isPerm("abc", "bac");
        result &= !isPerm("abc", "bace");
        result &= !isPerm("abc", "bae");
        
        if (result) {
            System.out.println("All tests passed!");
        }
        else {
            System.out.println("One or more tests failed...");
        }
    }
}