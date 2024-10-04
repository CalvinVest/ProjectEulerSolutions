package projecteulersolutions.problems;

/*
The goal of problem 4 is to find the largest palindromic
product of two three-digit numbers.

Find max(x)
Where x is y * z, 100 <= y < 1000, 100 <= z < 1000
 */
public class Problem0004 extends Problem {

    @Override
    public boolean isSolved() {
        return true;
    }

    @Override
    public void printSolution() {
        int largestPalindrome = 0;

        for (int x = 100; x < 1000; x++) {
            for (int y = 100; y < 1000; y++) {
                if (isPalindrome(x * y) && largestPalindrome < x * y) {
                    largestPalindrome = x * y;
                }
            }
        }

        System.out.println("The largest palindromic product of"
                + " two 3-digit numbers is " + largestPalindrome);
    }

    private boolean isPalindrome(int i) {
        String str = Integer.toString(i);
        // integers n and m are placeholders for the indeces
        // of two corresponding digits in the input integer i
        // for palindromic checks
        int n = 0, m = str.length() - 1;
        while (n < m) { // n is lower bound var and m is upper bound var
            if (str.charAt(n) != str.charAt(m)) // if digits are not the same
            {
                return false; // not palindromic
            }
            n++; // increase lower bound
            m--; // decrease upper bound
        }
        // if the while loop is broken because n >= m, then
        // the int i is palindromic, return true.
        return true;
    }
}
