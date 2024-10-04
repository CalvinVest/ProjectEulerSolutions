package projecteulersolutions.problems;

import projecteulersolutions.EulerMath;

/*
The number 3797 has an interesting property. Being prime itself, it is possible
to continuously remove digits from left to right, and remain prime at each stage:
3797, 797, 97, 7. Similarly, we can work from right to left: 3797, 379, 37, 3.

Find the sum of the only eleven primes that are can both be truncated from left to right
and right to left.

NOTE: 2, 3, 5, and 7 are not considered to be truncatable primes.
 */
@SuppressWarnings("unused")
public class Problem0037 extends Problem {

    private static final int COUNT_VALID = 11;

    @Override
    public boolean isSolved() {
        return true;
    }

    @Override
    public void printSolution() {
        int sum = 0;
        int found = 0;
        int i = 10;

        while (found < COUNT_VALID) {
            if (EulerMath.isPrime(i)) {
                // if the number is a truncatable prime in both directions, add
                // num to sum and increment count of found numbers.
                if (isPrimeLTR(i) && isPrimeRTL(i)) {
                    sum += i;
                    found++;
                }
            }
            i++;
        }
        
        System.out.println("The sum of all truncatable primes is " + sum);
    }

    // isPrimeLTR truncates digits starting at the most significant and checks
    // that each iteration while the number is a positive integer is a prime number.
    private boolean isPrimeLTR(int num) {
        // gets 10^(num length - 1) as our modulo
        int mod = (int) Math.pow(10, EulerMath.getDigitCount(num) - 1);
        // truncates most significant digit
        int curr = num % mod;

        // while still positive integer
        while (curr > 0) {
            // if not prime, return false. not truncatable prime
            if (!EulerMath.isPrime(curr)) {
                return false;
            }
            // reduces modulo by one digit and truncates next most significant digit
            mod /= 10;
            curr %= mod;
        }
        return true;
    }

    // isPrimeRTL truncates digits starting at the least significant and checks
    // that each iteration while the number is a positive integer is a prime number.
    private boolean isPrimeRTL(int num) {
        // truncates least significant digit
        int curr = num / 10;

        // while still positive integer
        while (curr > 0) {
            // if not prime, return false. not truncatable prime
            if (!EulerMath.isPrime(curr)) {
                return false;
            }
            // truncates least significant digit
            curr /= 10;
        }
        return true;
    }
}
