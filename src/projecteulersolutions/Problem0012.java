package projecteulersolutions;

public class Problem0012 extends Problem {

    @Override
    public void printSolution() {
        int curr = 1;
        int step = 2;
        boolean foundSolution = false;
        while (!foundSolution) {
            System.out.println("Triangle number: " + curr);
            if (getNumberOfDivisors(curr) > 500) {
                System.out.println("The smallest triangular number with more than 500 divisors is " + curr);
                foundSolution = true;
            } else {
                curr += step;
                step++;
            }
        }
    }

    public static int getNumberOfDivisors(int n) {
        int ret = 1;

        int factor = 2;
        while (factor <= n) {
            int temp = 1;
            while (n % factor == 0) {
                n /= factor;
                temp++;
            }
            ret *= temp;
            factor++;
        }
        return ret;
    }
}
