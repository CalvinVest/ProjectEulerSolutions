package projecteulersolutions;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class ReadmeGenerator {

    public static final String INFILEPATH = System.getProperty("user.dir") + "\\README_BODY.txt";
    public static final String OUTFILEPATH = System.getProperty("user.dir") + "\\README.md";

    private final File inFile, outFile;
    private Scanner fileIn;
    private FileWriter fileOut;

    public ReadmeGenerator() {
        inFile = new File(INFILEPATH);
        outFile = new File(OUTFILEPATH);
        try {
            fileIn = new Scanner(inFile);
            fileOut = new FileWriter(outFile);
        } catch (FileNotFoundException ex) {
            System.out.println("Failure: File " + inFile.getName() + " does not exist.");
        } catch (IOException ioe) {
            System.out.println("Failure: IOException - " + ioe.getMessage());
        }

        System.out.println("Readme Generator initiated.");
    }

    public void generateReadme() {
        try {
            fileIn = new Scanner(inFile);
            fileOut = new FileWriter(outFile);
            printReadmeBodyToReadme();
            printProgressTableToReadme();
            fileOut.close();
        } catch (IOException ioe) {
            System.out.println("Failure: File " + outFile + " does not exist.");
        }
    }

    private void printReadmeBodyToReadme() throws FileNotFoundException, IOException {
        while(fileIn.hasNext()) {
            fileOut.write(fileIn.nextLine() + "\n");
        }
        fileOut.write("\n");
    }
    
    private void printProgressTableToReadme() throws IOException {
        ProgressWriter pw = new ProgressWriter();
        ArrayList<String> progressValues = pw.getProgressValues();
        int valuesPerRow = 10;
        
        fileOut.write("<table>\n    <tr>\n        <td></td>\n");
        for(int i = 0; i < progressValues.size(); i+=3) {
            if(i != 0 && i % (3 * valuesPerRow) == 3 * (valuesPerRow - 1)) {
                fileOut.write("    </tr>\n    <tr>\n");
            }
            fileOut.write("        <td>" + progressValues.get(i) + " " + progressValues.get(i+2) + "</td>\n");
        }
        fileOut.write("    </tr>\n</table>\n");
    }
}
