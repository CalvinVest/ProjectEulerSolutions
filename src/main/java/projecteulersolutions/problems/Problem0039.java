package projecteulersolutions.problems;

import projecteulersolutions.EulerMath;

/*
If p is the perimeter of a right angle triangle with integral length
sides, {a, b, c}, there are exactly three solutions for p = 120.
{20, 48, 52}, {24, 45, 51}, {30, 40, 50}
Find which value for p <= 1000 the number of solutions is maximised.
 */
@SuppressWarnings("unused")
public class Problem0039 extends Problem {

    @Override
    public boolean isSolved() {
        return true;
    }

    @Override
    public void printSolution() {
        // max p is largest allowable perimeter
        int maxP = 1000;

        // array to hold count of instances of pythagorean triple sum
        // where the index is the sum of the pythagorean triple
        // + 1 offset to account for a, b <= maxP 
        // takes advantage of the fact that array values are default 0.
        int[] perimeterCount = new int[maxP + 1];

        // a and b can be up to max p
        for (int a = 1; a <= maxP; a++) {
            for (int b = a; a + b <= maxP; b++) {
                // c^2 = a^2 + b^2 
                int c = (int) Math.sqrt(a * a + b * b);
                if (EulerMath.isPythagorean(a, b, c) && a + b + c <= maxP) {
                    int p = a + b + c;
                    perimeterCount[p]++;
                }
            }
        }
        // since perimeter is index of count array get the max index
        // to find the perimeter with the highest count
        int maxFreqP = getMaxIndex(perimeterCount);

        System.out.println("Perimeter with most solutions is " + maxFreqP);
    }

    /*
    getMaxIndex returns the index of the max value of given int array
     */
    private int getMaxIndex(int[] nums) {
        // init max and max index to first num
        int max = nums[0];
        int maxIndex = 0;

        // for each of the rest of the nums search for max
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > max) {
                max = nums[i];
                maxIndex = i;
            }
        }

        // this method is concerned in returning the INDEX of the max
        // array value. This returns the index, not the actual max value
        // itself.
        return maxIndex;
    }
}
