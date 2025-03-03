package main.java.projecteulersolutions;

/*
EulerUtils is a general utilities interface I intend to use for some of the commonly used functions and
resources like file paths and problem logic. This will probably end up interacting with EulerMath or being thrown in
a utilities package with it in the future.
 */

import java.io.File;

public interface EulerUtils {
    String FILEPATH = System.getProperty("user.dir") + "\\src\\main\\java\\projecteulersolutions\\";
    String PROBLEM_FILEPATH = FILEPATH + "problems\\";
    String PROGRESS_FILEPATH = FILEPATH + "progress.txt";
    String DATA_FILEPATH = FILEPATH + "data\\";

    static String[] getProblemFileList() {
        return new File(PROBLEM_FILEPATH).list();
    }

    static String getProblemFileName(int problemNumber) {
        return String.format("Problem%04d.java", problemNumber);
    }

}
