package main.java.projecteulersolutions.problems;

import java.math.BigInteger;

/*
The goal of problem 56:

A googol (10^100) is a massive number: one followed by one-hundred zeros; 10^100
is almost unimaginably large: one followed by two-hundred zeros. Despite their size,
the sum of the digits in each number is only 1.

Considering natural numbers of the for a^b where a,b < 100, what is the maximum digital sum?
 */
@SuppressWarnings("unused")
public class Problem0056 extends Problem {

    @Override
    public boolean isSolved() {
        return true;
    }

    @Override
    public void printSolution() {
        int maxSum = 0;

        for (int i = 2; i < 100; i++) {
            for (int j = 2; j < 100; j++) {
                if (i % 10 == 0 || j % 10 == 0) {
                    continue;
                }
                int digitSum = getBigIntDigitSum(getBigIntPow(i, j));
                if (digitSum > maxSum) {
                    maxSum = digitSum;
                }
            }
        }
        System.out.println("The largest digital sum of two two-digit powers is " + maxSum);
    }

    private BigInteger getBigIntPow(int a, int b) {
        BigInteger bigA = new BigInteger(Integer.toString(a));
        return bigA.pow(b);
    }

    private int getBigIntDigitSum(BigInteger n) {
        int sum = 0;
        while (!n.toString().equals("0")) {
            sum += n.mod(BigInteger.TEN).intValue();
            n = n.divide(BigInteger.TEN);
        }
        return sum;
    }
}
