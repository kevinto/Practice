import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kevin on 7/24/16.
 */
public class WordJustify {
    public static void main(String[] args) {
        ArrayList<String> testarr = new ArrayList<>();
        testarr.add("This");
        testarr.add("is");
        testarr.add("an");
        testarr.add("example");
        testarr.add("of");
        testarr.add("text");
        testarr.add("justification");
        System.out.println(new WordJustify().fullJustify(testarr, 16));
    }

    public ArrayList<String> fullJustify(ArrayList<String> words, int L) {
        ArrayList<String> lines = new ArrayList<>();

        int start = 0;
        // Keep track of the start and end of words we are going to add
        // to the new line. last is excluded.
        while (start < words.size()) {
            // we are going to inc last until we fit enough full words into
            // the line.
            int count = words.get(start).length();
            int last = start + 1;
            while (last < words.size()) {
                if (words.get(last).length() + count + 1 > L) break;
                count += words.get(last).length() + 1; // added 1 to account for the space.
                last++;
            }

            StringBuilder builder = new StringBuilder();
            int diff = last - start - 1; // remember we do not include last.
            // if last line or number of words in the line is 1, left-justified
            if (last == words.size() || diff == 0) {
                for (int i = start; i < last; i++) {
                    builder.append(words.get(i) + " ");
                }
                builder.deleteCharAt(builder.length() - 1); // remove the ending space.
                for (int i = builder.length(); i < L; i++) {
                    // left justified so pad with spaces.
                    builder.append(" ");
                }
            } else {
                // middle justified
                int spaces = (L - count) / diff; // Gets the rounded number of spaces.
                int r = (L - count) % diff; // gets the remainder if the division wasn't clean.
                for (int i = start; i < last; i++) {
                    builder.append(words.get(i));
                    if (i < last - 1) {
                        // We are not at the last word.
                        for (int j = 0; j <= (spaces + ((i - start) < r ? 1 : 0)); j++) {
                            builder.append(" ");
                        }
                    }
                }
            }
            lines.add(builder.toString());
            start = last;
        }


        return lines;
    }
}
