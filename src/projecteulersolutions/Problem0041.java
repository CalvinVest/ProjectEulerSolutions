package projecteulersolutions;

import java.util.Arrays;

/*
The goal of problem 41 is to find the largest n-digit pandigital prime
that exists. An n-digit pandigital number makes use of all the digits
1 to n exactly once.
For example, 2143 is a 4-digit pandigital number.
 */
public class Problem0041 extends Problem {

    @Override
    public boolean isSolved() {
        return false;
    }

    @Override
    public void printSolution() {
        int largest = 0;
        for (int i = 2; i < 10000000; i++) {
            if (isPandigital(i) && isPrime(i)) {
                largest = i;
            }
        }
        System.out.println("The largest pandigital prime is " + largest);
    }

    private boolean isPandigital(int n) {
        int length = getLengthOfInt(n);
        char[] digits = new char[length];
        int temp = n;
        for (int i = length - 1; i >= 0; i--) {
            int d = temp % 10;
            temp /= 10;
            digits[i] = (char) (d + 48);
        }
        Arrays.sort(digits);
        char[] matchArr = new char[length];
        for (int i = 0; i < digits.length; i++) {
            matchArr[i] = (char) (i + 1 + 48);
        }
        return charArraysEqual(digits, matchArr);
    }

    private boolean isPrime(int n) {
        for (int i = 2; i <= n / 2; i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }

    private int getLengthOfInt(int n) {
        int length = 0;
        long temp = 1;
        while (temp <= n) {
            length++;
            temp *= 10;
        }
        return length;
    }
    
    private boolean charArraysEqual(char[] a, char[] b) {
        if(a.length != b.length) {
            return false;
        }
        for(int i = 0; i < a.length; i++) {
            if(a[i] != b[i]) {
                return false;
            }
        }
        return true;
    }
}
