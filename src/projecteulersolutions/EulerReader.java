package projecteulersolutions;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import projecteulersolutions.problems.Problem;

public class EulerReader {
    private final File file;

    public EulerReader() {
        file = new File(EulerUtils.PROGRESS_FILEPATH);
    }

    public ArrayList<String> loadProgress() {

        try (Scanner fileIn = new Scanner(file)) {
            ArrayList<String> values = new ArrayList<>();
            while (fileIn.hasNext()) {
                values.add(fileIn.next());
            }
            return values;
        } catch (FileNotFoundException fnfe) {
            EulerConsole.printExceptionMessage(fnfe, "Failed: " + file.getName() + " does not exist.");
        }
        return null;
    }
}
