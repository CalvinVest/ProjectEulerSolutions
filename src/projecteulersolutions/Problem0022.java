package projecteulersolutions;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/*
The goal of problem 22 is to find the sum of all name scores for the given file

The file provided is a list of over 5,000 names.
The alphanumeric sum of a number is the sum of its characters a=1 values.
    For example, Colin's alphanumeric sum  is c=3 + o=15 + l=12 + i=9 + n=14 = 53
The score of a name is its alphanumeric sum times its place in the alphabetized list
    For example, Colin is 938th in the list, so Colin's score is 53 * 938

Plan:
Import list
Alphabetize list
Output list for debug
Implement alphanumeric sum function
Implement name score function
Sum name scores
Print solution
 */
public class Problem0022 extends Problem {

    @Override
    public void printSolution() {
        File namesFile = new File(Problem.FILEPATH + "problem0022.txt");
        
        List<String> names = readNamesFromFile(namesFile);
        
    }

    private List<String> readNamesFromFile(File file) {
        ArrayList<String> names = new ArrayList<>();
        try {
            Scanner fileIn = new Scanner(file);
            fileIn.useDelimiter(",");
            while(fileIn.hasNext()) {
                names.add(fileIn.next());
            }
        } catch(FileNotFoundException fnfe) {
            System.out.println("Exception encountered: " + file.toString() + " does not exist.");
        }
        System.out.println("All names loaded from file");
        return names;
    }
}
