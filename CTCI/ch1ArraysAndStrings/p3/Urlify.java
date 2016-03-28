public class Urlify {
    
  public static char[] urlify(char[] str, int length) {
    int numSpaces = countSpaces(str, length);
    int endPtr = length - 1 + numSpaces * 2;
    for (int i = length - 1; i >= 0; i--) {
      if (str[i] == ' ') {
        str[endPtr] = '0';
        str[endPtr - 1] = '2';
        str[endPtr - 2] = '%';
        endPtr -= 3;
      } else {
        str[endPtr] = str[i];
        endPtr--;
      }
    }
    return str;
  }
  
  private static int countSpaces(char[] str, int length) {
    int numSpaces = 0;
    for (int i = 0; i < length; i++) {
      if (str[i] == ' ') {
        numSpaces++;
      }
    }
    return numSpaces;
  }
  
  public static void main(String[] args) {
    String testString = "Mr John Smith";
    char[] testCharArr = testString.toCharArray();
    System.out.println(urlify(testCharArr, 13));
  }
}