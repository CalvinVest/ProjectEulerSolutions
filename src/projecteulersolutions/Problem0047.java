package projecteulersolutions;


/*
The first two consecutive numbers to have two distinct prime factors are:
14 = 2 x 7
15 = 3 x 5

The first three consecutive numbers to have three distinct prime factors are:
644 = 2^2 x 7 x 23
645 = 3 x 5 x 43
646 = 2 x 17 x 19

Find the first four consecutive numbers to have four distinct prime factors each.
What is the first of these numbers?
 */
public class Problem0047 extends Problem {

    @Override
    public boolean isSolved() {
        return false;
    }

    @Override
    public void printSolution() {
        System.out.println("The first four consecutive integers with four distinct primes each starts at " + getProblem0047FirstValue());
    }

    private int getProblem0047FirstValue() {
        // list starts at 2*3*5*7, smallest four consecutive prime factors
        for (int n = 210;; n++) {
            // if the given value is prime, it cannot have four consecutive prime factors
            if (EulerMath.isPrime(n)) {
                continue;
            }
            // this value is our solution, return it
            if (isConsecutiveDistinctPrimes(n)) {
                return n;
            }
        }
    }

    private boolean isConsecutiveDistinctPrimes(int n) {
        return countDistinctPrimeFactors(n) >= 4
                && countDistinctPrimeFactors(n + 1) >= 4
                && countDistinctPrimeFactors(n + 2) >= 4
                && countDistinctPrimeFactors(n + 3) >= 4;
    }

    private static int countDistinctPrimeFactors(int n) {
        int count = 0;
        for (int i = 2; i <= n; i++) {
            if (n % i == 0 && EulerMath.isPrime(i)) {
                count++;
                while (n % i == 0) {
                    n /= i;
                }
            }
        }
        return count;
    }
}
