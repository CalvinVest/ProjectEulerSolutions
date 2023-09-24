package projecteulersolutions;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class ProgressFileWriter {

    private static final String FILEPATH = Problem.FILEPATH + "progress.txt";
    private final File file;

    public ProgressFileWriter() {
        file = new File(FILEPATH);
    }

    public void setProgress(ArrayList<String> values) {
        saveProgressToFile(values);
    }

    /*
    saveProgressToFile saves all statuses to the progress.txt file and
    generates a new readme file from the updated progress file.
     */
    private void saveProgressToFile(ArrayList<String> values) {
        ReadmeGenerator rg = new ReadmeGenerator();
        try ( FileWriter fw = new FileWriter(file)) {
            for (int i = 0; i < values.size(); i += 2) {
                fw.write(values.get(i) + " " + values.get(i + 1) + "\n");
            }
            fw.close();
        } catch (IOException ioe) {
            System.out.println("Failed: Could not save to file.\n" + ioe.getMessage());
        }
        System.out.println("Saved to file: " + file.getName());
        rg.generateReadme();
    }
}
