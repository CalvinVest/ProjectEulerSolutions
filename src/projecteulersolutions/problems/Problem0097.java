package projecteulersolutions.problems;

import java.math.BigInteger;

/*
The first known prime found to exceed one million digits was discovered in 1999,
and is a Mersenne prime of the form 2^6972593 - 1; it comtains exactly 2098960
digits. Subsequently other Mersenne primes, of the form 2^p - 1, have been found
which contain more digits.

However, in 2004 there was found a massive non-Mersenne prime which contains
2357207 digits: 28433 * 2^7830457 + 1.

Find the last ten digits of this prime number.
 */
@SuppressWarnings("unused")
public class Problem0097 extends Problem {

    @Override
    public boolean isSolved() {
        return true;
    }

    @Override
    public void printSolution() {
        int[] powerArr = {1, 0, 0, 1, 1, 1, 0, 1, 1, 1, 0, 1, 1, 1, 1, 0, 1, 1, 1, 0, 1, 1, 1};

        BigInteger tot = BigInteger.ONE;
        BigInteger curr = BigInteger.TWO;

        // successive squaring algorithm using power array above
        for (int i = 0; i < powerArr.length; i++) {
            if (powerArr[i] == 1) {
                tot = tot.multiply(curr).remainder(BigInteger.TEN.pow(10));
            }
            curr = curr.pow(2).remainder(BigInteger.TEN.pow(10));
        }
        // add remaining operators, x28433 and +1, and mod 1E10
        tot = tot.multiply(new BigInteger("28433")).add(BigInteger.ONE).remainder(BigInteger.TEN.pow(10));

        System.out.println("Last ten digits of result: " + tot.toString());
    }
}
