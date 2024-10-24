package main.java.projecteulersolutions.problems;

import main.java.projecteulersolutions.EulerMath;

/*
It was proposed by Christian Goldbach that every odd composite number can be
written as the sum of a prime and twice a square.

9 = 7 + (2 * 1^2)
15 = 7 + (2 * 2^2)
21 = 3 + (2 * 3^2)
25 = 7 + (2 * 3^2)
27 = 19 + (2 * 2^2)
33 = 31 + (2 * 1^2)

It turns out that conjecture was false.
What is the smallest odd composite that cannot be written as the sum of a prime
and twice a square?
 */
@SuppressWarnings("unused")
public class Problem0046 extends Problem {

    @Override
    public boolean isSolved() {
        return true;
    }

    @Override
    public void printSolution() {
        System.out.println("The smallest composite that satisfies this problem is " + findGoldbachException());
    }

    private int findGoldbachException() {
        // start with first composite odd number
        int n = 9;
        boolean found = false;

        // while a solution has not been found
        while (!found) {
            // incrememnts to next odd number as long as the current value is prime
            // we are only interested in looking at composite odd numbers
            while (EulerMath.isPrime(n)) {
                n += 2;
            }
            // if the current value is not the sum of a prime and twice a square
            if (!isSumOfPrimeAndTwiceSquare(n)) {
                // this is the solution value - return it
                return n;
            }
            // move to next odd number
            n += 2;
        }
        // no solution found
        return 0;
    }

    private boolean isSumOfPrimeAndTwiceSquare(int n) {
        // for all prime numbers, ascending
        for (int i = 2; i < n; i = EulerMath.getNextPrime(i)) {
            // for all integers for which the square is less than the given value
            for (int j = 1; j * j < n; j++) {
                // if the given value is the sum of a prime and twice a square
                if (i + 2 * j * j == n) {
                    return true;
                }
            }
        }
        // number is not the sum of a prime and twice a square - this is the solution
        return false;
    }
}
