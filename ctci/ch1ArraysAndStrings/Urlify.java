package ch1ArraysAndStrings;

/*
 * ## Problem 1.3 Statement:
 * URLify: Write a method to replace all spaces in a string with '%20'. 
 * You may assume that the string has sufficient space at the end to 
 * hold the additional characters, and that you are given the "true" 
 * length of the string (Note: If implementing in Java, please use a 
 * character array so that you can perform this operation in place.)
 * Example:
 * 		Input: "Mr  John  Smith    ", 13
 * 		Output: "Mr%20John%20Smith"
 */

public class Urlify {
  // Assumptions:
    // 1. There is enough space in the original string to do in string replacement.
    // 2. There can be multiple spaces between words.
  
  public static void main(String[] args) {
    String testString = "Mr John Smith           ";
    char[] testCharArr = testString.toCharArray();
    System.out.println(urlify(testCharArr, 13));
  }
  
  public static char[] urlify(char[] str, int length) {
    int numSpaces = countSpaces(str, length);
    
    // Set a point where we can start writing characters to the final string.
    // This newLength calcuation makes each space take 3 characters.
    int newLength = length + (numSpaces * 2);
    str[newLength] = '\0'; // Adds a terminator to the end of the new string
   
    // Start iteratoring backwards from the length of the original string.
    // Populate backwards starting from newLength. We are essentially
    // tracking two parts of the string.
    for (int i = length - 1; i >= 0; i--) {
      if (str[i] == ' ') {
        str[newLength - 1] = '0';
        str[newLength - 2] = '2';
        str[newLength - 3] = '%';
        newLength -= 3;
      }
      else {
        str[newLength - 1] = str[i];
        newLength -= 1;
      }
    }
    
    return str;
  }
  
  public static int countSpaces(char[] str, int length) {
    int count = 0;
    for (int i = 0; i < length; i++) {
      if (str[i] == ' ') {
        count++;
      }
    }
    
    return count;
  }
}