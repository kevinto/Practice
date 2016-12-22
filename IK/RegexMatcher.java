/**
 * Created by kevinto on 12/21/16.
 */
public class RegexMatcher {
    public static boolean stringMatching(String pattern, String text) {
        if (pattern == null || text == null)
            return false;
        return stringMatching(pattern, 0, text, 0);
    }

    private static boolean stringMatching(String pattern, int patternIndex, String text, int textIndex) {
        if (patternIndex == pattern.length()) {
            // Return true, if we are at the end of both the pattern and the text
            return (textIndex == text.length());
        }

        if (patternIndex + 1 < pattern.length() && pattern.charAt(patternIndex + 1) == '*') {
            // The next character over is a '*'.
            // We either:
            // 1. skip over it (meaning that there is 0 occurrences of whatever letter)
            // OR
            // 2. check if the '*" char matches the current letter OR
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

    public static void main(String[] args) {
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
