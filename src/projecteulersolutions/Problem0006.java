package projecteulersolutions;

/*
The goal of problem 6 is to find the difference between
the sum of the squares of the first 100 natural numbers and
the square of the sum of the first 100 natural numbers.

Essentially,
(1 + 2 + ... + 100)^2 - (1^2 + 2^2 + ... + 100^2)

When it came to finding the size of the necessary variables, the 
largest number we'll be dealing with is the square of the sum of the
first 100 natural numbers. The total value would be 5050^2 which is
well within the bounds of int.
*/
public class Problem0006 extends Problem {
    
    @Override
    public void printSolution() {
        // initiate sum variables
        int sum = 0; // sum of 1 to 100
        int sumOfSquares = 0; // sum of 1^2 to 100^2
        for(int i = 1; i <= 100; i++) { // for 1 to 100
            sum += i; // add all values to sum
            sumOfSquares += i * i; // add square of all values to sum
        }
        // square of sum 1 to 100
        int squareOfSums = sum * sum;
        
        // print answer
        System.out.println("The difference between the sum of "
            + "squares and square of sums for 1-100 is "
            + (squareOfSums - sumOfSquares));
    }
}
