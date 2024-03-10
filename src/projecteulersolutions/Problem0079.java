package projecteulersolutions;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Problem0079 extends Problem {
    
    @Override
    public boolean isSolved() {
        return false;
    }
    
    @Override
    public void printSolution() {
        System.out.println("This problem has not been solved.");
        File file = new File(Problem.FILEPATH + "problem0079.txt");
        
    }
    
    private List<Integer> readFile(File file) {
        List<Integer> vals = new ArrayList<>();
        try {
            Scanner fileIn = new Scanner(file);
            while (fileIn.hasNext()) {
                vals.add(fileIn.nextInt());
            }
        } catch (FileNotFoundException fnfe) {
            System.out.println("Exception encountered: " + file.toString() + " does not exist.");
        }
        return vals;
    }
}
