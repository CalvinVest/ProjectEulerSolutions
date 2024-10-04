package projecteulersolutions.problems;

/*
The goal of problem 357:

Consider the divisors of 30: 1, 2, 3, 5, 6, 10, 15, 30.
It can be seen that for every divisor d of 30, d + 30/d is prime.

Find the sum of all positive integers n not exceeding 100,000,000
such that for every divisor d of n, d + n/d is prime.
 */
public class Problem0357 extends Problem {

    private static final int LIMIT = (int) Math.pow(10, 8);

    private boolean[] sieve;

    @Override
    public boolean isSolved() {
        return true;
    }

    @Override
    public void printSolution() {
        // sieve is a sieve of eratosthenes with a range from 0 to our LIMIT value, 100,000,000
        sieve = sieveOfEratosthenes(LIMIT + 1);
        // sum variable to hold sum of all numbers whose factors are all prime cofactor sums
        long sum = 0l;

        // for every value within the given range
        for (int n = 0; n <= LIMIT; n++) {
            if (sieve[n + 1] && isPrimeCofactorSum(n)) {
                sum += n;
            }
        }
        System.out.println("The sum of all integers below 100 million whose cofactor sums are entirely prime is " + sum);
    }

    private boolean isPrimeCofactorSum(int n) {
        for (int i = 1; i <= (int) Math.sqrt(n); i++) {
            // if the value is a factor of the given int but i + n/i is not prime
            // then the value is not a prime cofactor sum
            if (n % i == 0 && !sieve[i + n / i]) {
                return false;
            }
        }
        // else all factors make prime cofactor sums, return true
        return true;
    }

    public static boolean[] sieveOfEratosthenes(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("Negative array size");
        }
        // make a bool array for values 0 to n
        boolean[] sieve = new boolean[n + 1];
        // if array is 2 or more, 2 is prime
        if (n >= 2) {
            sieve[2] = true;
        }
        // for all odd values, set to true
        for (int i = 3; i <= n; i += 2) {
            sieve[i] = true;
        }
        // for all odd values up to sqrt n, set to false if not prime
        for (int i = 3; i <= (int) Math.sqrt(n); i += 2) {
            if (sieve[i]) {
                int increment = 2 * i;
                for (int j = i * i; j <= n; j += increment) {
                    sieve[j] = false;
                }
            }
        }
        return sieve;
    }
}
