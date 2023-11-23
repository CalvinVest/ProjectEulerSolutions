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
        var solution = new Problem0100Solution();

        /*
        For MIN_T = 120:
        60 <= b < 120
        When b = 60: 120 <= t < 120
        When b = 119: 120 <= t < 238
        Area of solution range is right triangle with right angle side lengths 59 and 118.
        Area of solution is found formulaically as (MIN_T/2 - 1) * (MIN_T - 1) / 2
        
        For MIN_T = 1E12:
        5E11 <= b < 1E12 - 1
        When b = 5E11: 1E12 <= t < 1E12
        When b = 1E12 - 1: 1E12 <= t < 2E12 - 2
        Area of solution is (MIN_T/2 - 1) * (MIN_T - 1) / 2 = (5E11 - 1) * (1E12 - 1) / 2
        
        For a given value of MIN_T, the number of potential operations is approx. 2.5E23
         */
        for (double b = MIN_T / 2; b < MIN_T; b++) {
            for (double t = MIN_T; t < 2 * b; t++) {
                if (calcPickBTwice(b, t) < 0.5) {
                    break;
                }

                if (isPerfectPbb(b, t)) {
                    solution = new Problem0100Solution(t, b, true);
                }
            }
        }
        System.out.println(solution.getAnswerString());
    }

    private boolean isPerfectPbb(double b, double t) {
        return calcPickBTwice(b, t) == 0.5;
    }

    private double calcPickBTwice(double b, double t) {
        return b * (b - 1) / t / (t - 1);
    }

    private class Problem0100Solution {

        private boolean found;
        private double t, b;

        public Problem0100Solution(double t, double b, boolean found) {
            this.t = t;
            this.b = b;
            this.found = found;
        }

        public Problem0100Solution() {
            this(0, 0, false);
        }

        public String getAnswerString() {
            return found ? String.format("The solution is:\n%.0f Total\n%.0f Blue\n%.0f Red", t, b, t - b) : String.format("There is not a fitting solution for T = %.0f", t);
        }
    }
}
