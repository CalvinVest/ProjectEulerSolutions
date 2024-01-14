package projecteulersolutions;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class EulerReader {

    private static final String FILEPATH = Problem.FILEPATH + "progress.txt";
    private final File file;

    public EulerReader() {
        file = new File(FILEPATH);
    }

    public ArrayList<String> loadProgress() {

        try (Scanner fileIn = new Scanner(file)) {
            ArrayList<String> values = new ArrayList<>();
            while (fileIn.hasNext()) {
                values.add(fileIn.next());
            }
            return values;
        } catch (FileNotFoundException fnfe) {
            System.out.println("Failed: " + file.getName() + " does not exist.");
        }
        return null;
    }
}
