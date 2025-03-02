package main.java.projecteulersolutions.problems;

import main.java.projecteulersolutions.EulerUtils;

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

/*
The goal of problem 13 is to find the first ten digits of the sum
of the given one hundred 50-digit numbers.
 */
@SuppressWarnings("unused")
public class Problem0013 extends Problem {

    @Override
    public boolean isSolved() {
        return true;
    }

    @Override
    public void printSolution() {
        String[] numStrings = new String[100];

        File file = new File(EulerUtils.DATA_FILEPATH + "problem0013.txt");
        try {
            Scanner fileIn = new Scanner(file);

            for (int i = 0; i < numStrings.length; i++) {
                numStrings[i] = fileIn.next();
            }
        } catch (FileNotFoundException fnfe) {
            System.out.println("Exception: File " + file.getName() + " not found.");
        }

        long[] nums = convertStringsToTrimmedNums(numStrings);

        long sum = sumLongArray(nums);
        String trimmedSumString = Long.toString(sum).substring(0, 10);
        System.out.println("The first 10 digits of the sum of the 100 50-digit numbers is " + trimmedSumString);
    }

    private long[] convertStringsToTrimmedNums(String[] strs) {
        long[] nums = new long[100];

        for (int i = 0; i < nums.length; i++) {
            nums[i] = Long.parseLong(strs[i].substring(0, 12));
        }
        return nums;
    }

    private long sumLongArray(long[] nums) {
        long sum = 0L;
        for (long num : nums) {
            sum += num;
        }
        return sum;
    }
}
