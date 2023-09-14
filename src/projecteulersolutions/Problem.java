package projecteulersolutions;

/*
Problem.java is an abstract class that provides a single print
function and is the superclass to all Project Euler problems.

The printSolution() function is overridden in all subclass
implementations of Problem.java to include actual results for
the particular problem.
*/
public abstract class Problem {
    public static final String FILEPATH = System.getProperty("user.dir") + "\\src\\projecteulersolutions\\";
    
    public abstract void printSolution();
    
    /*
    getFileName(int) returns the formatted filename for a problem
    with the given int.
    
    Formats to the following pattern:
    123 -> Problem0123.java
    
    Throws IllegalArgumentException for integer values outside of the 1-9999 range
    */
    public static String getFileName(int problemNumber) {
        if(problemNumber > 0 && problemNumber < 10000) {
            return String.format("Problem%04d.java", problemNumber);
        }
        throw new IllegalArgumentException("Invalid problem value: " + problemNumber);
    }
}
