package projecteulersolutions;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class ProgressFileReader {

    private static final String FILEPATH = Problem.FILEPATH + "progress.txt";
    private final File file;

    public ProgressFileReader() {
        file = new File(FILEPATH);
    }

    public ArrayList<String> getProgress() {
        try {
            return loadProgressFromFile();
        } catch (FileNotFoundException fnfe) {
            System.out.println("Failed: " + file.getName() + " does not exist.");
        }
        return null;
    }

    /*
    loadProgressFromFile loads all statuses from the progress.txt file.
     */
    private ArrayList<String> loadProgressFromFile() throws FileNotFoundException {
        ArrayList<String> values = new ArrayList<>();
        try ( Scanner fileIn = new Scanner(file)) {
            while (fileIn.hasNext()) {
                values.add(fileIn.next());
            }
            fileIn.close();
        }
        return values;
    }
}
