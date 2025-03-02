package main.java.projecteulersolutions.problems;

import java.math.BigInteger;

/*
The 5-digit number, 16807 = 7^5, is also a fifth power. Similarly, the 9-digit
number, 134217728 = 8^9, is a ninth power.

How many n-digit positive integers exist which are also nth powers?
 */
@SuppressWarnings("unused")
public class Problem0063 extends Problem {

    @Override
    public boolean isSolved() {
        return true;
    }

    @Override
    public void printSolution() {
        // the count of all values where i^j = j.length()
        int powerfulDigitCount = getPowerfulDigitCount();
        System.out.println("The count of powerful digit numbers is " + powerfulDigitCount);
    }

    private int getPowerfulDigitCount() {
        int count = 0;
        int exponentCeiling = getExponentCeiling();
        for (int base = 1; base < 10; base++) { // a base >= 10 would result in more digits than the exponent value
            for (int exp = 1; exp <= exponentCeiling; exp++) {
                // gets BigInt value of base^exp
                BigInteger bigIntPow = new BigInteger(Integer.toString(base)).pow(exp);
                String powString = bigIntPow.toString();
                // if (base^exp).length() == exp, valid case; increment count.
                if (powString.length() == exp) {
                    count++;
                }
            }
        }
        return count;
    }

    /*
    My reasoning for the algorithm in getExponentCeiling is straightforward:
    
    The smallest base value for which the digit count of the base raised to a given
    exponent x is equal to x is 9.
    This is because 10, the next value, is THE base for base-10 counting. As a
    result, 10^x will ALWAYS be x + 1 digits. Since we're only interested in
    values where base^x == x.length(), any values >= 10 do not need to be
    evaluated.
    
    Now that the ceiling for the base value has been found, it can be used to find
    the ceiling for the exponent. Eventually, since 9 < 10, 9^x will eventually
    be less than x.length(). Once this occurs, all further values of x will not
    be valid solutions and the found exponent ceiling can be returned.
    
    By finding the lowest reasonable ceiling for both base and exp, I've been
    able to reduce my runtime of 100ms for base, exp = 100 to 7ms for 
    base <= 9, exp <= 22
    */
    private int getExponentCeiling() {
        int exp = 1;

        // BigInts to hold values for 9 and 9^x, respectively.
        BigInteger nine = BigInteger.TEN.subtract(BigInteger.ONE);
        BigInteger pow = nine.pow(exp);

        // eventually 9^x will be less than x.length(), giving the ceiling for
        // the greatest exponent need actually be considered for getPowerfulDigitCount.
        while (exp <= pow.toString().length()) {
            exp++;
            pow = nine.pow(exp);
        }
        return exp;
    }
}
