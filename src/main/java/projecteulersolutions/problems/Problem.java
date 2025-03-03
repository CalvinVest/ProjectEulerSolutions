package main.java.projecteulersolutions.problems;

/*
Problem.java is an abstract class that provides a single print
function and is the superclass to all Project Euler problems.

The printSolution() function is overridden in all subclass
implementations of Problem.java to include actual results for
the particular problem.
 */
@SuppressWarnings("unused")
public abstract class Problem {

    public abstract void printSolution();

    public abstract boolean isSolved();

}
