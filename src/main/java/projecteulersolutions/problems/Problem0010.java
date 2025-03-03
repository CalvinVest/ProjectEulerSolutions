package projecteulersolutions.problems;

import projecteulersolutions.EulerMath;

/*
The goal of problem 10 is to find the sum of
all primes below two million

The Sieve of Eratosthenes is the most efficient way to find
all primes smaller than a given value for values below about
ten million.
 */
@SuppressWarnings("unused")
public class Problem0010 extends Problem {

    @Override
    public boolean isSolved() {
        return true;
    }

    @Override
    public void printSolution() {
        long sum = sumSieveOfEratosthenes(EulerMath.getSieveOfEratosthenes(2000000));

        System.out.println("The sum of all primes below two million is " + sum);
    }

    private long sumSieveOfEratosthenes(boolean[] primes) {
        long sum = 0L;
        for (int i = 2; i < primes.length; i++) {
            if (primes[i]) {
                sum += i;
            }
        }
        return sum;
    }
}
