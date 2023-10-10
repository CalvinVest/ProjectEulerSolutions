package projecteulersolutions;

/*
The number 3797 has an interesting property. Being prime itself, it is possible
to continuously remove digits from left to right, and remain prime at each stage:
3797, 797, 97, 7. Similarly we can work from right to left: 3797, 379, 37, 3.

Find the sum of the only eleven primes that are both truncatable from left to right
and right to left.

NOTE: 2, 3, 5, and 7 are not considered to be truncatable primes.
 */
public class Problem0037 extends Problem {

    private static final int COUNT_VALID = 11;

    @Override
    public boolean isSolved() {
        return false;
    }

    @Override
    public void printSolution() {
        int sum = 0;
        int found = 0;
        int i = 10;

        while (found < COUNT_VALID) {
            if (EulerMath.isPrime(i)) {
                if (isPrimeLTR(i) && isPrimeRTL(i)) {
                    System.out.println(i + " is a bidirectional truncatable prime - " + found);
                    sum += i;
                    found++;
                }
            }
            i++;
        }
        
        System.out.println("The sum of all truncatable primes is " + sum);
    }

    private boolean isPrimeLTR(int num) {
        int mod = (int) Math.pow(10, EulerMath.getDigitCount(num) - 1);
        int curr = num % mod;

        while (curr > 0) {
            if (!EulerMath.isPrime(curr)) {
                return false;
            }
            mod /= 10;
            curr %= mod;
        }
        return true;
    }

    private boolean isPrimeRTL(int num) {
        int curr = num /= 10;

        while (curr > 0) {
            if (!EulerMath.isPrime(curr)) {
                return false;
            }
            curr /= 10;
        }
        return true;
    }
}
