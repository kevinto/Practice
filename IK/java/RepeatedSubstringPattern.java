/**
 * Created by kevinto on 2/16/17.
 */
public class RepeatedSubstringPattern {
    public boolean repeatedSubstringPattern(String str) {
        if (str == null || str.length() == 0) {
            return false;
        }

        for (int i = 0; i < str.length(); i++) {
            String substr = str.substring(0, i + 1);

            if (isRepeated(str, substr, i + 1)) {
                return true;
            }
        }

        return false;
    }

    private boolean isRepeated(String str, String substr, int start) {
        if (substr.length() == str.length()) {
            return false;
        } else if (start == str.length()) {
            return true;
        } else if (start + substr.length() - 1 >= str.length()) {
            return false;
        }

        int subI = 0;
        for (int i = start; i < str.length() && i < start + substr.length(); i++) {
            if (str.charAt(i) != substr.charAt(subI)) {
                return false;
            }
            subI++;
        }

        return isRepeated(str, substr, start + substr.length());
    }
}
