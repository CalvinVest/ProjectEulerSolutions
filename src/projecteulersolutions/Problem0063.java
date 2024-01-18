package projecteulersolutions;

import java.math.BigInteger;

/*
The 5-digit number, 16807 = 7^5, is also a fifth power. Similarly, the 9-digit
number, 134217728 = 8^9, is a ninth power.

How many n-digit positive integers exist which are also an nth power?
 */
public class Problem0063 extends Problem {

    @Override
    public boolean isSolved() {
        return true;
    }

    @Override
    public void printSolution() {
        int powerfulDigitCount = getPowerfulDigitCount();
        System.out.println("The count of powerful digit numbers is " + powerfulDigitCount);
    }

    private int getPowerfulDigitCount() {
        int count = 0;
        int exponentCeiling = getExponentCeiling();
        for (int i = 1; i < 10; i++) { // a base >= 10 would result in more digits than the exponent value
            for (int j = 1; j <= exponentCeiling; j++) {
                BigInteger bigIntPow = new BigInteger(Integer.toString(i)).pow(j);
                String powString = bigIntPow.toString();
                if (powString.length() == j) {
                    count++;
                }
            }
        }
        return count;
    }

    private int getExponentCeiling() {
        int ceiling = 1;

        BigInteger nine = BigInteger.TEN.subtract(BigInteger.ONE);
        BigInteger pow = nine.pow(ceiling);

        // eventually 9^x will be less than digits(x), giving the ceiling for
        // what exponents need actually be considered for getPowerfulDigitCount.
        while (ceiling <= pow.toString().length()) {
            ceiling++;
            pow = nine.pow(ceiling);
        }
        return ceiling;
    }
}
