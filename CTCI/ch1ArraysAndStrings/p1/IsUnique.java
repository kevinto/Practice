public class IsUnique {
    public static void main (String[] args) {
        boolean result = stringIsUnique("loae");
        System.out.println(result);
    }
    
    public static boolean stringIsUnique(String str) {
        boolean[] tracker = new boolean[26];
        char[] stringArr = str.toCharArray();
        
        for (int i = 0; i < stringArr.length; i++) {
            int intChar = (int) stringArr[i];
            int mapChar = intChar % 97;
            
            if (mapChar < 0 || 25 < mapChar) {
                continue;
            }
            
            if (tracker[mapChar]) {
                return false;
            }
            else {
                tracker[mapChar] = true;
            }
        }
        
        return true;
    }
}