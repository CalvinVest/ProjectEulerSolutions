package projecteulersolutions;

/*
The decimal number, 585 = 1001001001 (binary), is palindromic in both bases.

Find the sum of all numbers, less than one million, which are palindromic in base 
10 and base 2.

(Please note that the palindromic number, in either base, may not include leading zeros.)
 */
public class Problem0036 extends Problem {

    @Override
    public boolean isSolved() {
        return false;
    }

    @Override
    public void printSolution() {
        long sum = 0l;

        for (int i = 0; i < 1E6; i++) {
            String binStr = Integer.toBinaryString(i);

            if (EulerMath.isPalindrome(Integer.toString(i)) && EulerMath.isPalindrome(binStr)) {
                sum += i;
                System.out.println(i + " (" + binStr + ") is palindromic in base 2 and base 10.");
            }
        }

        System.out.println("The sum of all integers less than one million which are palindromic in base 2 and base 10 is " + sum);
    }
}
