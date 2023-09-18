package projecteulersolutions;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class ProblemProgressPrinter {

    private static final int PROBLEM_COUNT = 855;
    private static final String FILEPATH = Problem.FILEPATH + "problems.txt";
    private File file;
    private ArrayList<String> progress;

    public enum ProgressType {
        COMPLETE,
        COMPLETE_NOT_ON_GITHUB,
        IN_PROGRESS,
        BROKEN,
        INCOMPLETE
    }

    public ProblemProgressPrinter() {
        file = new File(FILEPATH);
        try {
            progress = readProgressFromFile(file);

        } catch (FileNotFoundException fnfe) {
            System.out.println("Failure: Progress file problems.txt does not exist.");
        }
    }

    public void editProgressValue(int problemNumber, ProgressType type) {
        int progressValueIndex = (problemNumber - 1) * 3;

        String problemString = Integer.toString(problemNumber);
        String emojiString = "";

        switch (type) {
            case COMPLETE ->
                emojiString = ":green_circle:";
            case COMPLETE_NOT_ON_GITHUB ->
                emojiString = ":large_blue_circle:";
            case IN_PROGRESS ->
                emojiString = ":orange_circle:";
            case BROKEN ->
                emojiString = ":red_circle:";
            case INCOMPLETE ->
                emojiString = ":black_circle:";
        }
        setProgressValue(progressValueIndex,
                problemString,
                type.toString(),
                emojiString);
        
        try {
            saveProgressToFile();
        } catch(IOException ioe) {
            System.out.println("Failed: IOException - " + ioe.getMessage());
        }
    }

    private void setProgressValue(int index, String problemNumberText, String typeString, String emojiString) {
        progress.set(index, problemNumberText);
        progress.set(index + 1, typeString);
        progress.set(index + 2, emojiString);
    }

    private void saveProgressToFile() throws IOException {
        FileWriter fw = new FileWriter(file);
        for (int i = 0; i < progress.size(); i += 3) {
            fw.write(progress.get(i) + " " + progress.get(i + 1)
                    + " " + progress.get(i + 2) + "\n");
        }
        fw.close();
        System.out.println("Saved to file: " + file.getName());
    }

    private ArrayList<String> readProgressFromFile(File file) throws FileNotFoundException {
        Scanner fileIn = new Scanner(file);
        ArrayList<String> values = new ArrayList<>();

        while (fileIn.hasNext()) {
            values.add(fileIn.next());
        }

        return values;
    }

    public void regenerateValues() {
        setAllProgressToIncomplete();
        setCompleteProblemsFromFiles();
    }

    private void setAllProgressToIncomplete() {
        progress.clear();
        for (int i = 0; i < PROBLEM_COUNT * 3; i += 3) {
            int problemNumber = i / 3 + 1;
            progress.add(Integer.toString(problemNumber));
            progress.add(ProgressType.INCOMPLETE.toString());
            progress.add(":black_circle:");
        }
    }

    public void setCompleteProblemsFromFiles() {
        String[] pathnames = new File(Problem.FILEPATH).list();

        for (String pathname : pathnames) {
            if (pathname.matches("Problem\\d\\d\\d\\d.java")) {
                int problemNumber = Integer.parseInt(pathname.substring(7, 11));
                int progressValueIndex = 3 * (problemNumber - 1);
                progress.set(progressValueIndex + 1, ProgressType.COMPLETE.toString());
                progress.set(progressValueIndex + 2, ":green_circle:");
            }
        }
    }

    public String getProblemStatus(int problemNumber) {
        int progressIndex = (problemNumber - 1) * 3;
        return progress.get(progressIndex + 1);
    }

    public void printProgressValues() {
        for (int i = 0; i < progress.size(); i += 3) {
            System.out.println(progress.get(i) + " | " + progress.get(i + 1) + " | " + progress.get(i + 2));
        }
    }
}
