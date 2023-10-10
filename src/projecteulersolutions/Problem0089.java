package projecteulersolutions;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

public class Problem0089 extends Problem {

    @Override
    public boolean isSolved() {
        return false;
    }

    @Override
    public void printSolution() {
        File file = new File(Problem.FILEPATH + "problem0089.txt");
        
        try{
            Scanner fileIn = new Scanner(file);
            
            while(fileIn.hasNext()) {
                String nextInStr = fileIn.next();
            }
            
            fileIn.close();
        } catch(FileNotFoundException fnfe) {
            System.out.println("Failed: File " + file.getName() + " not found.");
        }
    }

    private int romanToNum(String str) {
        HashMap<Character, Integer> romanValues = new HashMap<>();

        romanValues.put('I', 1);
        romanValues.put('V', 5);
        romanValues.put('X', 10);
        romanValues.put('L', 50);
        romanValues.put('C', 100);
        romanValues.put('D', 500);
        romanValues.put('M', 1000);

        int result = 0;
        int lastVal = 0;

        for (int i = str.length() - 1; i >= 0; i--) {
            int currVal = romanValues.get(str.charAt(i));

            if (currVal < lastVal) {
                result -= currVal;
            } else {
                result += currVal;
            }
            lastVal = currVal;
        }
        return result;
    }

    private String numToRoman(int num) {
        int[] vals = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        String[] romans = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
        String roman = "";

        int index = 0;
        while (num > 0) {
            int divisor = num / vals[index];
            num %= vals[index];

            for (int i = 0; i < divisor; i++) {
                roman += romans[index];
            }
            index++;
        }

        return roman;
    }
}
