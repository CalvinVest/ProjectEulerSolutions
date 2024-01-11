package projecteulersolutions;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class ReadmeGenerator {

    private static final String READMEHEADERPATH = System.getProperty("user.dir") + "\\README_HEADER.txt";
    private static final String READMEPATH = System.getProperty("user.dir") + "\\README.md";

    private final File headerFile, readmeFile;

    public ReadmeGenerator() {
        headerFile = new File(READMEHEADERPATH);
        readmeFile = new File(READMEPATH);
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
