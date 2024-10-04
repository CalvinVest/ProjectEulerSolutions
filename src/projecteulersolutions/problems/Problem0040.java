package projecteulersolutions.problems;

import java.util.ArrayList;

/*
The goal of problem 40 is to find the product of the given indeces of the array
of integers found by concatenating the positive integers as an irrational decimal
fraction as follows:

d = 0.123456789101112131415...
d(n) is the nth digit of the fractional part of the irrational decimal d
find d(1) * d(10) * d(100) * d(1000) * d(10000) * d(100000) * d(1000000)
 */
@SuppressWarnings("unused")
public class Problem0040 extends Problem {

    @Override
    public boolean isSolved() {
        return true;
    }

    @Override
    public void printSolution() {
        ArrayList<Character> charList = new ArrayList<>();
        for (int i = 1; charList.size() < 1000000; i++) {
            String numString = String.valueOf(i);
            int length = numString.length();
            for (int j = 0; j < length; j++) {
                charList.add(numString.charAt(j));
            }
        }
        int[] targets = {1, 10, 100, 1000, 10000, 100000, 1000000};

        int product = getProductFromCharList(charList, targets);

        System.out.println("The Champernowne's Constant target digit product is " + product);
    }

    private int getProductFromCharList(ArrayList<Character> charList, int[] targets) {
        int product = 1;
        for (int i = 0; i < targets.length; i++) {
            product *= Integer.parseInt(charList.get(targets[i] - 1).toString());
        }
        return product;
    }
}
