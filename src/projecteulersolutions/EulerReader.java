package projecteulersolutions;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class EulerReader {

    private static final Logger logger = Logger.getLogger(EulerReader.class.getName());

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
            logger.log(Level.SEVERE, "Failed: " + file.getName() + " does not exist.", fnfe);
        }
        return null;
    }
}
