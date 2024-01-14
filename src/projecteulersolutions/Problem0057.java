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
        return false;
    }

    @Override
    public void printSolution() {
        int count = 0;
        int iter;
        System.out.println("This problem has not yet been solved.");
        for (iter = 1; iter < 1000; iter++) {
            BigFraction sqrt2Frac = getSqrt2Approx(iter);
            if(sqrt2Frac.getNumerator().toString().length() > sqrt2Frac.getDenominator().toString().length()) {
                count++;
            }
        }
        
        System.out.println(count + " of the first " + iter + " iterations of the approximation of sqrt(2) have numerators of greter length");
    }

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

        public void setNumerator(BigInteger num) {
            this.num = num;
        }

        public BigInteger getDenominator() {
            return den;
        }

        public void setDenominator(BigInteger den) {
            this.den = den;
        }

        public void invert() {
            BigInteger temp = num;
            num = den;
            den = temp;
        }

        public void increment() {
            num = num.add(den);
        }

        @Override
        public String toString() {
            return "[" + num + "/" + den + "]";
        }
    }
}
