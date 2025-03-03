package projecteulersolutions.problems;

import main.java.projecteulersolutions.EulerUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

/*
The goal of problem 22 is to find the sum of all name scores for the given file

The file provided is a list of over 5,000 names.
The alphanumeric sum of a number is the sum of its characters a=1 values.
    For example, Colin's alphanumeric sum  is c=3 + o=15 + l=12 + i=9 + n=14 = 53
The score of a name is its alphanumeric sum times its place in the alphabetized list
    For example, Colin is 938th in the list, so Colin's score is 53 * 938
 */
@SuppressWarnings("unused")
public class Problem0022 extends Problem {

    @Override
    public boolean isSolved() {
        return true;
    }

    @Override
    public void printSolution() {
        File namesFile = new File(EulerUtils.DATA_FILEPATH + "problem0022.txt");
        List<String> names = readNamesFromFile(namesFile);
        List<String> trimmedNames = trimNames(names);
        Collections.sort(trimmedNames);
        List<Integer> nameScores = getNameScores(trimmedNames);

        int nameScoreSum = nameScores.stream().reduce(0, Integer::sum);

        System.out.println("The sum of the scores of all names in problem0022.txt is " + nameScoreSum);
    }

    private List<Integer> getNameScores(List<String> names) {
        List<Integer> nameScores = new ArrayList<>();
        for (int i = 0; i < names.size(); i++) {
            nameScores.add(getAlphaSum(names.get(i)) * (i + 1));
        }
        return nameScores;
    }

    private int getAlphaSum(String name) {
        int sum = 0;
        for (int i = 0; i < name.length(); i++) {
            sum += name.charAt(i) - 64;
        }
        return sum;
    }

    private List<String> trimNames(List<String> names) {
        List<String> newNames = new ArrayList<>();

        names.forEach(name -> newNames.add(name.substring(1, name.length() - 1)));

        return newNames;
    }

    private List<String> readNamesFromFile(File file) {
        ArrayList<String> names = new ArrayList<>();
        try {
            Scanner fileIn = new Scanner(file);
            fileIn.useDelimiter(",");
            while (fileIn.hasNext()) {
                names.add(fileIn.next());
            }
        } catch (FileNotFoundException fnfe) {
            System.out.println("Exception encountered: " + file + " does not exist.");
        }
        return names;
    }
}
