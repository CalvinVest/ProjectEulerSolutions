package main.java.projecteulersolutions.problems;

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
        long sum = sumSieveOfEratosthenes(sieveOfEratosthenes(2000000));

        System.out.println("The sum of all primes below two million is " + sum);
    }

    private boolean[] sieveOfEratosthenes(int n) {
        boolean[] truthArray = new boolean[n + 1];
        for (int i = 0; i <= n; i++) {
            truthArray[i] = true;
        }

        for (int i = 2; i * i <= n; i++) {
            if (truthArray[i]) {
                for (int j = i * i; j < n; j += i) {
                    truthArray[j] = false;
                }
            }
        }
        return truthArray;
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
