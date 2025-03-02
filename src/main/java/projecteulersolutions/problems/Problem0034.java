package main.java.projecteulersolutions.problems;

import main.java.projecteulersolutions.EulerMath;

/*
The goal of problem 34 is to find the sum of all numbers which can be
expressed as the sum of the factorial of their digits.

Where
f(145) = 1! + 4! + 5! = 1 + 24 + 120 = 145
145 is a factorial digit value.

Find the sum of all factorial digit values.

This problem is VERY similar to problem 30, and much of the process and
documentation for this problem was taken from my solution for that problem.
 */
@SuppressWarnings("unused")
public class Problem0034 extends Problem {

    /*
    The upper bound for this problem's loop can be found by first evaluating how
    many digits the bound will be. As we know the largest calculated value for any
    given digit of the value is 9!, we will use that to represent a given digit
    factorial value. The upper bound for this problem will be d * 9! where
    d is the number of digits.
    It can then be asserted:
    10^(d-1) <= d * 9! < 10^d
    Take the log_10 of all expressions:
    d-1 <= log(d) + log(9!) < d
    d-1 <= log(d) + 5.56 < d
    -1 - 5.56 <= log(d) - d < -5.56
    -6.56 <= log(d) - d < -5.56
    6.56 > d - log(d) >= 5.56
    5.56 <= d - log(d) < 6.56
    d = [6.3637104,7.43105019]
    The upper bound is 7 digits.
    We can further reduce the upper bound by understanding the maximum value obtainable
    within 7 digits is 7 * 9!, which is 2540160. This is the upper bound.
     */
    private static final int UPPER_BOUND = 2540160;

    @Override
    public boolean isSolved() {
        return true;
    }

    @Override
    public void printSolution() {
        int sum = 0;

        for (int i = 3; i <= UPPER_BOUND; i++) {
            if (isFactorialDigitValue(i)) {
                sum += i;
            }
        }

        System.out.println("The sum of all factorial digit sums is " + sum);
    }

    /*
    isFactorialDigitValue(int) returns true if the given int value
    is a sum of the factorial of its digits.
    
    For example,
    return 1234 == 1! + 2! + 3! + 4!;
     */
    private boolean isFactorialDigitValue(int n) {
        int temp = n;
        int sum = 0;
        while (temp > 0) {
            sum += (int) EulerMath.getFactorial(temp % 10);
            temp /= 10;
        }
        return sum == n;
    }
}
