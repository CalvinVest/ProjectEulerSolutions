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
        boolean[] sieve = sieveOfEratosthenes(LIMIT);

        for (int i = 0; i < sieve.length; i++) {
            if (sieve[i]) {
                if (!isCircularPrime(i)) {
                    sieve[i] = false;
                }
            }
        }

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

    private boolean isCircularPrime(int n) {
        int[] rotations = getRotations(n);

        for (int rotation : rotations) {
            if (!EulerMath.isPrime(rotation)) {
                return false;
            }
        }
        return true;
    }

    private int[] getRotations(int n) {
        int digitCount = EulerMath.getDigitCount(n);
        int mod = (int) Math.pow(10, digitCount - 1);
        int[] rotations = new int[digitCount];

        for (int i = 0; i < digitCount; i++) {
            rotations[i] = n;
            int firstDigit = n / mod;
            int left = ((n * 10) + firstDigit) - (firstDigit * mod * 10);
            n = left;
        }

        return rotations;
    }
}
