/**
 * Created by kevinto on 12/21/16.
 */
public class RegexMatcher {
    public static void main(String[] args) {

        boolean res = isMatchRecursive("aab","c*a*b");

        int errors = 0;
        for (int i = 0; i < tests.length; ++i) {
            boolean result = stringMatching(tests[i].r, tests[i].s);
            if (result != tests[i].result) {
                System.out.println("Error: result of test case number " + i + " is " + tests[i].result + ". Got " + result + " instead");
                errors++;
            }
        }

        if (errors > 0)
            System.out.println("Got " + errors + " errors");
        else
            System.out.println("Good work");
    }

    // My recursive implementation. Which is wrong and long....
    static boolean isMatchRecursive(String text, String pat) {
        return isMatchRecursive(text, text.length() - 1, pat, pat.length() - 1);
    }

    static boolean isMatchRecursive(String text, int textPtr, String pat, int patPtr) {
        if (patPtr < 0 && textPtr < 0) {
            return true;
        }else if (textPtr < 0 || patPtr < 0) {
            return false;
        }

        boolean result = false;
        if (pat.charAt(patPtr) != '.' && pat.charAt(patPtr) != '*') {
            if (text.charAt(textPtr) == pat.charAt(patPtr)) {
                result = isMatchRecursive(text, textPtr - 1, pat, patPtr - 1);
            } else {
                result = false;
            }
        } else if (pat.charAt(patPtr) == '.') {
            result = isMatchRecursive(text, textPtr - 1, pat, patPtr - 1);
        } else if (pat.charAt(patPtr) == '*') {
            // Represents 0 of something. so skip over that something in the pat too
            result = isMatchRecursive(text, textPtr, pat, patPtr - 2);

            if (patPtr == 0) {
                return true;
            } else if (pat.charAt(patPtr - 1) == '.') {
                char curr = text.charAt(textPtr);
                for (int i = textPtr; i >= 0; i--) {
                    if (text.charAt(i) != curr) {
                        break;
                    }

                    result |= isMatchRecursive(text, i - 1, pat, patPtr - 2);
                }

                if (patPtr - 1 == 0) {
                    return true;
                }
            } else {
                char curr = pat.charAt(patPtr - 1);
                for (int i = textPtr; i >= 0; i--) {
                    if (text.charAt(i) != curr) {
                        break;
                    }

                    result |= isMatchRecursive(text, i - 1, pat, patPtr - 1);
                }
            }
        }

        return result;
    }

    public static boolean stringMatching(String pattern, String text) {
        if (pattern == null || text == null)
            return false;

        // Start from the beginning of both strings.
        return stringMatching(pattern, 0, text, 0);
    }

    private static boolean stringMatching(String pattern, int patternIndex, String text, int textIndex) {
        if (patternIndex == pattern.length()) {
            // Return true, if we are at the end of both the pattern and the text
            return (textIndex == text.length());
        }

        // Pat len check so we can access the *
        if (patternIndex + 1 < pattern.length() && pattern.charAt(patternIndex + 1) == '*') {
            // The next character over is a '*'.
            // We either:
            // 1. skip over it (meaning that there is 0 occurrences of whatever letter)
            // OR
            // 2. check if the pre '*" char matches the current letter OR "." match. we move on the text string.
            //    if we already reached the end, don't go on. Confusing part is that .* means 0 or more of
            //    the wild card character NOT a single instance of the any specific character!!!!
            // Note : Java && has higher precedence than ||.
            return (stringMatching(pattern, patternIndex + 2, text, textIndex)
                    ||
                    // If the char matches the char right before the '*', we are going to keep the
                    // pattern pointer the same and move the text pointer only. We move the text pointer until we can ignore the multiple instances
                    (textIndex != text.length() &&
                            (pattern.charAt(patternIndex) == text.charAt(textIndex) || pattern.charAt(patternIndex) == '.')
                            && stringMatching(pattern, patternIndex, text, textIndex + 1)));
        }

        if (textIndex != text.length() && (pattern.charAt(patternIndex) == text.charAt(textIndex) || pattern.charAt(patternIndex) == '.' )) {
            // Both characters match or we hit a '.'
            return stringMatching(pattern, patternIndex + 1, text, textIndex + 1);
        }

        return false;
    }



    private static class TestCase {
        public TestCase(String r, String s, boolean result) {
            this.r = r;
            this.s = s;
            this.result = result;
        }

        String r, s;
        boolean result;
    }

    private static TestCase tests[] = {
//            new TestCase("yuval", "yuval", true),
//            new TestCase("yuval", "yuva", false),
//            new TestCase("yuv", "yuval", false),
//            new TestCase("", "yuval", false),
//            new TestCase("yuval", "", false),
//            new TestCase(".....", "yuval", true),
//            new TestCase("yuvb*al", "yuval", true),
//            new TestCase(".*", "yuval", true),
//            new TestCase("a*b*c*yuval.*a*b*c*", "yuval", true),
            new TestCase("a*abc", "aaaabc", true)
    };
}
