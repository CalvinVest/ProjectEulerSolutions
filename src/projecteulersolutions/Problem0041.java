package projecteulersolutions;

import java.util.ArrayList;
import java.util.Collections;

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
        String str = "" + n;
        ArrayList<Character> digits = new ArrayList<>();
        for (char c : str.toCharArray()) {
            digits.add(c);
        }
        Collections.sort(digits);
        String newStr = "";
        for (int i = 0; i < digits.size(); i++) {
            newStr += digits.get(i).toString();
        }
        String matchStr = "";
        for (int i = 0; i < digits.size(); i++) {
            matchStr += i + 1;
        }
        return newStr.matches(matchStr);
    }

    private boolean isPrime(int n) {
        for (int i = 2; i <= n / 2; i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }
}
