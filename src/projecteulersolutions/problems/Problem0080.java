package projecteulersolutions.problems;

import java.math.BigDecimal;
import java.math.MathContext;

@SuppressWarnings("unused")
public class Problem0080 extends Problem {

    @Override
    public boolean isSolved() {
        return true;
    }

    @Override
    public void printSolution() {
        int sum = 0;
        for (int i = 2; i < 100; i++) {
            if (!isPerfectSquare(i)) {
                String numStr = getSquareRootDecimalString(i);
                System.out.printf("Decimal part of root %d is %s\n", i, numStr);
                int currDigitSum = sumOfDigits(numStr);
                System.out.println("Root of " + i + " first hundred digit sum is " + currDigitSum);
                sum += currDigitSum;
            }

        }
        System.out.println("The total sum of root digit sums is " + sum);
    }

    private String getSquareRootDecimalString(int n) {
        BigDecimal root = new BigDecimal(n).sqrt(new MathContext(102));

        // Convert square root to string and remove decimal point
        String rootStr = root.toString().replace(".", "");

        if(rootStr.length() < 100) {
            return rootStr;
        }
        // Return the first 'precision' decimal digits
        return rootStr.substring(0, 100);
    }

    private boolean isPerfectSquare(int num) {
        if (num < 0) {
            return false; // Negative numbers are not perfect squares
        }

        int root = (int) Math.sqrt(num); // Integer square root
        int checker = root * root;
        // Check if square of root equals n
        return checker == num;
    }

    public static int sumOfDigits(String number) {
        int sum = 0;
        for (int i = 0; i < number.length(); i++) {
            char digitChar = number.charAt(i);
            if (Character.isDigit(digitChar)) {
                int digit = Character.getNumericValue(digitChar);
                sum += digit;
            }
        }
        return sum;
    }
}
