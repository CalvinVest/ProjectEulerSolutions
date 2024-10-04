package projecteulersolutions;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class EulerWriter {

    private static final String README_HEADER_PATH = System.getProperty("user.dir") + "\\README_HEADER.txt";
    private static final String README_PATH = System.getProperty("user.dir") + "\\README.md";
    private static final String PROGRESS_PATH = Problem.FILEPATH + "progress.txt";

    protected static final int PROBLEM_COUNT = 855;

    private final ArrayList<String> values;
    private final File progressOutFile, headerInFile, readmeOutFile;

    public final String[] STATUS = {
        /*0*/"COMPLETE",
        /*1*/ "IN_PROGRESS",
        /*2*/ "INCOMPLETE"};

    public EulerWriter() {
        values = new EulerReader().loadProgress();
        progressOutFile = new File(PROGRESS_PATH);
        headerInFile = new File(README_HEADER_PATH);
        readmeOutFile = new File(README_PATH);
    }

    /*
    setProblemStatus sets the given problemNumber to the given status.
     */
    public void setProblemStatus(int problemNumber, int status) {
        int problemIndex = (problemNumber - 1) * 2;
        values.set(problemIndex, Integer.toString(problemNumber));
        values.set(problemIndex + 1, STATUS[status]);

        saveProgressToFile(values);
    }

    /*
    saveProgressToFile takes the progress data for all problems and saves the
    data to progress.txt
     */
    public void saveProgressToFile(ArrayList<String> values) {
        try (FileWriter writer = new FileWriter(progressOutFile)) {
            for (int i = 0; i < values.size(); i += 2) {
                writer.write(values.get(i) + " " + values.get(i + 1) + "\n");
            }
            writer.close();
        } catch (IOException ioe) {
            System.out.println("Failed: Could not save to file.\n" + ioe.getMessage());
        }
        System.out.println("Saved to file: " + progressOutFile.getName());
        generateReadme();
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
        // set all the completed or in progress files based on existence of files
        for (String pathname : pathnames) {
            if (pathname.matches("^Problem\\d{4}.java$")) {
                problems.add(pathname.substring(7, 11));
            }
        }

        for (int i = 0; i < problems.size(); i++) {
            var problem = problems.get(i);
            int problemNumber = Integer.parseInt(problem);
            // index of the given problem number
            int index = 2 * (problemNumber - 1);

            // bool flag of if problem is solved or not. Existing problems are
            // either solved or in progress. Incomplete problems do not have a
            // class file
            boolean isSolved = (boolean) new JavaClassLoader().invokeClassMethod("projecteulersolutions.Problem" + problem, "isSolved");
            int status = isSolved ? 0 : 1;
            // sets problem status
            values.set(index + 1, STATUS[status]);

            // print problem status
            System.out.println(problem + (isSolved ? " is solved." : " is in progress."));
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
    getProblemStatusNum returns the status of a given problem number as
    its index in STATUS[].
     */
    public int getProblemStatusNum(int problemNumber) {
        return switch (getProblemStatus(problemNumber)) {
            case "COMPLETE" ->
                0;
            case "IN_PROGRESS" ->
                1;
            default ->
                2;
        };
    }

    public void generateReadme() {
        try (FileWriter readmeFileOut = new FileWriter(readmeOutFile)) {
            printHeaderToReadme(readmeFileOut);
            printProgressToReadme(readmeFileOut);
            readmeFileOut.close();
            System.out.println("Saved to file: " + readmeOutFile.getName());
        } catch (IOException ioe) {
            System.out.println("Failed: File " + readmeOutFile + " does not exist.");
        }
    }

    private void printHeaderToReadme(FileWriter readmeOut) throws IOException {
        try (Scanner headerFileIn = new Scanner(headerInFile)) {
            while (headerFileIn.hasNext()) {
                readmeOut.write(headerFileIn.nextLine() + "\n");
            }
            readmeOut.write("\n");
            headerFileIn.close();
        }
    }

    private void printProgressToReadme(FileWriter readmeOut) throws IOException {
        EulerWriter writer = new EulerWriter();
        ArrayList<String> progressValues = writer.getValues();
        int[] statusCounts = new int[writer.STATUS.length];
        for (int i = 0; i < statusCounts.length; i++) {
            statusCounts[i] = Collections.frequency(progressValues, writer.STATUS[i]);
        }
        String statusCountsString = getStatusCountsString(statusCounts);
        readmeOut.write(statusCountsString);
        int valuesPerRow = 10;

        readmeOut.write("<table>\n\t<tr>\n\t\t<td></td>\n");
        for (int i = 0; i < progressValues.size(); i += 2) {
            if (i != 0 && i % (2 * valuesPerRow) == 2 * (valuesPerRow - 1)) {
                readmeOut.write("\t</tr>\n\t<tr>\n");
            }
            readmeOut.write("\t\t<td>" + progressValues.get(i) + " " + getEmojiString(progressValues.get(i + 1)) + "</td>\n");
        }
        readmeOut.write("\t</tr>\n</table>\n");
    }

    private String getStatusCountsString(int[] statusCounts) {
        return ":green_circle: Complete: " + statusCounts[0]
                + "\n:small_orange_diamond: In Progress: " + statusCounts[1]
                + "\n:heavy_multiplication_x: Incomplete: " + statusCounts[2] + "\n";
    }

    private String getEmojiString(String status) {
        return switch (status) {
            case "COMPLETE" ->
                ":green_circle:";
            case "IN_PROGRESS" ->
                ":small_orange_diamond:";
            default ->
                ":heavy_multiplication_x:";
        };
    }
}
