package projecteulersolutions;

/*
EulerUtils is a general utilities interface I intend to use for some of the commonly used functions and
resources like file paths and problem logic. This will probably end up interacting with EulerMath or being thrown in
a utilities package with it in the future.
 */

import projecteulersolutions.problems.Problem;

import java.io.File;

public interface EulerUtils {


    public static final String FILEPATH = System.getProperty("user.dir") + "\\src\\projecteulersolutions\\";
    public static final String PROBLEM_FILEPATH = System.getProperty("user.dir") + "\\src\\projecteulersolutions\\problems\\";

    public static String[] getProblemFiles() {
        return new File(Problem.PROBLEM_FILEPATH).list();
    }
}
