package projecteulersolutions;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class ProgressWriter {

    private static final int PROBLEM_COUNT = 855;
    private static final String FILEPATH = Problem.FILEPATH + "problems.txt";
    private File file;
    private ArrayList<String> values;

    public final String[] TYPE = {
        "COMPLETE",
        "COMPLETE_NOT_ON_GITHUB",
        "IN_PROGRESS",
        "BROKEN",
        "INCOMPLETE"};

    public ProgressWriter() {
        file = new File(FILEPATH);
        values = new ArrayList<>();
        try {
            loadProgressFromFile(file);

        } catch (FileNotFoundException fnfe) {
            System.out.println("Failure: Progress file problems.txt does not exist.");
        }
    }

    public void setProblemStatus(int problemNumber, String type) {
        setProgressValue(problemNumber, type, getEmojiString(type));

        try {
            saveProgressToFile();
        } catch (IOException ioe) {
            System.out.println("Failed: IOException - " + ioe.getMessage());
        }
    }

    private String getEmojiString(String type) {
        return switch (type) {
            case "COMPLETE" ->
                ":green_circle:";
            case "COMPLETE_NOT_ON_GITHUB" ->
                ":large_blue_circle:";
            case "IN_PROGRESS" ->
                ":orange_circle:";
            case "BROKEN" ->
                ":red_circle:";
            case "INCOMPLETE" ->
                ":black_circle:";
            default ->
                ":black_circle:";
        };
    }

    private void setProgressValue(int problemNumber, String typeString, String emojiString) {
        int index = (problemNumber - 1) / 3;
        values.set(index, Integer.toString(problemNumber));
        values.set(index + 1, typeString);
        values.set(index + 2, emojiString);
    }

    private void saveProgressToFile() throws IOException {
        FileWriter fw = new FileWriter(file);
        ReadmeGenerator rg = new ReadmeGenerator();
        for (int i = 0; i < values.size(); i += 3) {
            fw.write(values.get(i) + " " + values.get(i + 1)
                    + " " + values.get(i + 2) + "\n");
        }
        fw.close();
        rg.generateReadme();
        System.out.println("Saved to file: " + file.getName());
    }

    private void loadProgressFromFile(File file) throws FileNotFoundException {
        Scanner fileIn = new Scanner(file);

        values.clear();
        while (fileIn.hasNext()) {
            values.add(fileIn.next());
        }
    }

    public void regenerateValues(Scanner userIn) {

        System.out.print("Confirm overwrite? Data will be lost! y/n\n> ");
        char userConfirmChar = ProjectEulerSolutions.getNextUserChar(userIn);
        switch (userConfirmChar) {
            case 'y' ->
                setValuesFromFiles();
            case 'n' ->
                System.out.println("Aborted: Operation cancelled by user.");
            default ->
                System.out.println("Aborted: Invalid entry.");

        }
    }

    public void setValuesFromFiles() {
        // clear values list and reset all statuses to incomplete
        values.clear();
        for (int i = 1; i <= PROBLEM_COUNT; i++) {
            values.add(Integer.toString(i));
            values.add(TYPE[4]);
            values.add(getEmojiString(TYPE[4]));
        }

        // all files in the project folder
        String[] pathnames = new File(Problem.FILEPATH).list();

        // set all of the completed or in progress files based on existence of files
        for (String pathname : pathnames) {
            if (pathname.matches("^Problem\\d\\d\\d\\d.java$")) {
                String problemNumberText = pathname.substring(7,11);
                int problemNumber = Integer.parseInt(problemNumberText);
                int index = 3 * (problemNumber - 1);
                boolean isSolved = (boolean) new JavaClassLoader().invokeClassMethod("projecteulersolutions.Problem" + problemNumberText, "isSolved");

                int type;
                if (isSolved) {
                    System.out.println(pathname + " is solved.");
                    type = 0;
                } else {
                    System.out.println(pathname + " is in progress.");
                    type = 2;
                }
                String emojiString = getEmojiString(TYPE[type]);

                values.set(index + 1, TYPE[type]);
                values.set(index + 2, emojiString);
            }
        }

        try {
            saveProgressToFile();
        } catch (IOException ioe) {
            System.out.println("Failed: Could not save to file.\n" + ioe.getMessage());
        }
    }

    public String getProblemStatus(int problemNumber) {
        int progressIndex = (problemNumber - 1) * 3;
        return values.get(progressIndex + 1);
    }

    public void printStatusList() {
        for (int i = 0; i < values.size(); i += 3) {
            System.out.println(values.get(i) + " | " + values.get(i + 1));
        }
    }

    public ArrayList<String> getProgressValues() {
        return values;
    }
}
