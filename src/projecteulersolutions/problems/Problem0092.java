package projecteulersolutions.problems;

/*
The goal of problem 92 is to find the number of values below ten million whose
square digit chain arrives at 89.

Square digit chains are formed by adding the squares of the digits of a value n
Where s is the square digit chain for n = 44
s(n) = s[i] = 44, s[i+1] = 4^2 + 4^2 = 32, s[i+2] = 3^2 + 2^2 = 13...s[x] = 1
s(85) = s[i] = 85... s[x] = 89

All square digit chains resolve to either 1 or 89. 
 */
@SuppressWarnings("unused")
public class Problem0092 extends Problem {

    @Override
    public boolean isSolved() {
        return true;
    }

    @Override
    public void printSolution() {
        int count = 0;
        for(int i = 1; i < 10000000; i++) {
            if(is89Loop(i)) {
                count++;
            }
        }
        System.out.println("The count of values below ten million whose square digit chain resolves to 89 is " + count);
    }

    private boolean is89Loop(int i) {
        while (i != 1 && i != 89) {
            i = getSquareOfDigits(i);
        }
        return i == 89;
    }

    private int getSquareOfDigits(int i) {
        int tot = 0;
        while (i > 0) {
            int dig = i % 10;
            tot += dig * dig;
            i /= 10;
        }
        return tot;
    }
}
