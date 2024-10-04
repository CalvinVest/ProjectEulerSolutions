package projecteulersolutions.problems;

import java.math.BigInteger;

/*
The goal of problem 25 is to find the index of the first term of 
the Fibonacci sequence to contain 1000 digits.
 */
@SuppressWarnings("unused")
public class Problem0025 extends Problem {

    @Override
    public boolean isSolved() {
        return true;
    }

    @Override
    public void printSolution() {
        BigInteger last1 = new BigInteger("1");
        BigInteger last2 = new BigInteger("1");
        BigInteger curr;
        int index = 2;

        int digitCount = 0;

        while (digitCount < 1000) {
            curr = last1.add(last2);
            index++;
            digitCount = curr.toString().length();

            last2 = last1;
            last1 = curr;
        }

        System.out.println("The index of the first Fibonacci number that contains 1000 digits is " + index);
    }
}
