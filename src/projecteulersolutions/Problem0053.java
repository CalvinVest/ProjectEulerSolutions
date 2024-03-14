package projecteulersolutions;

public class Problem0053 extends Problem {

    @Override
    public boolean isSolved() {
        return true;
    }

    @Override
    public void printSolution() {
        int count = 0;
        
        for(int n = 2; n <= 100; n++) {
            for(int r = 1; r < n; r++) {
                double comb = getCombinations(n, r);
                System.out.println(n + "C" + r + " = " + comb);
                if(comb > 1000000) {
                    count++;
                }
            }
        }
        System.out.println("The result for problem 53 is " + count);
    }

    private double getCombinations(int n, int r) {
        if (r >= n) {
            return 0.;
        }

        double result = 1.0;
        while (r > 0) {
            result *= n--;
            result /= r--;
        }
        return result;
    }
}
