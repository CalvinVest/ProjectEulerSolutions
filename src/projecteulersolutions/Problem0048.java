package projecteulersolutions;

/*
The goal of problem 48 is to find the last ten digits
of the sum of all self powers up to 1000.

A self power is a number raised to the power of itself.

if f(x) = x^x,
find the last ten digits of f(1) + f(2) + ... + f(1000)
 */
public class Problem0048 extends Problem {

    @Override
    public boolean isSolved() {
        return true;
    }

    @Override
    public void printSolution() {
        long result = 0;
        long modulo = 10000000000l; // ten zeros, to find last ten digits

        for (int i = 1; i <= 1000; i++) {
            long temp = i;
            for (int j = 1; j < i; j++) {
                temp *= i;
                temp %= modulo;
            }

            result += temp;
            result %= modulo;
        }
        System.out.println("The last ten digits of the sum of the first 1000 self powers is " + result);
    }
}
