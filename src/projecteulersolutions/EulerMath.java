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

    public static boolean isPandigital(int n) {
        int length = EulerMath.getDigitCount(n);
        int[] digitArr = getDigitArray(n);
        for (int i = 0; i < length; i++) {
            if (!containsInt(digitArr, i + 1)) {
                return false;
            }
        }
        return true;
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

    public static boolean isPythagorean(int a, int b, int c) {
        return a * a + b * b == c * c;
    }

    public static int getBigIntDigitCount(BigInteger n) {
        return n.toString().length();
    }

    public static int getBigIntDigitSum(BigInteger n) {
        int sum = 0;
        for (String str : n.toString().split("")) {
            sum += Integer.parseInt(str);
        }
        return sum;
    }

    public static BigInteger getBigIntFactorial(int n) {
        BigInteger factorial = BigInteger.ONE;

        for (int i = 2; i <= n; i++) {
            factorial = factorial.multiply(BigInteger.valueOf(i));
        }
        return factorial;
    }

    private static int[] getDigitArray(long n) {
        int length = EulerMath.getDigitCount(n);
        int[] arr = new int[length];
        long temp = n;
        for (int i = length - 1; i >= 0; i--) {
            arr[i] = (int) (temp % 10);
            temp /= 10;
        }
        return arr;
    }

    public static int getDigitCount(long n) {
        int count = 0;
        while (n > 0) {
            count++;
            n /= 10;
        }
        return count;
    }

    public static int getDigitProduct(long n) {
        int product = 1;
        while (n > 0) {
            product *= n % 10;
            n /= 10;
        }
        return product;
    }

    public static int getDigitSum(long n) {
        int sum = 0;
        while (n > 0) {
            sum += n % 10;
            n /= 10;
        }
        return sum;
    }

    public static double getFactorial(int num) {
        if (num <= 1) {
            return 1;
        }
        return num * getFactorial(num - 1);
    }

    public static int getNextPrime(int n) {
        int nextPrime = n + 1;
        while (!isPrime(nextPrime)) {
            nextPrime++;
        }
        return nextPrime;
    }

    private static boolean containsInt(int[] arr, int d) {
        for (int i : arr) {
            if (i == d) {
                return true;
            }
        }
        return false;
    }
}
