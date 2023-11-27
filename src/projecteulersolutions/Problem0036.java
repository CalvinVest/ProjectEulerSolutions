package projecteulersolutions;

/*
The decimal number, 585 = 1001001001 (binary), is palindromic in both bases.

Find the sum of all numbers, less than one million, which are palindromic in base 
10 and base 2.

(Please note that the palindromic number, in either base, may not include leading zeros.)
 */
public class Problem0036 extends Problem {

    public static final int MAX = 1000000;
    
    @Override
    public boolean isSolved() {
        return true;
    }

    @Override
    public void printSolution() {
        // holds sum of all base10/base2 palindromes
        int sum = 0;

        // for every int below the max value
        for (int i = 0; i < MAX; i++) {
            // if base 10 is palindromic and base 2 is palindromic
            if (EulerMath.isPalindrome(Integer.toString(i)) && EulerMath.isPalindrome(Integer.toBinaryString(i))) {
                sum += i;
            }
        }

        System.out.println("The sum of all integers less than one million which are palindromic in base 2 and base 10 is " + sum);
    }
}
