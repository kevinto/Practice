// Link: https://leetcode.com/problems/excel-sheet-column-number/
// Problem: Given a column title as appear in an Excel sheet, 
//          return its corresponding column number.

// Examples: (input -> output)
// A -> 1
// B -> 2
// C -> 3
// ...
// Z -> 26
// AA -> 27
// AB -> 28 

// Questions:
// 1. What is the pattern for the column names?
// 2. Is there a max column number?

import java.lang.*;

public class titleToNumber {
    public static void main(String[] args) {
        System.out.println("A: " + titleToNum("A"));
        System.out.println("Z: " + titleToNum("Z"));
        System.out.println("AA: " + titleToNum("AA"));
        System.out.println("AAA: " + titleToNum("AAA"));
    }
    
    public static int titleToNum(String str) {
        str = str.toUpperCase(); 
        int strLen = str.length();
        
        if (strLen == 1) {
            return ((int)(str.charAt(0)) % 65) + 1;
        }
       
        int columnNum = 0; 
        for (int i = 0; i < strLen; i++) {
            char currChar = str.charAt(i);
            int currCharValue = ((int)currChar % 65) + 1;
            
            if (i == strLen - 1) {
                columnNum += currCharValue; 
            }
            else {
                columnNum += Math.pow(26, strLen - i - 1) * currCharValue;
            }
        }
        
        return columnNum;
    }
}