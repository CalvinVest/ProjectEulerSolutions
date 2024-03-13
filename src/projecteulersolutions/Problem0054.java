package projecteulersolutions;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Problem0054 extends Problem {

    @Override
    public boolean isSolved() {
        return false;
    }

    @Override
    public void printSolution() {
        System.out.println("This problem has not been solved.");

        File file = new File(Problem.FILEPATH + "problem0096.txt");
        List<String> lines = readLinesFromFile(file);
    }

    private List<String> readLinesFromFile(File file) {
        ArrayList<String> lines = new ArrayList<>();
        try {
            Scanner fileIn = new Scanner(file);
            while (fileIn.hasNext()) {
                lines.add(fileIn.nextLine());
            }
        } catch (FileNotFoundException fnfe) {
            System.out.println("Exception encountered: " + file.toString() + " does not exist.");
        }
        return lines;
    }
}
