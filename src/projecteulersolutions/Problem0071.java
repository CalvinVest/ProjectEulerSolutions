package projecteulersolutions;

/*
Consider the fraction, n/d, where n and d are positive integers. If n < d and
HCF(n,d) = 1, it is called a reduced proper fraction.

If we list the set of reduced proper fractions for d <= 8 in ascending order of
size, we get:

1/8, 1/7, 1/6, 1/5, 1/4, 2/7, 1/3, 3/8, 2/5, 3/7, 1/2, ...

It can be seen that 2/5 is the fraction immediately to the left of 3/7.

By listing the set of reduced proper fractions for d<= 1 000 000 in ascending
order of size, find the numerator of the fraction immediately to the left of 3/7
 */
public class Problem0071 extends Problem {

    @Override
    public boolean isSolved() {
        return false;
    }

    @Override
    public void printSolution() {
        System.out.println("This problem has not yet been solved.");

        int solN = 0, solD = 0;
        double solDbl = 0.0;

        for (int d = 8; d <= 1000000; d++) {
            int n = d * 3 / 7 - 10;
            n = (n < 0) ? 0 : n;
            while (7 * n < 3 * d) { // try while 7n < 3d
                double currDbl = (double) n / (double) d;
                if (currDbl < 3.0 / 7.0 && currDbl > solDbl) {
                    solN = n;
                    solD = d;
                    solDbl = currDbl;
                    System.out.println("Closest fraction is " + n + "/" + d + " = " + currDbl);
                }

                n++;
            }

        }
        System.out.println("The closest fraction to 3/7 is " + solN + "/" + solD + ".");
    }
}
