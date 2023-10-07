package projecteulersolutions;

import java.math.BigInteger;

/*
The goal of problem 20 is to find the sum of the digits of (100)!

(10)! = 10 * 9 * ... * 1 = 3628800
f(10) = 3 + 6 + 2 + 8 + 8 + 0 + 0 = 27
find f(100)
 */
public class Problem0020 extends Problem {

    @Override
    public boolean isSolved() {
        return true;
    }

    @Override
    public void printSolution() {
        // BigInteger object to hold the value of 100!
        BigInteger factorial = EulerMath.bigFactorial(100);
        // int value to hold the sum of the digits of 100!
        int digitSum = sumDigits(factorial);
        System.out.println("The sum of the digits of (100)! is " + digitSum);
    }

    private int sumDigits(BigInteger n) {
        // int sum to hold sum of digit values
        int sum = 0;

        // splits the given BigInteger into one-char strings of each digit
        String[] numStrs = n.toString().split("");

        // for each digit of the given BigInteger value
        for (String str : numStrs) {
            // add the digit value to the sum of digit values
            sum += Integer.parseInt(str);
        }

        return sum;
    }
}
