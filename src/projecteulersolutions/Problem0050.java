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
        return false;
    }

    @Override
    public void printSolution() {
        System.out.println("This problem has not been solved yet.");
    }

    private int getNextPrime(int n) {
        int nextPrime = n + 1;
        while(!isPrime(nextPrime)) {
            nextPrime++;
        }
        return nextPrime;
        
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
