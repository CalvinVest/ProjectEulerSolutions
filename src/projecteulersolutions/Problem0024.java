package projecteulersolutions;

/*
The goal of problem 24 is to find the millionth lexicographic permutation of
the digits 0, 1, 2, 3, 4, 5, 6, 7, 8, and 9.

p(0) = 0123456789
p(1) = 0123456798
p(2) = 0123456879

find p(999999) (the millionth permutation)
 */
public class Problem0024 extends Problem {

    @Override
    public boolean isSolved() {
        return false;
    }

    @Override
    public void printSolution() {
        try {
            String solution = getNthPermutationRecursive("0123456789".toCharArray(), 999999);
            System.out.println("The millionth permutation of 0123456789 is: " + solution);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private String getNthPermutationRecursive(char[] str, int n) {
        if (str.length == 1) {
            // the last remaining char is returned to break recursion
            return Character.toString(str[0]);
        }
        int factorial = getFactorial(str.length - 1);
        // the number of times the current factorial divides into the given n
        int dividend = n / factorial;
        // the remainder from the above division
        int remainder = n % factorial;
        // char at the index for how many times the current factorial goes into n
        char currentChar = str[dividend];

        // adds all characters that are not the current char to the new char array
        char[] newStr = new char[str.length - 1];
        for (int i = 0; i < str.length; i++) {
            if (i < dividend) {
                // have not yet encountered the current char index
                newStr[i] = str[i];
            } else if (i > dividend) {
                // have encountered current char index, adjust index offset
                newStr[i - 1] = str[i];
            }
        }

        // recursively adds all characters to the return string
        // in correct order for given permutation count
        return currentChar + getNthPermutationRecursive(newStr, remainder);
    }

    private int getFactorial(int n) {
        if (n <= 1) {
            return 1;
        }
        return n * getFactorial(n - 1);
    }
}
