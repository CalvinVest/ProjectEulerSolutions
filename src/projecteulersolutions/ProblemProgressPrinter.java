package projecteulersolutions;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class ProblemProgressPrinter {

    private String filepath = Problem.FILEPATH + "problems.txt";
    private ArrayList<String> progress;
    private static final int PROBLEM_COUNT = 855;

    public enum ProgressType {
        COMPLETE,
        COMPLETE_NOT_ON_GITHUB,
        IN_PROGRESS,
        BROKEN,
        INCOMPLETE
    }

    public ProblemProgressPrinter() {
        File file = new File(filepath);
        try {
            progress = readProgressFromFile(file);

            for (int i = 0; i < progress.size(); i += 3) {
                System.out.println(progress.get(i) + " | " + progress.get(i + 1) + " | " + progress.get(i + 2));
            }

        } catch (FileNotFoundException fnfe) {
            System.out.println("Failure: Progress file problems.txt does not exist.");
        }
    }

    private void saveProgressToFile(File file) throws IOException {
        FileWriter fw = new FileWriter(file);
        for (int i = 0; i < progress.size(); i += 3) {
            fw.write(progress.get(i) + " " + progress.get(i + 1)
                    + " " + progress.get(i + 2) + "\n");
        }
        fw.close();
    }

    private ArrayList<String> readProgressFromFile(File file) throws FileNotFoundException {
        Scanner fileIn = new Scanner(file);
        ArrayList<String> values = new ArrayList<>();

        while (fileIn.hasNext()) {
            values.add(fileIn.next());
        }

        return values;
    }

    private void setAllProgressToIncomplete() {
        for (int i = 0; i < PROBLEM_COUNT * 3; i += 3) {
            progress.set(i, "" + (i/3 + 1));
            progress.set(i + 1, ProgressType.INCOMPLETE.toString());
            progress.set(i + 2, ":black_circle:");
        }

    }
}
