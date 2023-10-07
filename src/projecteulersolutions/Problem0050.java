package projecteulersolutions;

/*
The goal of problem 50 is to find the prime below one million that can be written
as the sum of the most consecutive primes.

The prime 41 can be written as the sum of six consecutive primes:
41 = 2 + 3 + 5 + 7 + 11 + 13
This is the longest sum of consecutive primes that adds to a prime below 100.
The longest sum of consecutive primes below 1000 has 21 terms and is 953.
 */
public class Problem0050 extends Problem {

    @Override
    public boolean isSolved() {
        return true;
    }

    @Override
    public void printSolution() {
        /*
        Problem plan:
        
        For every consecutive prime sum below the upper bound
            if the sum itself is prime
                if number of steps is greater than max
                    set new max
                    record max length prime sum
         */
        int upperBound = 1000000;
        int longestPrimeSum = 0;
        int maxSteps = 1;
        int starterPrime = 2;

        /*
        in this loop the upper bound is divided by the max number of steps since
        any primes above this value would not be able to reach the max number of
        steps in the consecutive prime sum.
        In other words, for maxSteps = n,
        sum = currPrime + nextPrime + primeAfterThat...n times
        if currPrime * n > upperBound, this sum would not be valid
         */
        for (int i = starterPrime; i < upperBound / maxSteps; i = getNextPrime(i)) {
            int currPrime = i;
            int sum = currPrime;
            int steps = 1;

            while (sum < upperBound) {
                if (steps > maxSteps && EulerMath.isPrime(sum)) {
                    maxSteps = steps;
                    longestPrimeSum = sum;
                }
                currPrime = getNextPrime(currPrime);
                sum += currPrime;
                steps++;
            }
        }

        System.out.println("The largest prime sum of consecutive primes below one million is " + longestPrimeSum);
    }

    /*
    getNextPrime returns the next prime after the given int
    */
    private int getNextPrime(int n) {
        int nextPrime = n + 1;
        while (!EulerMath.isPrime(nextPrime)) {
            nextPrime++;
        }
        return nextPrime;
    }
}
