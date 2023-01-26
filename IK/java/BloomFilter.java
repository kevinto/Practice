import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.BitSet;

/**
 * Created by kevinto on 1/22/17.
 *
 * The test file I am using is in the practice folder but not assigned
 * to this project. Filename: bloomtest.txt
 * This file should reside in the root folder not the ik folder,
 * but 1 level above.
 */
public class BloomFilter {
    private static BitSet BitArray;
    private static int Size;

    public static void main(String[] args) throws IOException {
        bloomInit(10);
        read();

        System.out.println("Hello: " + lookup("Hello"));
        System.out.println("hello: " + lookup("hello"));
        System.out.println("from: " + lookup("from"));
        System.out.println("1: " + lookup("1"));
    }

    private static boolean lookup(String str) {
        int hashCode = Arrays.hashCode(new Object[] { str });
        int bitIdx = hashCode % Size;

        return BitArray.get(bitIdx);
    }

    private static void add(String str) {
        int hashCode = Arrays.hashCode(new Object[] {str});
        int bitIndex = hashCode % Size;

        BitArray.set(bitIndex);
    }

    private static void bloomInit(int size) {
        BitArray = new BitSet();
        Size = 10;
    }

    private static void read() throws IOException {
        Path path = Paths.get("bloomtest.txt");
        try (BufferedReader reader = Files.newBufferedReader(path, StandardCharsets.UTF_8)) {
            String line = null;
            while ((line = reader.readLine()) != null) {
                addLine(line);
            }
        }
    }

    private static void addLine(String line) {
        String[] words = line.split(" ");
        for (String word : words) {
            add(word);
        }
    }
}
