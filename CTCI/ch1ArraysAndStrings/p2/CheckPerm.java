public class CheckPerm {
    public static void main (String[] args) {
        System.out.println(isPerm("abc", "bac"));
        System.out.println(isPerm("abc", "bace"));
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
}