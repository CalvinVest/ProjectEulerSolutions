package main.java.projecteulersolutions.problems;

/*
The goal of problem 5 is to find the smallest number
divisible by all the natural numbers from 1 to 20.
 */
@SuppressWarnings("unused")
public class Problem0005 extends Problem {

    @Override
    public boolean isSolved() {
        return true;
    }

    @Override
    public void printSolution() {
        long answer = 1; // storage var for final answer

        //continues while an answer has yet to be found
        while (!isDivisible(answer)) {
            answer++;
        }

        // answer output
        System.out.println("The smallest number divisible by numbers 1-20 is " + answer);
    }

    private boolean isDivisible(long num) {
        // Divisibility by all numbers 1-20 can be simplified:
        // Divisible by 1 is superfluous for any natural number.
        // Divisible by 6 is equivalent to by 2 and 3
        // Divisible by 8 is equivalent to by 2 and 4
        // Divisible by 10 is equivalent to by 2 and 5
        // Divisible by 12 is equivalent to by 3 and 4
        // Divisible by 14 is equivalent to by 2 and 7
        // Divisible by 15 is equivalent to by 3 and 5
        // Divisible by 18 is equivalent to by 2 and 9
        return num % 2 == 0
                && num % 3 == 0
                && num % 4 == 0
                && num % 5 == 0
                && num % 7 == 0
                && num % 9 == 0
                && num % 11 == 0
                && num % 13 == 0
                && num % 16 == 0
                && num % 17 == 0
                && num % 19 == 0;
    }
}
