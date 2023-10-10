package projecteulersolutions;

/*
Euler dicovered the remarkable quadratic formula:
n^2 + n + 41

It turns out that the formula will produce 40 primes for the consecutive integer
values 0 <= n <= 39. However, when n = 40, 40^2 + 40 + 41 is divisible by 41.

The incredible formula n^2 - 79n + 1601 was discovered, which produces 80 primes
for the consecutive values 0 <= n <= 79. The product of the coefficients, -79 and
1601, is -126479.

Considering quadratics of the form:
n^2 + an + b where |a| < 1000 and |b| <= 1000

Find the product of the coefficients, a and b, for the quadratic expression that
produces the maximum number of primes for consecutive values of n, starting with n = 0.
 */
public class Problem0027 extends Problem {

    @Override
    public boolean isSolved() {
        return true;
    }

    @Override
    public void printSolution() {
        int maxCount = 0;
        int maxProd = 0;
        // for |a| < 1000 and |b| <= 1000
        for (int a = -999; a < 1000; a++) {
            for (int b = -1000; b <= 1000; b++) {
                // count tracks the number of consecutive primes
                int count = 0;
                // n is the variable for the quadratic formula and increments
                int n = 0;
                // the first value is 0*0 + a*0 + b = b.
                int curr = b;

                // while the quadratic results are prime
                while (EulerMath.isPrime(curr)) {
                    // increment count of consecutive primes
                    count++;

                    // increment quadratic variable
                    n++;
                    // recalculate new curr value
                    curr = n * n + a * n + b;
                }

                // if new max consecutive primes is found
                if (count > maxCount) {
                    // redefine max consecutive primes
                    maxCount = count;
                    // save product of coefficients, this is the result.
                    maxProd = a * b;
                }
            }
        }

        System.out.println("The product of the coefficients of the longest quadratic prime chain is " + maxProd);
    }
}
