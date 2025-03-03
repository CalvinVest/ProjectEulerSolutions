package projecteulersolutions.problems;

import projecteulersolutions.EulerMath;

import java.util.Arrays;

/*
The goal of problem 49 is to find an existing "prime permutation".

From projecteuler.net/problem=49:

"The arithmetic sequence, 1487, 4817, 8147, in which each of the terms
increases by 3330, is unusual in two ways:
(i) each of the three terms are prime, and,
(ii) each of the 4-digit numbers are permutations of one another.

There are no arithmetic sequences made up of three 
1-, 2-, or 3-digit primes, exhibiting this property, but there is one other
4-digit increasing sequence.

What 12-digit number do you form by concatenating the three terms
in this sequence?"

Plan:
- make isPrime function for ints
- make isPermutation for ints
- make main loop to go through all valid int triples
- valid is when j = i + 3330, k = j + 3330, and i j and k are permutative
- solution is when i != 1487 (given solution, find other)
 */
@SuppressWarnings("unused")
public class Problem0049 extends Problem {

    @Override
    public boolean isSolved() {
        return true;
    }

    @Override
    public void printSolution() {
        for (int i = 1000; i < 3340; i++) {
            if (i == 1487) {
                continue;
            }
            int j = i + 3330, k = i + 6660;
            if (EulerMath.isPrime(i)
                    && EulerMath.isPrime(j)
                    && EulerMath.isPrime(k)
                    && isPermutation(i, j, k)) {
                System.out.printf("The concatenation of the prime permutations is %d%d%d\n", i, j, k);
            }
        }
    }

    private boolean isPermutation(int i, int j, int k) {
        char[] x = Integer.toString(i).toCharArray();
        char[] y = Integer.toString(j).toCharArray();
        char[] z = Integer.toString(k).toCharArray();
        Arrays.sort(x);
        Arrays.sort(y);
        Arrays.sort(z);
        return areArraysEqual(x, y, z);
    }

    private boolean areArraysEqual(char[] x, char[] y, char[] z) {
        if (x.length != y.length || x.length != z.length) {
            return false;
        }
        for (int i = 0; i < x.length; i++) {
            if (!(x[i] == y[i] && x[i] == z[i])) {
                return false;
            }
        }
        return true;
    }
}
