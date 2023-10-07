package projecteulersolutions;

/*
The goal of problem 15 is to find the total number of paths from the top left
corner of a lattice to the bottom right corner only going right and down.

As an example provided, the number of paths for a 2x2 lattice is 6:
RRDD
RDRD
RDDR
DRRD
DRDR
DDRR

This problem can be solved with combinatrics.
Our solution will always be n of R + n of D for an n*n matrix.
Thus, the solution is 40 R and D for a 20*20 matrix.
This is given as the formula (40!)/(20! * 20!)
 */
public class Problem0015 extends Problem {

    @Override
    public boolean isSolved() {
        return true;
    }

    @Override
    public void printSolution() {
        int latticeWidth = 20;
        double solution = EulerMath.getFactorial(latticeWidth * 2)
                / EulerMath.getFactorial(latticeWidth)
                / EulerMath.getFactorial(latticeWidth);
        System.out.println("The number of paths in a 20x20 lattice is " + (long) solution);
    }
}
