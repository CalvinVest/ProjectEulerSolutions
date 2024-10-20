package projecteulersolutions;

/*
EulerUtils is a general utilities interface I intend to use for some of the commonly used functions and
resources like file paths and problem logic. This will probably end up interacting with EulerMath or being thrown in
a utilities package with it in the future.
 */

import projecteulersolutions.problems.Problem;

import java.io.File;

public interface EulerUtils {
    static final String FILEPATH = System.getProperty("user.dir") + "\\src\\projecteulersolutions\\";
    static final String PROBLEM_FILEPATH = FILEPATH + "problems\\";
    static final String PROGRESS_FILEPATH = FILEPATH + "progress.txt";
    static final String DATA_FILEPATH = FILEPATH + "data\\";

    public static String[] getProblemFileList() {
        return new File(PROBLEM_FILEPATH).list();
    }

    public static String getProblemFileName(int problemNumber) {
        return String.format("Problem%04d.java", problemNumber);
    }

}
