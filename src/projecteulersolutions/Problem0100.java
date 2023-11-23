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

    private static final double MIN_T = 100;

    @Override
    public boolean isSolved() {
        return false;
    }

    @Override
    public void printSolution() {

        for (double b = MIN_T / 2; b < MIN_T; b++) {
            for (double r = 0; r < b; r++) {
                double t = r + b;
                if (isPerfectPbb(b, t)) {
                    System.out.println("The number of blue discs with a perfect 50% chance of being drawn twice is " + b);
                }
            }
        }
    }

    private boolean isPerfectPbb(double b, double t) {
        return b * (b - 1) / t / (t - 1) == 0.5;
    }
}
