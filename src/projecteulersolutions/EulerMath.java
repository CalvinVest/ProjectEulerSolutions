package projecteulersolutions;

import java.math.BigInteger;

/*
EulerMath is my own homebrew calculator to assist in solving
Project Euler problems by building a collection of useful mathematical
operations within a single class.

EulerMath is an interface since it's a utility class. Using an interface allows
me to prevent instantiation of the utility class without the usual use of a private
constructor and making the class final. Interfaces can have static classes, and
will not have any issues with instantiation.
 */
public interface EulerMath {

    public static BigInteger bigFactorial(int n) {
        BigInteger factorial = BigInteger.ONE;

        for (int i = 2; i <= n; i++) {
            factorial = factorial.multiply(BigInteger.valueOf(i));
        }
        return factorial;
    }

    public static double factorial(int num) {
        if (num <= 1) {
            return 1;
        }
        return num * factorial(num - 1);
    }

    public static boolean isPrime(int n) {
        if (n < 2) {
            return false;
        }
        if (n == 2 || n == 3) {
            return true;
        }
        if (n % 2 == 0 || n % 3 == 0) {
            return false;
        }
        int sqrtN = (int) Math.sqrt(n) + 1;
        for (int i = 6; i <= sqrtN; i += 6) {
            if (n % (i - 1) == 0 || n % (i + 1) == 0) {
                return false;
            }
        }
        return true;
    }
}
