package projecteulersolutions;


/*
The goal of problem 41 is to find the largest n-digit pandigital prime
that exists. An n-digit pandigital number makes use of all the digits
1 to n exactly once.
For example, 2143 is a 4-digit pandigital number.
 */
public class Problem0041 extends Problem {

    /*
    When establishing an upper bound for this problem, I can assert that since the
    greatest pandigital number is 987654321, the upper bound is at most this value.
    However, every 9-digit pandigital number intrinsically has a digital sum of
    9 + 8 + 7 + 6 + 5 + 4 + 3 + 2 + 1 = 45.
    
    It is understood that any integer with a digital sum divisible by 3 is itself
    divisible by 3. Hence, for any given 9-digit pandigital number, it is not prime.
    
    Actually, any 9-, 8-, 6-, 5-, 3-, and 2-digit pandigital number will not be
    prime for this same reason.
    The only 1-digit pandigital number is 1, which is not prime.
    
    This means that our bounds can be narrowed to be only 4- and 7-digit pandigital
    numbers. Since we're looking for the LARGEST pandigital prime, the 4- digit
    numbers can be ommitted for convenience's sake.
    
    Therefore, we're just left with 7-digit numbers between 1234567 and 7654321.
    Our result is somewhere within these bounds.
     */
    private static final int LOWER_BOUND = 1234567;
    private static final int UPPER_BOUND = 7654321;

    @Override
    public boolean isSolved() {
        return true;
    }

    @Override
    public void printSolution() {
        /*
        Since we're looking for the LARGEST pandigital prime, we can start at
        the upper bound and work downwards, breaking when the first (and largest)
        result is found.
         */
        for (int i = UPPER_BOUND; i >= LOWER_BOUND; i--) {
            /*
            This checks if the number is pandigital and prime for EVERY number.
            I was trying to find a more efficient method but really this is the
            most straightfoward for the given problem.
             */
            if (isPandigital(i) && EulerMath.isPrime(i)) {
                System.out.println("The largest pandigital prime is " + i);
                break;
            }
        }
    }

    /*
    isPandigital(int) returns if the given int is pandigital.
    A number is pandigital if the digits contain all integers up to the length
    of the number. e.g. 4231 or 52431 or 958746312
     */
    private boolean isPandigital(int n) {
        // length of the given int in digits
        int length = EulerMath.getDigitCount(n);
        // array to hold the values of the digits
        int[] digitArr = getDigitArray(n);
        // for every number in the pandigital of the same length
        for (int i = 0; i < length; i++) {
            // does the digit array contain the particular digit
            // if not return false
            if (!containsDigit(digitArr, i + 1)) {
                return false;
            }
        }
        // else the given number is pandigital since the array of all digit values
        // contains every digit 1<=n<=length.
        return true;
    }

    /*
    getDigitArray(int) returns a given int as an array of its digits.
     */
    private int[] getDigitArray(int n) {
        // length of array used for array size and for loop
        int length = EulerMath.getDigitCount(n);
        // array to hold digits
        int[] arr = new int[length];
        int temp = n;
        // add the given digit to the array, starting at least significant index
        // of the array to match the fact that the digit finding algorithm works
        // smallest to largest
        for (int i = length - 1; i >= 0; i--) {
            arr[i] = temp % 10;
            temp /= 10;
        }
        return arr;
    }

    /*
    containsDigit(int[], int) returns if the given array contains the given int
     */
    private boolean containsDigit(int[] arr, int d) {
        for (int i : arr) {
            // if the given int matches any value of the array return true
            if (i == d) {
                return true;
            }
        }
        // array does not contain value, return false
        return false;
    }
}
