package projecteulersolutions;

public class Problem0014 extends Problem {
    
    @Override
    public void printSolution() {
        int maxChainLength = 0;
        int maxChainLengthNum = 0;
        
        for(int i = 1; i < 1000000; i++) {
            int chainLength = calcCollatzChainLength(i);
            if(chainLength > maxChainLength) {
                maxChainLength = chainLength;
                maxChainLengthNum = i;
            }
        }
        
        System.out.println("The number under one million with the largest Collatz Chain is " + maxChainLengthNum);
    }
    
    private int calcCollatzChainLength(int n) {
        long x = n;
        int steps = 0;
        while(x > 1) {
            if(x % 2 == 0) {
                x /= 2;
            }
            else {
                x = x*3 + 1;
            }
            steps++;
        }
        return steps;
    }
}
