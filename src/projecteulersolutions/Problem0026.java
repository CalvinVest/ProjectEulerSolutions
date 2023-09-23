package projecteulersolutions;

import java.util.HashMap;
import java.util.Map;

/*
The goal of problem 26 is to find the number < 1000 for which
1/d contains the longest recurring cycle in its decimal fraction
part.

A unit fraction contains 1 in the numerator. The decimal representation
of the unit fractions with denominators 2 to 10 are:
1/2 = 0.5
1/3 = 0.(3)
1/4 = 0.25
1/5 = 0.2
1/6 = 0.1(6)
1/7 = 0.(142857)
1/8 = 0.125
1/9 = 0.(1)
1/10 = 0.1

1/6 has a 1-digit recurring cycle, the repeating 6.
1/7 has a 6-digit recurring cycle, the repeating 142857.
 */
public class Problem0026 extends Problem {

    @Override
    public boolean isSolved() {
        return false;
    }

    @Override
    public void printSolution() {
        int maxLength = 0;
        int num = 0;

        for (int i = 2; i < 1000; i++) {
            int length = getCycleLength(i);
            if (length > maxLength) {
                maxLength = length;
                num = i;
            }
        }

        System.out.println("The number less than 1000 with the longest recurring cycle is " + num);
    }

    private static int getCycleLength(int n) {

        Map<Integer, Integer> digitLength = new HashMap<>();
        int digit = 1; // holds value of next digit
        int length = 0; // length of recurring cycle
        while (!digitLength.containsKey(digit)) {
            digitLength.put(digit, length);
            digit = digit * 10 % n;
            length++;
        }
        //stateToIter.values().forEach(key -> System.out.println(key));
        return length - digitLength.get(digit);
    }
}
