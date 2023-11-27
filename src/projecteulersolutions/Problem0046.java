package projecteulersolutions;

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
public class Problem0046 extends Problem {

    @Override
    public boolean isSolved() {
        return false;
    }

    @Override
    public void printSolution() {
        System.out.println("The smallest composite that satisfies this problem is " + findGoldbachException());
    }

    private int findGoldbachException() {
        int n = 3;
        boolean found = false;

        while (!found) {
            while (EulerMath.isPrime(n)) {
                n += 2;
            }
            if (!isSumOfPrimeAndTwiceSquare(n)) {
                return n;
            }
            n += 2;
        }
        return 0;
    }

    private boolean isSumOfPrimeAndTwiceSquare(int n) {
        for (int i = 2; i < n; i = EulerMath.getNextPrime(i)) {
            for (int j = 1; j * j < n; j++) {
                if (i + 2 * j * j == n) {
                    return true;
                }
            }
        }
        return false;
    }
}
