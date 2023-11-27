package projecteulersolutions;

import java.util.ArrayList;
import java.util.List;

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

    public static final int CONSECUTIVE_COUNT = 4;

    @Override
    public boolean isSolved() {
        return false;
    }

    @Override
    public void printSolution() {
        int sol = 0;
        boolean solutionFound = false;
        for (int n = 2; !solutionFound; n++) {
            if (isConsecutiveDistinctFactors(n, CONSECUTIVE_COUNT)) {
                sol = n;
                solutionFound = true;
            }
        }

        System.out.println("The first four consecutive integers with four distinct primes each starts at " + sol);
    }

    private int getCountOfDistinctPrimeFactors(int n) {
        int count = 0;
        int prime = 2;
        while (n > 1) {
            if (n % prime == 0) {
                count++;
                while (n % prime == 0) {
                    n /= prime;
                }
            }
            prime = EulerMath.getNextPrime(prime);
        }
        return count;
    }

    private boolean isConsecutiveDistinctFactors(int n, int range) {
        for (int i = 0; i < range; i++) {
            if (getCountOfDistinctPrimeFactors(n + i) < range) {
                return false;
            }
        }
        return true;
    }
}
