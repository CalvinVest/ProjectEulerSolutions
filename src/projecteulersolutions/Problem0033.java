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
        /*
        In all cases, numerator is less than denominator, and 10 <= num,den < 100
        
        For each valid case, 10 <= n,d < 100, n < d
        Check if the n,d pair fit the case given in the problem intro.
        Make sure, if they're valid, that the solution is not trivial.
        Remember the solution is the product of all four solutions.
        Return solution is lowest terms, print denominator
         */

        int solNum = 1, solDnm = 1;

        for (int dnm = 11; dnm < 100; dnm++) {
            for (int num = 10; num < dnm; num++) {
                int a = num / 10, b = num % 10, c = dnm / 10, d = dnm % 10;
                if (b == d && b == 0) {
                    continue;
                }
                double fracVal = (double) num / (double) dnm;

                boolean ac = a == c, ad = a == d, bc = b == c, bd = b == d;
                
                if((ac && (double)b / (double)d == fracVal) ||
                        (ad && (double)b / (double)c == fracVal) ||
                        (bc && (double)a / (double)d == fracVal) ||
                        (bd && (double)a / (double)c == fracVal)) {
                    System.out.println(num + "/" + dnm + " = " + fracVal);
                }
            }
        }
    }
}
