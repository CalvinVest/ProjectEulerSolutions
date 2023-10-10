package projecteulersolutions;

/*
The number 197 is called a circular prime because all rotations of the digits:
197, 971, 719, are themselves prime.

There are thirteen such primes below 100: 2, 3, 5, 7, 11, 13, 17, 31, 37, 71, 73, 79, 97.
How many circular primes are there below one million?
 */
public class Problem0035 extends Problem {

    private static final int LIMIT = 1000000;

    /*
    I have an idea for the implementation of this solution.
    I think an efficient way of solving this problem is to make a sieve of Eratosthenes
    to give me all prime numbers, then I'll go through all found primes and determine
    if they are circular primes. if so, keep as true in the sieve.
    If not, set them to negative as if they weren't prime in the first place.
    The result is the number of trues in the sieve array.
     */
    @Override
    public boolean isSolved() {
        return false;
    }

    @Override
    public void printSolution() {
        // boolean array where prime indeces are true
        boolean[] sieve = sieveOfEratosthenes(LIMIT);

        // for all prime indeces of the sieve
        for (int i = 0; i < sieve.length; i++) {
            if (sieve[i]) {
                // if the given prime is not circular set to false
                if (!isCircularPrime(i)) {
                    sieve[i] = false;
                }
            }
        }
        // the resulting sieve array after this loop only shows true for circular
        // prime values instead of all primes

        // count of circular primes
        int count = 0;
        for (boolean b : sieve) {
            if (b) {
                count++;
            }
        }

        System.out.println("The number of circular primes below one million is " + count);
    }

    private boolean[] sieveOfEratosthenes(int n) {
        boolean[] truthArray = new boolean[n + 1];
        for (int i = 2; i <= n; i++) {
            truthArray[i] = true;
        }

        for (int i = 2; i * i <= n; i++) {
            if (truthArray[i]) {
                for (int j = i * i; j <= n; j += i) {
                    truthArray[j] = false;
                }
            }
        }
        return truthArray;
    }

    /*
    isCircularPrime(int) returns whether the given
    number is prime for all rotations of the given number.
     */
    private boolean isCircularPrime(int n) {
        // all rotations in an array
        int[] rotations = getRotations(n);

        // for each rotation
        for (int rotation : rotations) {
            // if a given rotation is not prime, then the number is not a circular prime
            if (!EulerMath.isPrime(rotation)) {
                return false;
            }
        }
        // all rotations are prime, number is a circular prime
        return true;
    }

    /*
    getRotations(int) returns all rotations of the given int
    e.g. for the input n = 159
    the function returns 159, 915, 591
     */
    private int[] getRotations(int n) {
        // int length
        int digitCount = EulerMath.getDigitCount(n);
        // for a given x-digit number, mod is 10^(x-1)
        int mod = (int) Math.pow(10, digitCount - 1);
        int[] rotations = new int[digitCount];

        // for all digits of the given int
        for (int i = 0; i < digitCount; i++) {
            // stores rotation
            rotations[i] = n;
            // gets first digit for digit shift
            int firstDigit = n / mod;
            // shifts all digits
            int nextRotation = ((n * 10) + firstDigit) - (firstDigit * mod * 10);
            // sets n to next rotation
            n = nextRotation;
        }
        return rotations;
    }
}
