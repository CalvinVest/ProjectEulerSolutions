package projecteulersolutions;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class ProgressWriter {

    private static final int PROBLEM_COUNT = 855;
    private static final String FILEPATH = Problem.FILEPATH + "progress.txt";
    private File file;
    private ArrayList<String> values;

    public final String[] TYPE = {
        /*0*/"COMPLETE",
        /*1*/ "IN_PROGRESS",
        /*2*/ "BROKEN",
        /*3*/ "INCOMPLETE"};

    public ProgressWriter() {
        file = new File(FILEPATH);
        values = new ArrayList<>();
        try {
            loadProgressFromFile(file);
        } catch (FileNotFoundException fnfe) {
            System.out.println("Failed: " + file.getName() + " does not exist.");
        }
    }

    public void setProblemStatus(int problemNumber, String type) {
        int index = (problemNumber - 1) * 2;
        values.set(index, Integer.toString(problemNumber));
        values.set(index + 1, type);

        saveProgressToFile();
    }

    public void setStatusFromString(String userString, String type) {
        String[] strs = userString.split(",");
        for (String str : strs) {
            try {
                int i = Integer.parseInt(str.trim());
                setProblemStatus(i, type);
            } catch (NumberFormatException nfe) {
                System.out.println(str + " is not a valid number.");
            }
        }
    }

    private void saveProgressToFile() {
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

    private void loadProgressFromFile(File file) throws FileNotFoundException {
        Scanner fileIn = new Scanner(file);

        values.clear();
        while (fileIn.hasNext()) {
            values.add(fileIn.next());
        }
    }

    public void regenerateValues() {
        System.out.println("-------------------------------------");

        // clear values list and reset all statuses to incomplete
        for (int i = 1; i <= PROBLEM_COUNT; i++) {
            values.set(2 * (i - 1), Integer.toString(i));

            if (!isBroken(i)) {
                values.set(2 * (i - 1) + 1, TYPE[3]);
            }
        }
        // all files in the project folder
        String[] pathnames = new File(Problem.FILEPATH).list();
        ArrayList<String> problems = new ArrayList<>();
        // set all of the completed or in progress files based on existence of files
        for (String pathname : pathnames) {
            if (pathname.matches("^Problem\\d{4}.java$")) {
                problems.add(pathname.substring(7, 11));
            }
        }

        problems.forEach(problem -> {
            int problemNumber = Integer.parseInt(problem);
            int index = 2 * (problemNumber - 1);
            boolean isSolved = (boolean) new JavaClassLoader().invokeClassMethod("projecteulersolutions.Problem" + problem, "isSolved");
            int type;

            if (isSolved) {
                System.out.println(problem + " is solved.");
                type = 0;
            } else if (isBroken(problemNumber)) {
                System.out.println(problem + " is broken.");
                type = 2;
            } else {
                System.out.println(problem + " is in progress.");
                type = 1;
            }
            values.set(index + 1, TYPE[type]);
        });

        System.out.println("-------------------------------------");
        saveProgressToFile();
    }

    private boolean isBroken(int problemNumber) {
        return values.get(2 * (problemNumber - 1) + 1).trim().equalsIgnoreCase(TYPE[2]);
    }

    public ArrayList<String> getValues() {
        return values;
    }

    public String getProblemStatus(int problemNumber) {
        int progressIndex = (problemNumber - 1) * 2;
        return values.get(progressIndex + 1);
    }

    public void printValues() {
        for (int i = 0; i < values.size(); i += 2) {
            System.out.println(values.get(i) + " | " + values.get(i + 1));
        }
    }
}
