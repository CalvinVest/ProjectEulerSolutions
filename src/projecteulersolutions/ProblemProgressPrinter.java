package projecteulersolutions;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class ProblemProgressPrinter {
    private String filepath = Problem.FILEPATH + "problems.txt";
    private ArrayList<String> progress;
    
    public ProblemProgressPrinter() {
        File file = new File(filepath);
        try {
            progress = readProgressFromFile(file);
            
            for(int i = 0; i < progress.size(); i+= 3) {
                System.out.println(progress.get(i) + " | " + progress.get(i+1) + " | " + progress.get(i+2));
            } 
            
            
        } catch(FileNotFoundException fnfe) {
            System.out.println("Failure: Progress file problems.txt does not exist.");
        }
    }
    
    private ArrayList<String> readProgressFromFile(File file) throws FileNotFoundException {
        Scanner fileIn = new Scanner(file);
        return readProgressValues(fileIn);
    }
    
    private ArrayList<String> readProgressValues(Scanner fileIn) {
        ArrayList<String> values = new ArrayList<>();
        
        while(fileIn.hasNext()) {
            values.add(fileIn.next());
        }
        
        return values;
    }
}
