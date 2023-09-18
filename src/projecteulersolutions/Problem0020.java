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
    public void printSolution() {
        BigInteger factorial = getFactorial(100);
        int digitSum = sumDigits(factorial);
        System.out.println("The sum of the digits of (100)! is " + digitSum);
    }

    private BigInteger getFactorial(int n) {
        BigInteger factorial = BigInteger.ONE;

        for (int i = 2; i <= n; i++) {
            factorial = factorial.multiply(BigInteger.valueOf(i));
        }

        return factorial;
    }

    private int sumDigits(BigInteger n) {
        int sum = 0;

        String[] numStrs = n.toString().split("");

        for (String str : numStrs) {
            sum += Integer.parseInt(str);
        }

        return sum;
    }
}
