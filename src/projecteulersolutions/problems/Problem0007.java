package projecteulersolutions.problems;

import projecteulersolutions.EulerMath;

/*
The goal of problem 7 is to find the 10001st prime number
 */
public class Problem0007 extends Problem {

    @Override
    public boolean isSolved() {
        return true;
    }

    @Override
    public void printSolution() {
        int countPrimes = 0;
        int latestPrime = 0;

        for (int i = 2; countPrimes < 10001; i++) {
            if (EulerMath.isPrime(i)) {
                countPrimes++;
                latestPrime = i;
            }
        }

        System.out.println("The 10001st prime number is " + latestPrime);
    }
}
