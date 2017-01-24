package p131;

/**
 * Created by kevinto on 1/24/17.
 */
public class SentenceScreenFitting {
    public static void main(String[] args) {
        String[] words = {"a", "bcd", "e"};
        System.out.println(wordsTypingBruteForce(words, 3, 6));
    }

    public static int wordsTypingBruteForce(String[] words, int rows, int cols) {
        if (words == null || words.length == 0) {
            return -1;
        }

        int row = 0;
        int pos = 0;
        int sum = 0;
        int count = 0;
        while (row < rows) {
            if (sum == 0 && (words[pos].length() > cols)) {
                System.out.println("Words will never fit.");
                return -1;
            } else if (sum + words[pos].length() + 1 > cols) {
                sum = 0;
                row++;
                continue;
            }

            sum += sum == 0 ? words[pos].length() : words[pos].length() + 1;
            pos++;

            if (pos >= words.length) {
                pos = 0;
                count++;
            }
        }

        return count;
    }
}
