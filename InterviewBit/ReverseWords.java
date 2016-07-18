/**
 * Created by Kevin on 7/17/16.
 */
public class ReverseWords {
    public static void main(String[] args) {
        String test1 = "  i am   robot  ";
        System.out.println(reverseWords(test1));

        String test2 = "hello word";
        System.out.println(reverseWords(test2));
    }

    public static String reverseWords(String original) {
        char[] arr = original.toCharArray();

        // Reverse the words first, then reverse the entire array
        for (int i = 0; i < arr.length; i++) {
            while (i < arr.length && arr[i] == ' ') {
                i++;
            }

            int start = i;
            int end = -1;
            while (i < arr.length && arr[i] != ' ') {
                i++;
            }
            end = i - 1;
            if (end < start) break;

            reverse(arr, start, end);
        }

        reverse(arr, 0, arr.length - 1);

        return removeExtraSpaces(arr);
    }

    public static void reverse(char[] arr, int start, int end) {
        char temp;
        for (int i = 0; i < (end - start + 1) / 2; i++) {
            temp = arr[start + i];
            arr[start + i] = arr[end - i];
            arr[end - i] = temp;
        }
    }

    public static String removeExtraSpaces(char[] arr) {
        int index = 0;
        boolean previouslyFoundWord = false;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] != ' ') {
                arr[index++] = arr[i];
                previouslyFoundWord = true;
            } else {
                if (previouslyFoundWord) arr[index++] = ' ';
                previouslyFoundWord = false;
            }
        }

        if (index - 1 >= 0 && index - 1 < arr.length && arr[index - 1] == ' ') {
            // Checks if the last index is a space. Goes back 1 if it is.
            index--;
        }

        return new String(arr, 0, index);
    }
}