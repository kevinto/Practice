/**
 * Created by kevinto on 11/19/16.
 */
public class SortAllCharInString {
    public static void main(String[] args) {
        String test1 = "This is easy";
        System.out.println(sortCharacters(test1));
    }

    static String sortCharacters(String str) {
        if (str.length() == 0) {
            return str;
        }

        int[] map = new int[256];
        loadFreqMap(map, str);

        return mapToStr(map);
    }

    static void loadFreqMap(int[] map, String str) {
        int strLen = str.length();
        int i = 0;

        while (i < strLen && !isDelimiter(str.charAt(i))) {
            map[str.charAt(i)]++;
            i++;
        }
    }

    static boolean isDelimiter(char value) {
        return (value == 0 || value == 10);
    }

    static String mapToStr(int[] map) {
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i < map.length; i++) {
            if (i == 10 || map[i] == 0) {
                continue;
            }

            // Add all freq of the same letter
            for (int j = 0; j < map[i]; j++) {
                sb.append((char)i);
            }
        }

        return sb.toString();
    }
}
