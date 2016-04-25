import java.math.BigInteger;

/**
 * Created by Kevin on 4/24/16.
 *
 * Write Up:
 * Link:  http://www.codewars.com/kata/56a127b14d9687bba200004d/train/java
 * Elaboration: http://math.stackexchange.com/questions/636128/calculating-the-number-of-possible-paths-through-some-squares
 *
 * Lets say we have m = #rows and n = #columns
 * For example, let's say m = 2 and n = 3. How many paths can there be
 * if we start from the lower left corner to the upper right corner?
 * Let's also call the lower left corner "A" and the upper right
 * corner "B".
 *
 * We can say that it always takes 5 steps to traverse from A to B.
 * We can also say that there will always be 2 "ups" and 3 "rights".
 * Remember we are traversing on the edges of each square, not
 * within each square itself.
 *
 * Now this boils down to two situations:
 * 1. There are 5 open positions how many different combinations
 *    of 2 "ups" can we have within those 5 open positions?
 * 2. There are 5 open positions how many different combinations
 *    of 3 "rights" can we have within those 5 open positions?
 * Notice that we only have to solve one of those situations to
 * get the answer.
 *
 * We use combinations instead of permutations because a permutation
 * implies that ordering is unqiue. Let's say we have the path:
 * 1U 1R 2U 2R 3R. If we are using permutations, then that means that
 * 2U 1R 1U 2R 3R is a different path. Permutations say that the 2 Ups
 * are completely different. We use the combinations concept to say
 * that the UPs are the same.
 *
 * The problem is solved in two ways:
 * 5 C 2 -> Choosing 2 ups
 * 5 C 3 -> Choosing 3 downs
 *
 */
public class NumRoutes {
    public static void main(String[] args) {
        System.out.println(numberOfRoutes(3, 2));
        System.out.println(numberOfRoutes(6, 6));
    }

    public static BigInteger numberOfRoutes(long n, long m) {
        BigInteger upper = BigInteger.ONE;
        long highest = (m > n) ? m : n;
        long lowest = (m < n) ? m : n;

        for (long i = highest + 1; i <= highest + lowest; i++) {
            upper = upper.multiply(BigInteger.valueOf(i));
        }

        return upper.divide(factorial(BigInteger.valueOf(lowest)));
    }

    public static BigInteger factorial(BigInteger number) {

        if (number.compareTo(BigInteger.ONE) == -1) {
            // number passed in is less than 1
            return BigInteger.ONE;
        }
        else {
            return number.multiply(factorial(number.subtract(BigInteger.ONE)));
        }
    }
}
