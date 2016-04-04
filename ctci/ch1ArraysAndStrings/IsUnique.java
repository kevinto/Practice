package ch1ArraysAndStrings;

/*
 * Problem 1.2 Statement:
 * Check Permutation: Given two strings, write a method to decide if one is a permutation of the other.
 */

public class IsUnique {
    public static void main (String[] args) {
        runTests();
    }
    
    public static boolean stringIsUnique(String str) {
        boolean[] letterExists = new boolean[26];
        char[] stringArr = str.toCharArray();
        
        for (int i = 0; i < stringArr.length; i++) {
            int intChar = (int) stringArr[i];
            int mapChar = intChar % 97;
            
            if (mapChar < 0 || 25 < mapChar) {
                continue;
            }
            
            if (letterExists[mapChar]) {
                return false;
            }
            else {
                letterExists[mapChar] = true;
            }
        }
        
        return true;
    }
    
    public static void runTests() {
        boolean testsPassed = true;
        testsPassed &= stringIsUnique("loae");
        testsPassed &= !stringIsUnique("lol");
        testsPassed &= !stringIsUnique("hello");
        testsPassed &= stringIsUnique("bye");
        
        if (testsPassed) {
            System.out.println("All tests passed!");
        }
        else {
            System.out.println("One or more tests failed...");      
        }
    }
}