package projecteulersolutions;

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

    //==================================================
    // BOOLEAN CALCULATIONS
    // Methods which return whether a provided value has
    // the specified characteristic.
    //==================================================
    //==============================
    // isPalindrome
    //==============================
    // Return whether the provided
    // String is palindromic
    //==============================
    static boolean isPalindrome(String str) {
        str = str.toLowerCase();

        int length = str.length();
        for (int i = 0; i < length / 2; i++) {
            if (str.charAt(i) != str.charAt(length - i - 1)) {
                return false;
            }
        }
        return true;
    }

    //==============================
    // isPandigital
    //==============================
    // Return whether the provided
    // integer is pandigital.
    // Helper methods:
    // getDigitArray(long)
    // containsInt(int[], int)
    //==============================
    static boolean isPandigital(int n) {
        int length = EulerMath.getDigitCount(n);
        int[] digitArr = getDigitArray(n);
        for (int i = 0; i < length; i++) {
            if (!containsInt(digitArr, i + 1)) {
                return false;
            }
        }
        return true;
    }

    //====================
    // getDigitArray
    //====================
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

    //====================
    // containsInt
    //====================
    private static boolean containsInt(int[] arr, int d) {
        for (int i : arr) {
            if (i == d) {
                return true;
            }
        }
        return false;
    }

    //==============================
    // isPrime
    //==============================
    // Returns whether the provided
    // integer is prime.
    //==============================
    static boolean isPrime(int n) {
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

    //==============================
    // isPythagorean
    //==============================
    // Returns whether the provided
    // integers form a Pythagorean
    // triplet.
    //==============================
    static boolean isPythagorean(int a, int b, int c) {
        return a * a + b * b == c * c;
    }

    //==================================================
    // CALCULATION GETTER METHODS
    //==================================================
    // These methods return a calculated value determined
    // by provided values of various data types and
    // preestablished algorithms, formulas, patterns,
    // and mathematical principles
    //==================================================
    //==============================
    // getDigitCount
    //==============================
    // Returns the number of digits
    // of a provided long
    //==============================
    static int getDigitCount(long n) {
        int count = 0;
        while (n > 0) {
            count++;
            n /= 10;
        }
        return count;
    }

    //==============================
    // getFactorial
    //==============================
    // Returns the factorial of a
    // provided int as a double
    //==============================
    static double getFactorial(int num) {
        if (num <= 1) {
            return 1;
        }
        return num * getFactorial(num - 1);
    }

    //==============================
    // getGCD
    //==============================
    // Returns the greatest common
    // denominator of two provided
    // integer values.
    //==============================
    static int getGCD(int a, int b) {
        if (b > a) {
            int t = a;
            a = b;
            b = t;
        }

        while (b != 0) {
            int t = b;
            b = a % b;
            a = t;
        }

        return a;
    }
    
    //==============================
    // getLCM
    //==============================
    // Returns the least common
    // multiple of two provided
    // integer values.
    //==============================
    static int getLCM(int a, int b) {
        return a * b / getGCD(a, b);
    }

    //==============================
    // getNextPrime
    //==============================
    // Returns the next-encountered
    // prime integer, ascending from
    // the provided integer.
    //==============================
    static int getNextPrime(int n) {
        int nextPrime = n + 1;
        while (!isPrime(nextPrime)) {
            nextPrime++;
        }
        return nextPrime;
    }
}
