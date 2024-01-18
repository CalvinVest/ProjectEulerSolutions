package projecteulersolutions;

import java.math.BigInteger;

/*
The 5-digit number, 16807 = 7^5, is also a fifth power. Similarly, the 9-digit
number, 134217728 = 8^9, is a ninth power.

How many n-digit positive integers exist which are also an nth power?
*/
public class Problem0063 extends Problem {

    @Override
    public boolean isSolved() {
        return false;
    }

    @Override
    public void printSolution() {
        System.out.println("This problem has not yet been solved.");
        int powerfulDigitCount = getPowerfulDigitCount(100);
        
        System.out.println("The count of powerful digit numbers is " + powerfulDigitCount);
    }
    
    private int getPowerfulDigitCount(int arbitraryCeiling) {
        int count = 0;
        for(int i = 1; i < 10; i++) { // a base >= 10 would result in more digits than the exponent value
            for(int j = 1; j <= arbitraryCeiling; j++) {
                BigInteger bigIntPow = new BigInteger(Integer.toString(i)).pow(j);
                String powString = bigIntPow.toString();
                if(powString.length() == j) {
                    System.out.println(powString + " is " + i + "^" + j + " and is " + powString.length() + " digits.");
                    count++;
                }
            }
            System.out.println(i);
        }
        return count;
    }
}
