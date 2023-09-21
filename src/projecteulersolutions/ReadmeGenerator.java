package projecteulersolutions;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class ReadmeGenerator {

    private static final String READMEHEADERPATH = System.getProperty("user.dir") + "\\README_HEADER.txt";
    private static final String READMEPATH = System.getProperty("user.dir") + "\\README.md";

    private final File headerFile, readmeFile;
    private Scanner headerIn;
    private FileWriter readmeOut;

    public ReadmeGenerator() {
        headerFile = new File(READMEHEADERPATH);
        readmeFile = new File(READMEPATH);
        try {
            headerIn = new Scanner(headerFile);
            readmeOut = new FileWriter(readmeFile);
        } catch (FileNotFoundException ex) {
            System.out.println("Failed: " + headerFile.getName() + " does not exist.");
        } catch (IOException ioe) {
            System.out.println("Failed: IOException - " + ioe.getMessage());
        }
    }

    public void generateReadme() {
        try {
            headerIn = new Scanner(headerFile);
            readmeOut = new FileWriter(readmeFile);
            printHeaderToReadme();
            printProgressToReadme();
            headerIn.close();
            readmeOut.close();
            System.out.println("Saved to file: " + readmeFile.getName());
        } catch (IOException ioe) {
            System.out.println("Failed: File " + readmeFile + " does not exist.");
        }
    }

    private void printHeaderToReadme() throws IOException {
        while (headerIn.hasNext()) {
            readmeOut.write(headerIn.nextLine() + "\n");
        }
        readmeOut.write("\n");
    }

    private void printProgressToReadme() throws IOException {
        ProgressWriter pw = new ProgressWriter();
        ArrayList<String> progressValues = pw.getValues();
        int[] typeCounts = new int[pw.TYPE.length];
        for (int i = 0; i < typeCounts.length; i++) {
            typeCounts[i] = Collections.frequency(progressValues, pw.TYPE[i]);
        }
        printProgressIndexToReadme(typeCounts);
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

    private void printProgressIndexToReadme(int[] typeCounts) throws IOException {
        readmeOut.write("<p>"
                + ":green_circle: Complete: " + typeCounts[0] + "<br>\n"
                + ":orange_circle: In Progress: " + typeCounts[1] + "<br>\n"
                + ":red_circle: Broken: " + typeCounts[2] + "<br>\n"
                + ":black_circle: Incomplete: " + typeCounts[3] + "</p>\n");
    }

    private String getEmojiString(String type) {
        return switch (type) {
            case "COMPLETE" ->
                ":green_circle:";
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
}
