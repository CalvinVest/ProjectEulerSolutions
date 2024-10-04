package projecteulersolutions.problems;

import java.util.ArrayList;

/*
The goal of problem 23 is to find the sum of all positive integers which cannot
be expressed as the sum of two abundant numbers.

A perfect number is one where the sum of its factors is equal to the number.
A deficient number is one where the sum of factors is less than the number.
An abundant number is one where the sum of factors is more than the number.

By mathematical analysis, all numbers > 28123 can be written as the sum of two
abundant numbers.
 */
@SuppressWarnings("unused")
public class Problem0023 extends Problem {

    @Override
    public boolean isSolved() {
        return true;
    }

    @Override
    public void printSolution() {
        System.out.println("The sum of all non-abundant sums is " + abundantSum(28123));
    }

    private int abundantSum(int limit) {
        int[] array = new int[limit + 1];

        ArrayList<Integer> list = abundantList(limit);

        for (int i = 0; i < list.size(); i++) {
            for (int j = i; j < list.size(); j++) {
                int sum = list.get(i) + list.get(j);
                if (sum <= limit) {
                    array[sum] = sum;
                }
            }
        }

        int sum = 0;
        for (int i = 1; i < limit; i++) {
            if (array[i] != i) {
                sum += i;
            }
        }
        return sum;
    }

    private ArrayList<Integer> abundantList(int limit) {
        ArrayList<Integer> list = new ArrayList<>();

        for (int i = 12; i <= limit; i++) {
            if (isAbundant(i)) {
                list.add(i);
            }
        }
        return list;
    }

    private boolean isAbundant(int n) {
        int sqrt = (int) Math.sqrt(n);
        var list = new ArrayList<Integer>();
        int sum = 0;

        list.add(1);
        for (int i = 2; i <= sqrt; i++) {
            if (n % i == 0) {
                list.add(i);
                list.add(n / i);
            }
        }
        list = removeDuplicates(list);

        for (int i : list) {
            sum += i;
        }
        return sum > n;
    }

    private ArrayList<Integer> removeDuplicates(ArrayList<Integer> list) {
        ArrayList<Integer> newList = new ArrayList<>();

        for (int i : list) {
            if (!newList.contains(i)) {
                newList.add(i);
            }
        }
        return newList;
    }
}
