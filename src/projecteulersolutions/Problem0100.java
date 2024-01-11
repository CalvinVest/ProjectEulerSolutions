package projecteulersolutions;

/*
If a box contains twenty-one colored discs, composes of fifteen blue discs and six
red discs, and two discs were taken at random, it can be seen that the probability
of taking two blue discs,
P(BB) = (15/21) * (14/20) = 1/2

The next such arrangement, for which there is exactly 50% chance of taking two blue
discs at random, is a box containing 85 blue discs and 35 red discs.

By finding the arrangement to contain over 10^12 = 1000000000000 discs in total,
determine the number of blue discs that the box would contain.
 */
public class Problem0100 extends Problem {

    private static final double MIN_T = 120;

    @Override
    public boolean isSolved() {
        return false;
    }

    @Override
    public void printSolution() {
        /*
        The simplest method for finding a fitting solution to this problem seems
        to me to be calculating every case where the probability Pbb is 1/2, and
        progressing iteratively until a solution of t > 1E12 is found.
         */

        long b = 15l;
        long t = 21l;

        /*
        From the formula
        
        b/t + (b-1)/(t-1) = 1/2
        
        we can solve for a relation between b and t such that the successive values
        for each can be calculated easily. See:
        
        2b^2 - 2b = t^2 - t
        
        b_n+1 = 3*b_n + 2*t_n - 2
        t_n+1 = 4*b_n + 3*t_n - 3
        
        So now the next value for both B and T when Pbb = 1/2 can be easily
        calculated.
         */
        while (t < 1e12) {
            long currB = b;
            long currT = t;
            b = nextB(currB, currT);
            t = nextT(currB, currT);
        }

        System.out.println("The probability Pbb is 1/2 when t = " + t + " and b = " + b + ".");
    }

    private long nextB(long b, long t) {
        return 3 * b + 2 * t - 2;
    }

    private long nextT(long b, long t) {
        return 4 * b + 3 * t - 3;
    }
}
