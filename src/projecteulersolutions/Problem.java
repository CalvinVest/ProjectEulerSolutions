package projecteulersolutions;

/*
Problem.java is an abstract class that provides a single print
function and is the superclass to all Project Euler problems.

The printAnswer() function is overwritten in all subclass
implementations of Problem.java to include actual results for
the particular problem.
*/
public abstract class Problem {
    public void printAnswer() {
        // If a subclass implementation has not overwritten the
        // printAnswer() function, default print indicates no
        // solution is present.
        System.out.println("This problem has not yet been solved.");
    }
}
