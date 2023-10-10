package projecteulersolutions;

/*
The goal for problem 72:

Consider the fraction n/d, where n and d are positive integers. If n < d and
HCF(n, d) = 1, it is called a reduced proper fraction.

If we list the set of reduced proper fractions for d <= 8 in ascending order of size,
we get:
1/8, 1/7, 1/6, 1/5, 1/4, 2/7, 1/3, 3/8, 2/5, 3/7, 1/2, 4/7, 3/5, 5/8, 2/3, 5/7, 3/4,
4/5, 5/6, 6/7, 7/8

It can be seen there are 21 elements in this set.

How many elements would be contained in the set of reduced proper fractions for
d <= 1,000,000?
 */
public class Problem0072 extends Problem {

    private static final int UPPER_BOUND = 10000;

    /*
    For this problem, my brief roadmap is:
    - Go through all 1 million denominators.
    - Numerators are <= denominators, loop through them too
    - Need a count for reduced proper fractions
    - If a given numerator and denominator GCF is 1, then increment count
    - The count is the desired result.
    - Count is likely to be very large. million * million = 1E12.
     */
    @Override
    public boolean isSolved() {
        return false;
    }

    @Override
    public void printSolution() {
        long count = 0;

        for (int denom = 2; denom <= UPPER_BOUND; denom++) {
            if (EulerMath.isPrime(denom)) {
                count += denom - 1;
                continue;
            }
            for (int numer = 1; numer < denom; numer++) {
                if (EulerMath.getGCF(numer, denom) == 1) {
                    count++;
                    //System.out.println(count + ": " + numer + "/" + denom);
                }
            }
        }

        System.out.println("The number of reduced proper fractions for denominator <= " + UPPER_BOUND + " is " + count);

        /*long countReducedProper = 0l;
        boolean[][] sieveMatrix = generateSieve();

        for (int d = 2; d <= UPPER_BOUND; d++) {
            for (int n = 1; n < d; n++) {
                if (sieveMatrix[n][d]) {
                    countReducedProper++;
                }
            }
        }

        System.out.println("There are " + countReducedProper + " reduced proper fractions for d <= " + UPPER_BOUND);*/
    }

    /*private boolean[][] generateSieve() {
        boolean[][] sieve = new boolean[UPPER_BOUND + 1][UPPER_BOUND + 1];
        
        for(int i = 0; i < sieve.length; i++) {
            for(int j = 0; j < sieve[0].length; j++) {
                sieve[i][j] = isReducedProper(i,j);
            }
        }
        
        return sieve;
    }*/

 /*
    private boolean isReducedProper(int n, int d) {
        for (int i = 2; i < n; i++) {
            if (n % i == 0 && d % i == 0) {
                return false;
            }
        }
        System.out.println(n + "/" + d + " is reduced proper.");
        return true;
    }*/
}
