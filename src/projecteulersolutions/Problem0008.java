package projecteulersolutions;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Problem0008 extends Problem {

    @Override
    public boolean isSolved() {
        return true;
    }

    @Override
    public void printSolution() {
        String str = loadStringFromFile();
        long largestProduct = findLargestSubstringProduct(str);
        System.out.println("The largest product substring is " + largestProduct);
    }
    
    private String loadStringFromFile() {
        String str = "";
        File file = new File(Problem.FILEPATH + "problem0008.txt");
        try {
            Scanner fileIn = new Scanner(file);
            str = fileIn.next();
        } catch (FileNotFoundException ex) {
            System.out.println("Failed: " + file.getName() + " does not exist.");
        }
        return str;
    }

    private long findLargestSubstringProduct(String str) {
        long maxSubstrProd = 0;
        for (String substr : getAdjacentDigitArrays(str)) {
            if (calcSubstringProduct(substr) > maxSubstrProd) {
                maxSubstrProd = calcSubstringProduct(substr);
            }
        }
        return maxSubstrProd;
    }

    private String[] getAdjacentDigitArrays(String str) {
        int subStringLength = 13;
        int numAdjacentStrings = str.length() - subStringLength;
        String[] subStrings = new String[numAdjacentStrings];
        for (int i = 0; i < numAdjacentStrings; i++) {
            subStrings[i] = str.substring(i, i + subStringLength);
        }
        return subStrings;
    }

    private long calcSubstringProduct(String str) {
        long product = 1l;
        for (char c : str.toCharArray()) {
            product *= Integer.parseInt(Character.toString(c));
        }
        return product;
    }
}
