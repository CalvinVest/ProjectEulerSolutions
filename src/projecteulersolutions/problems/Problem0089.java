package projecteulersolutions.problems;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

/*
For a number written in Roman numerals to be considered valid there are basic rules
which must be followed. Even though the rules allow some numbers to be expressed
in more than one way there is always a "best" way of writing a particular number.

For example, it would appear that there are at least six ways of writing the number
sixteen:
IIIIIIIIIIIIIIII
VIIIIIIIIIII
VVIIIIII
VVVI
XVI

However, according to the rules only XIIIIII and XVI are valid, and the last example
is considered to be the most efficient, as it uses the least number of numerals.

The 11K text file, problem0089.txt, contains one thousand numbers written in valid,
but not necessarily minimal, Roman numerals. Find the number of characters saved
by writing each of these in their minimal form.
 */
@SuppressWarnings("unused")
public class Problem0089 extends Problem {

    @Override
    public boolean isSolved() {
        return true;
    }

    @Override
    public void printSolution() {
        File file = new File(Problem.FILEPATH + "problem0089.txt");

        try {
            Scanner fileIn = new Scanner(file);
            int sumSaved = 0;

            while (fileIn.hasNext()) {
                String currRoman = fileIn.next();

                int num = romanToNum(currRoman);
                String convertedRoman = numToRoman(num);

                /*
                diffLength is the difference in length between the current roman
                numeral from the file and its converted counterpart. In many
                cases, these may be the same length, but if there are characters
                saved in the conversion, add the count of those characters to sum.
                 */
                int diffLength = currRoman.length() - convertedRoman.length();
                if (diffLength > 0) {
                    sumSaved += diffLength;
                }
            }
            // close scanner
            fileIn.close();

            // solution message
            System.out.println("The total number of characters saved by converting all numerals is " + sumSaved);
        } catch (FileNotFoundException fnfe) {
            System.out.println("Failed: File " + file.getName() + " not found.");
        }
    }

    /*
    romanToNum(String) takes a roman numeral in the given string and returns the
    integer value of that roman numeral.
    
    There is no sanitization or input checking on this method.
    This method is private so this issue is low priority, but this should be
    resolved in the future, especially if roman numeral conversion is added to the
    EulerMath library.
     */
    private int romanToNum(String str) {
        // HashMap contains corresponding roman numeral and integer pairs
        HashMap<Character, Integer> romanValues = new HashMap<>();
        romanValues.put('I', 1);
        romanValues.put('V', 5);
        romanValues.put('X', 10);
        romanValues.put('L', 50);
        romanValues.put('C', 100);
        romanValues.put('D', 500);
        romanValues.put('M', 1000);

        // result stores the running value of the roman numeral
        int result = 0;
        // lastVal is the last interpreted numeral, in case roman numeral prefix
        // subtraction is used.
        int lastVal = 0;

        // for every character of the roman numeral, evaluate piecewise, utilizing
        // lastVal if prefix subtraction is being used.
        for (int i = str.length() - 1; i >= 0; i--) {
            // gets the int value of the current character of the roman numeral
            int currVal = romanValues.get(str.charAt(i));

            // if the current value is lesser than the last value, the character
            // is being subtracted instead of added.
            if (currVal < lastVal) {
                result -= currVal;
            } else {
                result += currVal;
            }
            // update last value with current value
            lastVal = currVal;
        }
        // return the integer value of the given roman numeral string.
        return result;
    }

    /*
    numToRoman(int) returns the converted Roman numeral of the given int value as
    a String. This Roman numeral is in its most efficient, least-character form.
    
    There is no sanitization or input checking on this method.
    This method is private so this issue is low priority, but this should be
    resolved in the future, especially if roman numeral conversion is added to the
    EulerMath library.
     */
    private String numToRoman(int num) {
        // array of integer values
        int[] vals = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        // corresponding array of Roman numeral pieces
        String[] romanStrs = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
        // result string, starting as empty string
        String resultStr = "";
        
        // reference int for both vals and romanStrs arrays to find corresponding values
        int index = 0;
        
        /*
        The upcoming while loop does not check if index is within the bounds of
        the vals and romanStrs arrays. This is because the loop checks while the given
        num is greater than zero, and since the last index of vals will decrement
        num by 1 the num value will reach zero at most when the max index is reached.
        There will never be a case for any positive integer where this loop will
        access an array out of bounds.
        */
        while (num > 0) {
            /*
            countOfNumeral is the number of times the given numeral can be used
            for example, if the current index is 0, romans[index] = "M" and vals[index] = 1000.
            if the given num is 2000, countOfNumeral = 2000 / 1000 = 2 so "M"
            is added to the result String twice.
             */
            int countOfNumeral = num / vals[index];
            // leaves the remainder from the countOfNumeral division
            num %= vals[index];

            // for however many times the given numeral can be added, append it
            // to the result String
            for (int i = 0; i < countOfNumeral; i++) {
                resultStr += romanStrs[index];
            }
            // increment index
            index++;
        }

        return resultStr;
    }
}
