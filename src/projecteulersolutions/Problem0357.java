package projecteulersolutions;

/*
The goal of problem 357:

Consider the divisors of 30: 1, 2, 3, 5, 6, 10, 15, 30.
It can be seen that for every divisor d of 30, d + 30/d is prime.

Find the sum of all positive integers n not exceeding 100,000,000
such that for every divisor d of n, d + n/d is prime.
 */
public class Problem0357 extends Problem {
    
    private static final int UPPER_BOUND = 100000000;

    @Override
    public boolean isSolved() {
        return false;
    }

    @Override
    public void printSolution() {
        long sum = 0l;

        for (int i = 4; i <= UPPER_BOUND; i++) {
            if(i % (UPPER_BOUND / 10000) == 0) {
                System.out.printf("Calculating: %5.2f%% (%d/%d)\n"
                        + "Sum: %d\n", (double) i / UPPER_BOUND * 100,  i, UPPER_BOUND, sum);
            }
            if (!isPrime(i) && hasAllPrimeCofactorSums(i)) {
                sum += i;
                //System.out.println("New sum is " + sum);
            }
        }

        System.out.println("The sum of all numbers with all prime cofactor sums below 100000000 is " + sum);
    }

    /*
    hasAllPrimeCofactorSums(int) returns if a given ints divisors are all prime
    such that for every divisor d of n, d + n/d is prime.
     */
    private boolean hasAllPrimeCofactorSums(int n) {
        for (int i = 2; i <= n / 2; i++) {
            if (n % i == 0 && !isPrimeCofactorSum(i, n)) {
                return false;
            }
        }
        //System.out.println(n + " has all prime cofactor sums.");
        return true;
    }

    private boolean isPrimeCofactorSum(int i, int n) {
        if (n % i == 0) {
            return isPrime(i + n / i);
        }
        return false;
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
