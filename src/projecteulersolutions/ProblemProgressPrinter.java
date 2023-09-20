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
    private ArrayList<String> values;

    public final String[] TYPE = {
        "COMPLETE",
        "COMPLETE_NOT_ON_GITHUB",
        "IN_PROGRESS",
        "BROKEN",
        "INCOMPLETE"};

    public ProblemProgressPrinter() {
        file = new File(FILEPATH);
        values = new ArrayList<>();
        try {
            readProgressFromFile(file);

        } catch (FileNotFoundException fnfe) {
            System.out.println("Failure: Progress file problems.txt does not exist.");
        }
    }

    public void setProblemStatus(int problemNumber, String type) {
        int index = (problemNumber - 1) * 3;
        String problemString = Integer.toString(problemNumber);
        String emojiString = getEmojiString(type);

        setProgressValue(index, problemString, type, emojiString);

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

    private void setProgressValue(int index, String problemNumberText, String typeString, String emojiString) {
        values.set(index, problemNumberText);
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

    private void readProgressFromFile(File file) throws FileNotFoundException {
        Scanner fileIn = new Scanner(file);

        values.clear();
        while (fileIn.hasNext()) {
            values.add(fileIn.next());
        }
    }

    public void regenerateValues(Scanner userIn) {
        setAllProgressToIncomplete();
        setCompleteProblemsFromFiles();

        System.out.print("Confirm overwrite? Data will be lost! y/n\n> ");
        char userConfirmChar = userIn.next().toLowerCase().charAt(0);
        switch (userConfirmChar) {
            case 'y' -> {
                try {
                    saveProgressToFile();
                } catch (IOException ioe) {
                    System.out.println("Failed: IOException - " + ioe.getMessage());
                }
            }
            case 'n' -> {
                try {
                    readProgressFromFile(file);
                } catch (FileNotFoundException ex) {
                    System.out.println("Failed: File " + file.getName() + " not found.");
                }
            }

        }
    }

    private void setAllProgressToIncomplete() {
        values.clear();
        for (int i = 1; i <= PROBLEM_COUNT; i++) {
            values.add(Integer.toString(i));
            values.add(TYPE[4]);
            values.add(getEmojiString(TYPE[4]));
        }
    }

    public void setCompleteProblemsFromFiles() {
        String[] pathnames = new File(Problem.FILEPATH).list();

        for (String pathname : pathnames) {
            if (pathname.matches("Problem\\d\\d\\d\\d.java")) {
                int problemNumber = Integer.parseInt(pathname.substring(7, 11));
                String problemNumberText = String.format("%04d", problemNumber);
                int progressValueIndex = 3 * (problemNumber - 1);
                String emojiString;
                JavaClassLoader jcl = new JavaClassLoader();
                boolean isSolved = (boolean) jcl.invokeClassMethod("projecteulersolutions.Problem" + problemNumberText, "isSolved");

                int type;
                if (isSolved) {
                    System.out.println(pathname + " is solved.");
                    type = 0;
                } else {
                    System.out.println(pathname + " is in progress.");
                    type = 2;
                }
                emojiString = getEmojiString(TYPE[type]);

                values.set(progressValueIndex + 1, TYPE[type]);
                values.set(progressValueIndex + 2, emojiString);
            }
        }

        try {
            saveProgressToFile();
        } catch (IOException ioe) {
            System.out.println("Failed: IOException - " + ioe.getMessage());
        }
    }

    public String getProblemStatus(int problemNumber) {
        int progressIndex = (problemNumber - 1) * 3;
        return values.get(progressIndex + 1);
    }

    public void printStatusList() {
        for (int i = 0; i < values.size(); i += 3) {
            System.out.println(values.get(i) + " | " + values.get(i + 1) + " | " + values.get(i + 2));
        }
    }

    public ArrayList<String> getProgressValues() {
        return values;
    }
}
