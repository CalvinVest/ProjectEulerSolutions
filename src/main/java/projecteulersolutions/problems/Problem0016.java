package main.java.projecteulersolutions.problems;

import java.math.BigInteger;

/*
The goal of problem 16 is to find the sum of the digits of the number 2^1000.

2^15 = 32768, sum of digits is 3+2+7+6+8 = 26
f(15) = 26, find f(1000).
 */
@SuppressWarnings("unused")
public class Problem0016 extends Problem {

    @Override
    public boolean isSolved() {
        return true;
    }

    @Override
    public void printSolution() {
        BigInteger val = new BigInteger("2").pow(1000);

        int sum = 0;
        for (String str : val.toString().split("")) {
            sum += Integer.parseInt(str);
        }
        System.out.println("The sum of the digits of 2^1000 is " + sum);
    }
}
