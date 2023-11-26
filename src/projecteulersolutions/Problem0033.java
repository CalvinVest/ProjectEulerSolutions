package projecteulersolutions;

/*
The fraction 49/98 is a curious fraction, as an inexperienced mathematician in
attempting to simplify it may incorrectly believe that 49/98 = 4/8, which is
correct, is obtained by cancelling the 9s.

We shall consider fractions like, 30/50 = 3/5, to be trivial examples.

There are exactly four non-trivial examples of this type of fraction, less than
one in value, and containing two digits in the numerator and denominator.

If the product of these four fractions is given in its lowest common terms,
find the value of the denominator.
 */
public class Problem0033 extends Problem {

    @Override
    public boolean isSolved() {
        return false;
    }

    @Override
    public void printSolution() {

        int solNum = 1, solDnm = 1;

        // for denominator vals 11 to 99
        for (int dnm = 11; dnm < 100; dnm++) {
            // for numerator vals 10 to 98
            // all fractions 10/11 to 98/99
            for (int num = 10; num < dnm; num++) {
                // fraction is in the form ab/cd
                int a = num / 10, b = num % 10, c = dnm / 10, d = dnm % 10;
                // if fraction is in form a0/c0 solution is trivial, skip it
                if (b == 0 || d == 0) {
                    continue;
                }
                // if a num and dnm digit are similar, see if the fraction of the
                // remaining digits equals the original fraction. if so, a solution
                // fraction has been found
                boolean isSol = (a == c) ? (b * dnm == d * num) : (a == d) ? (b * dnm == c * num) : (b == c) ? (a * dnm == d * num) : (b == d) ? (a * dnm == c * num) : false;

                if (isSol) {
                    System.out.println(num + "/" + dnm + " = " + (double) num / (double) dnm);
                    solNum *= num;
                    solDnm *= dnm;
                }
            }
        }
        // find GCD of solution fraction parts to simplify to smallest terms
        int gcd = EulerMath.getGCD(solNum, solDnm);
        System.out.println("Product of solution fractions is " + solNum / gcd + "/" + solDnm / gcd);
    }
}
