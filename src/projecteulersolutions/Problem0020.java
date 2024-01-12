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
        BigInteger factorial = getBigIntFactorial(100);
        // int value to hold the sum of the digits of 100!
        int digitSum = getBigIntDigitSum(factorial);
        System.out.println("The sum of the digits of (100)! is " + digitSum);
    }

    private int getBigIntDigitSum(BigInteger n) {
        int sum = 0;
        for (String str : n.toString().split("")) {
            sum += Integer.parseInt(str);
        }
        return sum;
    }

    private BigInteger getBigIntFactorial(int n) {
        BigInteger factorial = BigInteger.ONE;

        for (int i = 2; i <= n; i++) {
            factorial = factorial.multiply(BigInteger.valueOf(i));
        }
        return factorial;
    }
}
