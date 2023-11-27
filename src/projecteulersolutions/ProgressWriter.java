package projecteulersolutions;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class ProgressWriter {

    public static final int PROBLEM_COUNT = 855;
    private final ArrayList<String> values;
    private static final String FILEPATH = Problem.FILEPATH + "progress.txt";
    private final File file;

    public final String[] TYPE = {
        /*0*/"COMPLETE",
        /*1*/ "IN_PROGRESS",
        /*2*/ "INCOMPLETE"};

    public ProgressWriter() {
        file = new File(FILEPATH);
        values = new ProgressFileReader().getProgress();
    }

    /*
    setProblemStatus sets the given problemNumber to the given
    status type.
     */
    public void setProblemStatus(int problemNumber, int type) {
        int index = (problemNumber - 1) * 2;
        values.set(index, Integer.toString(problemNumber));
        values.set(index + 1, TYPE[type]);

        saveProgressToFile(values);
    }
    
    /*
    saveProgressToFile takes the progress data for all problems and saves the
    data to progress.txt
    */
    public void saveProgressToFile(ArrayList<String> values) {
        try ( FileWriter fw = new FileWriter(file)) {
            for (int i = 0; i < values.size(); i += 2) {
                fw.write(values.get(i) + " " + values.get(i + 1) + "\n");
            }
            fw.close();
        } catch (IOException ioe) {
            System.out.println("Failed: Could not save to file.\n" + ioe.getMessage());
        }
        System.out.println("Saved to file: " + file.getName());
        new ReadmeGenerator().generateReadme();
    }

    /*
    regenerateValues regenerates all statuses by first setting all
    non-broken problems to incomplete, then generating in progress and
    complete problems by checking for the existence of the problem file
    and state of the Problem0000.isSolved method respectively.
     */
    public void regenerateValues() {
        System.out.println("-------------------------------------");

        // clear values list and reset all statuses to incomplete
        for (int i = 1; i <= PROBLEM_COUNT; i++) {
            values.set(2 * (i - 1), Integer.toString(i));
        }
        // all files in the project folder
        String[] pathnames = Problem.getProblemFiles();
        ArrayList<String> problems = new ArrayList<>();
        // set all of the completed or in progress files based on existence of files
        for (String pathname : pathnames) {
            if (pathname.matches("^Problem\\d{4}.java$")) {
                problems.add(pathname.substring(7, 11));
            }
        }

        for (int i = 0; i < problems.size(); i++) {
            var problem = problems.get(i);
            int problemNumber = Integer.parseInt(problem);
            int index = 2 * (problemNumber - 1);
            boolean isSolved = (boolean) new JavaClassLoader().invokeClassMethod("projecteulersolutions.Problem" + problem, "isSolved");
            int type = isSolved ? 0 : 1;

            System.out.println(problem + (isSolved ? " is solved." : " is in progress."));
            if (isSolved) {
                System.out.println(problem + " is solved.");
            } else {
                System.out.println(problem + " is in progress.");
            }
            values.set(index + 1, TYPE[type]);
        }
        System.out.println("-------------------------------------");

        saveProgressToFile(values);
    }

    /*
    getValues returns the arraylist of problem numbers and their statuses
    as the arraylist values.
     */
    public ArrayList<String> getValues() {
        return values;
    }

    /*
    getProblemStatus returns the status of a given problem number.
     */
    public String getProblemStatus(int problemNumber) {
        int progressIndex = (problemNumber - 1) * 2;
        return values.get(progressIndex + 1);
    }

    /*
    getProblemTypeNum returns the status of a given problem number as
    its index in TYPE[].
     */
    public int getProblemTypeNum(int problemNumber) {
        return switch (getProblemStatus(problemNumber)) {
            case "COMPLETE" ->
                0;
            case "IN_PROGRESS" ->
                1;
            default ->
                2;
        };
    }

    /*
    printValues prints the problem number and status of all available
    problem files.
     */
    public void printValues() {
        for (int i = 0; i < values.size(); i += 2) {
            System.out.println(values.get(i) + " | " + values.get(i + 1));
        }
    }
}
