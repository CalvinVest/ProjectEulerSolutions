package projecteulersolutions.problems;

@SuppressWarnings("unused")
public class Problem0012 extends Problem {

    @Override
    public boolean isSolved() {
        return true;
    }

    @Override
    public void printSolution() {
        int curr = 1;
        int step = 2;
        boolean foundSolution = false;
        while (!foundSolution) {
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
        int count = 1;

        int factor = 2;
        while (factor <= n) {
            int exp = 0;
            while (n % factor == 0) {
                n /= factor;
                exp++;
            }
            count *= exp + 1;
            factor++;
        }
        return count;
    }
}
