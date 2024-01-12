package projecteulersolutions;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class ProgressWriter {
    
    private static final String READMEHEADERPATH = System.getProperty("user.dir") + "\\README_HEADER.txt";
    private static final String READMEPATH = System.getProperty("user.dir") + "\\README.md";

    public static final int PROBLEM_COUNT = 855;
    private final ArrayList<String> values;
    private static final String FILEPATH = Problem.FILEPATH + "progress.txt";
    private final File file;
    private final File headerFile, readmeFile;

    public final String[] TYPE = {
        /*0*/"COMPLETE",
        /*1*/ "IN_PROGRESS",
        /*2*/ "INCOMPLETE"};

    public ProgressWriter() {
        file = new File(FILEPATH);
        values = new ProgressFileReader().getProgress();
        headerFile = new File(READMEHEADERPATH);
        readmeFile = new File(READMEPATH);
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
        try (FileWriter fw = new FileWriter(file)) {
            for (int i = 0; i < values.size(); i += 2) {
                fw.write(values.get(i) + " " + values.get(i + 1) + "\n");
            }
            fw.close();
        } catch (IOException ioe) {
            System.out.println("Failed: Could not save to file.\n" + ioe.getMessage());
        }
        System.out.println("Saved to file: " + file.getName());
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
        // set all of the completed or in progress files based on existence of files
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
            int type = isSolved ? 0 : 1;
            // sets problem type
            values.set(index + 1, TYPE[type]);

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
    
    public void generateReadme() {
        try ( FileWriter readmeOut = new FileWriter(readmeFile)) {
            printHeaderToReadme(readmeOut);
            printProgressToReadme(readmeOut);
            readmeOut.close();
            System.out.println("Saved to file: " + readmeFile.getName());
        } catch (IOException ioe) {
            System.out.println("Failed: File " + readmeFile + " does not exist.");
        }
    }
    
    private void printHeaderToReadme(FileWriter readmeOut) throws IOException {
        try ( Scanner headerIn = new Scanner(headerFile)) {
            while (headerIn.hasNext()) {
                readmeOut.write(headerIn.nextLine() + "\n");
            }
            readmeOut.write("\n");
            headerIn.close();
        }
    }

    private void printProgressToReadme(FileWriter readmeOut) throws IOException {
        ProgressWriter pw = new ProgressWriter();
        ArrayList<String> progressValues = pw.getValues();
        int[] typeCounts = new int[pw.TYPE.length];
        for (int i = 0; i < typeCounts.length; i++) {
            typeCounts[i] = Collections.frequency(progressValues, pw.TYPE[i]);
        }
        String progressIndex = getProgressIndex(typeCounts);
        readmeOut.write(progressIndex);
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
    
    private String getProgressIndex(int[] typeCounts) {
        return "<p>"
                + ":green_circle: Complete: " + typeCounts[0] + "<br>\n"
                + ":orange_circle: In Progress: " + typeCounts[1] + "<br>\n"
                + ":black_circle: Incomplete: " + typeCounts[2] + "</p>\n";
    }

    private String getEmojiString(String type) {
        return switch (type) {
            case "COMPLETE" ->
                ":green_circle:";
            case "IN_PROGRESS" ->
                ":orange_circle:";
            default ->
                ":black_circle:";
        };
    }
}
