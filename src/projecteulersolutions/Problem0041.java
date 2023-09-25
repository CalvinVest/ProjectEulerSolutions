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
        return true;
    }

    @Override
    public void printSolution() {
        int maxPandigitalPrime = 0;
        /* 9-digit pandigitals have a digital sum of
        1+2+3+4+5+6+7+8+9=45. A digital sum divisible by 3 indicates
        that the number is divisible by three
        
        This means the upper bound is now max 8-digit pandigital.
        1+2+3+4+5+6+7+8=36. A digital sum divisible by 3 indicates
        that the number is divisible by three
        
        This means the upper bound is now max 7-digit pandigital.
        
        It can be readily assumed that there exists a 7-digit pandigital
        prime, so lower bound can be min 7-digit pandigital.
         */
        for (int i = 7654321; i >= 1234567; i -= 2) {
            if (isPandigital(i) && isPrime(i)) {
                maxPandigitalPrime = i;
                break;
            }
        }
        System.out.println("The largest pandigital prime is " + maxPandigitalPrime);
    }

    private boolean isPandigital(int n) {
        String str = "" + n;
        ArrayList<Character> charList = new ArrayList<>();
        for (char c : str.toCharArray()) {
            charList.add(c);
        }
        Collections.sort(charList);
        String newStr = "";
        String matchStr = "";
        for (int i = 0; i < charList.size(); i++) {
            newStr += charList.get(i).toString();
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
