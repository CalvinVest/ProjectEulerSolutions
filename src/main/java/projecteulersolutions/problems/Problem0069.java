package projecteulersolutions.problems;

/*
Euler's totient function, phi(n) [sometimes called the phi function], is defined
as the number of positive integers not exceeding n which are relatively prime to
n. For example, as 1, 2, 4, 5, 7, and 8 are less than or equal to nine and 
relatively prime to nine, phi(9) = 6.

It is understood that n=6 produces a maximum n/[phi(n)] for n <= 10, at 3. (6/2)

Find the value of n <= 1 000 000 for which n/[phi(n)] is a maximum.
 */
@SuppressWarnings("unused")
public class Problem0069 extends Problem {

    @Override
    public boolean isSolved() {
        return true;
    }

    @Override
    public void printSolution() {
        System.out.println("This problem has not yet been solved.");

        double maxRatio = 0.0;
        int maxIndex = 0;

        for (int i = 2; i <= 1000000; i++) {
            double currRatio = i / (double) getTotient(i);
            if (currRatio > maxRatio) {
                maxRatio = currRatio;
                maxIndex = i;
            }
        }

        System.out.println("The value n for which n/phi(n) is greatest, n <= 1000000 is: " + maxIndex);
    }

    private int getTotient(int n) {
        int result = n; // Initialize result as n

        // Consider all prime factors of n and subtract their multiples from result
        for (int i = 2; i * i <= n; ++i) {
            if (n % i == 0) {
                // Subtract multiples of i from result
                while (n % i == 0) {
                    n /= i;
                }
                result -= result / i;
            }
        }

        // If n is a prime number greater than 1, then subtract its multiple
        if (n > 1) {
            result -= result / n;
        }

        return result;
    }
}
