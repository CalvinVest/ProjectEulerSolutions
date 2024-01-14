package projecteulersolutions;

import java.math.BigInteger;

/*
It is possible to show that the square root of two can be expressed as an
infinite continued fraction.

sqrt(2) = 1 + 1/(2 + 1/(2 + 1/(2...

By expanding this for the first four iterations, we get:

1 + 1/2 = 3/2 = 1.5
1 + 1/(2+1/2) = 7/5 = 1.4
1 + 1/(2 + 1/(2 + 1/(2 + 1/2))) = 17/12 = 1.41666
1 + ... = 41/29 = 1.41379

The next three expansions are 99/70, 239/169, and 577/408, but the eighth
expansion, 1393/985, is the first example where the number of digits in the
numerator exceeds the number of digits in the denominator.

In the first one-thousand expansions, how many fractions contain a numerator
with more digits than the denominator?
 */
public class Problem0057 extends Problem {

    @Override
    public boolean isSolved() {
        return true;
    }

    @Override
    public void printSolution() {
        int count = 0;
        int iter;
        System.out.println("This problem has not yet been solved.");
        for (iter = 1; iter < 1000; iter++) {
            BigFraction sqrt2Frac = getSqrt2Approx(iter);
            if (sqrt2Frac.getNumerator().toString().length() > sqrt2Frac.getDenominator().toString().length()) {
                count++;
            }
        }

        System.out.println(count + " of the first " + iter + " iterations of the approximation of sqrt(2) have numerators of greter length");
    }

    /*
    cases for iterations:
    1: frac = 3/2, since the for loop is skipped and the original 2/1
    is inverted (1/2) and incremented (3/2)
    2: frac = 7/5, since the 2 is inverted (1/2) and incremented twice
    (5/2) by going through the loop a single time, then exits the loop
    and inverts (2/5) and increments (7/5)
    3: frac = 17/12, but from this point on it gets easier since we can
    use the for loop more effectively. The original value (2/1) is on its
    first loop inverted (1/2) and incremented twice (5/2). The same process
    is repeated on the second loop, an inversion (2/5) and double increment
    (12/5). This is once again finalized with an inversion (5/12) and an
    increment (17/12).
    
    This process continues, and the numbers get incredibly large pretty
    quickly. By the 30th iteration both values were very prone to overflow,
    so BigIntegers were used to represent the fraction numerator and
    denominators.
     */
    private BigFraction getSqrt2Approx(int iterations) {
        BigFraction frac = new BigFraction(BigInteger.TWO, BigInteger.ONE);

        if (iterations > 0) {
            for (int i = 0; i < iterations - 1; i++) {
                // for each iteration, the fraction becomes 2 + 1/itself
                // this can alternatively be expressed as the inverse, twice incremented.
                frac.invert();
                frac.increment();
                frac.increment();
            }

            // after the loop may or may not have been executed, the starting
            // (or working) BigFraction frac is inverted and incremented, following
            // the provided pattern in the problem description for the expansion
            // of the approximation of sqrt(2)
            frac.invert();
            frac.increment();
        }

        return frac;
    }

    private class BigFraction {

        private BigInteger num, den;

        public BigFraction(BigInteger num, BigInteger den) {
            this.num = num;
            this.den = den;
        }

        public BigInteger getNumerator() {
            return num;
        }

        public BigInteger getDenominator() {
            return den;
        }

        // inverting the fraction is simply swapping numerator and denominator.
        public void invert() {
            BigInteger temp = num;
            num = den;
            den = temp;
        }

        // to increment the fraction is to increase the value of the numerator by
        // the numerical value of the denominator.
        public void increment() {
            num = num.add(den);
        }
    }
}
