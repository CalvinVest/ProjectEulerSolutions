package projecteulersolutions;

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
        System.out.println("This problem has not yet been solved.");
        /*for(int iter = 1; iter < 1000; iter++) {
            double sqrt2Approx = 1.0 + getSqrt2DecApprox(iter);
            System.out.printf("iter: %d| sqrt(2): %10.8f\n", iter, sqrt2Approx);
        }*/
    }

    /*private double getSqrt2DecApprox(int iterations) {
        Fraction frac = new Fraction(2, 1);
    }
    
    private class Fraction {
        private int num, den;
        
        public Fraction(int num, int den) {
            this.num = num;
            this.den = den;
        }
        
        public int getNumerator() {
            return num;
        }
        
        public void setNumerator(int num) {
            this.num = num;
        }
        
        public int getDenominator() {
            return den;
        }
        
        public void setDenominator(int den) {
            this.den = den;
        }
        
        public void simplify() {
            int gcf = EulerMath.getGCD(num, den);
            num /= gcf;
            den /= gcf;
            
            if(den < 0) {
                num *= -1;
                den *= -1;
            }
        }
    }*/
}
