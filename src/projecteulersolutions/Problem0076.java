package projecteulersolutions;

import java.math.BigInteger;

public class Problem0076 extends Problem {

    @Override
    public boolean isSolved() {
        return true;
    }

    @Override
    public void printSolution() {

        System.out.println("The number of ways to express 100 as a sum of integers is " + countSumExpressionsString(100));
    }

    private String countSumExpressionsString(int num) {
        BigInteger[][] table = new BigInteger[num+1][num+1];
        
        for(int i = 0; i <= num; i++) {
            for(int j = num; j >= 0; j--) {
                if(j > i) {
                    table[i][j] = BigInteger.ZERO;
                } else if(j == i) {
                    table[i][j] = BigInteger.ONE;
                } else if(j==0) {
                    table[i][j] = table[i][j+1];
                } else {
                    table[i][j] = table[i][j+1].add(table[i-j][j]);
                }
            }
        }
        return table[num][1].subtract(BigInteger.ONE).toString();
    }
}
