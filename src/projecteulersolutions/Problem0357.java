package projecteulersolutions;

/*
The goal of problem 357:

Consider the divisors of 30: 1, 2, 3, 5, 6, 10, 15, 30.
It can be seen that for every divisor d of 30, d + 30/d is prime.

Find the sum of all positive integers n not exceeding 100,000,000
such that for every divisor d of n, d + n/d is prime.
 */
public class Problem0357 extends Problem {

    @Override
    public boolean isSolved() {
        return false;
    }

    @Override
    public void printSolution() {
        System.out.println("This solution has not been implemented.");
    }

    private boolean isDivisor(int d, int n) {
        return n % d == 0;
    }

    private boolean isPrime(int n) {
        for (int i = 2; i <= n / 2; i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }
}
