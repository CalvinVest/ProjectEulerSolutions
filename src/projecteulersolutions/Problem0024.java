package projecteulersolutions;

/*
The goal of problem 24 is to find the millionth lexicographic permutation of
the digits 0, 1, 2, 3, 4, 5, 6, 7, 8, and 9.

p(1) = 0123456789
p(2) = 0123456798
p(3) = 0123456879
 */
public class Problem0024 extends Problem {

    @Override
    public boolean isSolved() {
        return false;
    }

    @Override
    public void printSolution() {
        String solution = getNthPermutation("0123456789".toCharArray(), 1000000);
        System.out.println("The millionth permutation of 0123456789 is: " + solution);
    }

    private String getNthPermutation(char[] str, int n) {
        int length = str.length;
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < length; i++) {
            int index = n / (length - i);
            result.append(str[index]);
            // Remove the used character from the array
            for (int j = index; j < length - i - 1; j++) {
                str[j] = str[j + 1];
            }
            n %= (length - i);
        }

        return result.toString();
    }

  
}
