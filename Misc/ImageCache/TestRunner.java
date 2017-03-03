package ImageCache;

import ImageCache.ImageCache;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by kevinto on 2/27/17.
 */
public class TestRunner {
    public static void main(String[] args) {
        try {
            runTests();
        } catch (Exception e) {
            // TODO: Write exception handling.
            System.out.println("Something bad occured");
        }

        runTests();
    }

    public static void runTests() {
        runNormalCaseTest();
    }

    private static void runNormalCaseTest() {
        try {
            ImageCacheTestCase testCase = readTestFromFile("/Users/kevinto/IdeaProjects/Practice/Algorithms/ImageCacheTestInput");

            ImageCache newCache = new ImageCache(testCase.cacheSizeLimit);

            // TODO: iterate through test of test.
        } catch (Exception e) {
            // TODO: Write exception handling.
            System.out.println("Something bad occured");
        }
    }

    public static ImageCacheTestCase readTestFromFile(String fileName) throws IOException {
        ImageCacheTestCase testCase = new ImageCacheTestCase();

        // TODO: This assumes input file is correct
        // File input Code taken from: http://stackoverflow.com/questions/4716503/reading-a-plain-text-file-in-java
        BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName));
        try {
            testCase.cacheSizeLimit = Integer.parseInt(bufferedReader.readLine());
            testCase.numUrls = Integer.parseInt(bufferedReader.readLine());

            for (int i = 0; i < testCase.numUrls; i++) {
                testCase.urls.add(bufferedReader.readLine());
            }
        } finally {
            bufferedReader.close();
        }

        return testCase;
    }
}

