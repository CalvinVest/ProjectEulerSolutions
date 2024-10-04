package projecteulersolutions.problems;

@SuppressWarnings("unused")
public class Problem0002 extends Problem {

    @Override
    public boolean isSolved() {
        return true;
    }

    /*
    The goal of problem 2 is to find the sum of all
    even-numbered Fibonacci numbers under four million.
     */
    @Override
    public void printSolution() {
        int curr = 0; // current fibonacci number
        int last1 = 1; // first fibonacci prior number
        int last2 = 1; // second fibonacci prior number
        long sum = 0; // running sum of even fibonacci numbers

        while (curr < 4000000) {
            // calculate current fibonacci number
            curr = last1 + last2;
            if (curr % 2 == 0) // even check
            {
                sum += curr; // adds current fibonacci number to sum
            }
            // cycle prior fibonacci numbers
            // to be added at start of next loop
            last2 = last1;
            last1 = curr;
        }
        System.out.println("The sum of all even Fibonacci numbers below 4,000,000 is " + sum);
    }
}
