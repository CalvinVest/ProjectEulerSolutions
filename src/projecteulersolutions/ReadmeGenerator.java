package projecteulersolutions;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class ReadmeGenerator {

    private static final String READMEHEADERPATH = System.getProperty("user.dir") + "\\README_HEADER.txt";
    private static final String READMEPATH = System.getProperty("user.dir") + "\\README.md";

    private final File readmeHeaderFile, readmeFile;
    private Scanner headerFileIn;
    private FileWriter readmeFileOut;

    public ReadmeGenerator() {
        readmeHeaderFile = new File(READMEHEADERPATH);
        readmeFile = new File(READMEPATH);
        try {
            headerFileIn = new Scanner(readmeHeaderFile);
            readmeFileOut = new FileWriter(readmeFile);
        } catch (FileNotFoundException ex) {
            System.out.println("Failed: " + readmeHeaderFile.getName() + " does not exist.");
        } catch (IOException ioe) {
            System.out.println("Failed: IOException - " + ioe.getMessage());
        }
    }

    public void generateReadme() {
        try {
            headerFileIn = new Scanner(readmeHeaderFile);
            readmeFileOut = new FileWriter(readmeFile);
            printHeaderToReadme();
            printProgressToReadme();
            readmeFileOut.close();
            System.out.println("Saved to file: " + readmeFile.getName());
        } catch (IOException ioe) {
            System.out.println("Failed: File " + readmeFile + " does not exist.");
        }
    }

    private void printHeaderToReadme() throws IOException {
        while(headerFileIn.hasNext()) {
            readmeFileOut.write(headerFileIn.nextLine() + "\n");
        }
        readmeFileOut.write("\n");
    }
    
    private void printProgressToReadme() throws IOException {
        ProgressWriter pw = new ProgressWriter();
        ArrayList<String> progressValues = pw.getValues();
        int valuesPerRow = 10;
        
        readmeFileOut.write("<table>\n    <tr>\n        <td></td>\n");
        for(int i = 0; i < progressValues.size(); i+=2) {
            if(i != 0 && i % (2 * valuesPerRow) == 2 * (valuesPerRow - 1)) {
                readmeFileOut.write("    </tr>\n    <tr>\n");
            }
            readmeFileOut.write("        <td>" + progressValues.get(i) + " " + getEmojiString(progressValues.get(i+1)) + "</td>\n");
        }
        readmeFileOut.write("    </tr>\n</table>\n");
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
}
