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

    private static final int UPPER_BOUND = 1000000;

    @Override
    public boolean isSolved() {
        return true;
    }

    /*
    The algorithm for this problem focuses on Euler's totient function. This function
    returns the number of coprimes of a given value less than that value.
    totient(10) = |{1, 3, 7, 9}| so totient(10) = 4.
    
    To find the number of reduced proper fractions, sum all denominator totients
    and the resulting value is the count of reduced proper fractions.
     */
    @Override
    public void printSolution() {
        // count variable to hold number of reduced proper fractions
        long count = 0;

        // totient array, where totients[i] = totient(i).
        int[] totients = EulerMath.getTotientArray(UPPER_BOUND);

        // for all denominators, add totient(denominator) to count
        for (int i = 2; i < totients.length; i++) {
            count += totients[i];
        }
        System.out.println("The number of reduced proper fractions for denominator <= " + UPPER_BOUND + " is " + count);
    }
}
