package projecteulersolutions.problems;

import java.io.File;

/*
Problem.java is an abstract class that provides a single print
function and is the superclass to all Project Euler problems.

The printSolution() function is overridden in all subclass
implementations of Problem.java to include actual results for
the particular problem.
 */
public abstract class Problem {

    public static final String FILEPATH = System.getProperty("user.dir") + "\\src\\projecteulersolutions\\";

    public static final String PROBLEM_FILEPATH = System.getProperty("user.dir") + "\\src\\projecteulersolutions\\problems\\";

    public abstract void printSolution();

    public abstract boolean isSolved();

    /*
    getFileName(int) returns the formatted filename for a problem
    with the given int.
    
    Formats to the following pattern:
    123 -> Problem0123.java
     */
    public static String getFileName(int problemNumber) {
        return String.format("Problem%04d.java", problemNumber);
    }

    public static String[] getProblemFiles() {
        return new File(Problem.PROBLEM_FILEPATH).list();
    }
}
