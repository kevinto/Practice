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
 * 5 C 2 -> Choosing 2 ups = (5!) / (2! * (8-2)!)
 * 5 C 3 -> Choosing 3 downs
 * How many ways can you choose 3U's and 2R's in 5 steps?
 *
 * For 5c2, it spells out how many ways can you choose 2 ups
 * from 5 steps
 *
 * Lets say you have 5 spaces. Let's name those spaces:
 * _1_ _2_ _3_ _4_ _5_
 *  Choose 2 of these spaces to put "U". This is a combo
 *  because 1,2 is the same as 2,1.
 *
 *  (m+n)c(m) = (m+n)!/(m! (n)!)
 *
 */
public class NumRoutes {
    public static void main(String[] args) {
        System.out.println(numberOfRoutes(3, 3));
        System.out.println(numberOfRoutes(2, 3));
        System.out.println(numberOfRoutes(6, 6));
    }

        public static BigInteger numberOfRoutes (int m, int n) {
            BigInteger numRoutes = BigInteger.ONE;
            long max = Math.max(m, n);
            long min = Math.min(m, n);

            for (long i = max + 1; i <= max + min; i++) {
                numRoutes = numRoutes.multiply(BigInteger.valueOf(i));
            }

            return numRoutes.divide(factorial(min));
        }

        public static BigInteger factorial(long val) {
            BigInteger product = BigInteger.ONE;
            BigInteger multiplier = BigInteger.valueOf(val);

            while (multiplier.compareTo(BigInteger.ZERO) >= 0) {
                product = product.multiply(multiplier);
                multiplier.subtract(BigInteger.ONE);
            }

            return product;
        }

//    public static BigInteger numberOfRoutes(long n, long m) {
//        BigInteger upper = BigInteger.ONE;
//
//        long highest = Math.max(n, m);
//        long lowest = Math.min(n,m);
//
//        for (long i = highest + 1; i <= highest + lowest; i++) {
//            upper = upper.multiply(BigInteger.valueOf(i));
//        }
//
//        return upper.divide(factorialRecursive(BigInteger.valueOf(lowest)));
//    }

    public static BigInteger factorialIterative(BigInteger number) {
        BigInteger sum = BigInteger.ONE;
        while (number.compareTo(BigInteger.ZERO) != 0) {
            sum = sum.multiply(number);
            number = number.subtract(BigInteger.ONE);
        }

        return sum;
    }

    public static BigInteger factorialRecursive(BigInteger number) {
        if (number.compareTo(BigInteger.ONE) == -1) {
            return BigInteger.ONE;
        }
        else {
            return number.multiply(factorialIterative(number.subtract(BigInteger.ONE)));
        }
    }

    // This implementation is simple to read but it does unncessary work.
    // The numberator is doing a (m+n)! when it only needs to to
    // (m+n)!/(m+n-m)!
    public static BigInteger numberOfRoutes2(long n, long m) {
        BigInteger upper = factorialIterative(BigInteger.valueOf(m + n));
        BigInteger lower = factorialIterative(BigInteger.valueOf(m)).multiply(factorialIterative(BigInteger.valueOf(n)));

        return upper.divide(lower);
    }
}
