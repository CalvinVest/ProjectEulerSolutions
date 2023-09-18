package projecteulersolutions;

/*
The goal of problem 21 is to find the sum of all amicable numbers below 10,000

Let d(n) be the sum of n's proper divisors.

Numbers a and b are amicable numbers if a != b, d(a) = b, and d(b) = a.
 */
public class Problem0021 extends Problem {

    @Override
    public void printSolution() {
        int sum = 0;
        for (int i = 1; i < 10000; i++) {
            if (isAmicable(i)) {
                sum += i;
            }
        }

        System.out.println("The sum of all amicable numbers below 10,000 is " + sum);
    }

    private boolean isAmicable(int n) {
        return (n != getSumOfFactors(n)) && (n == getSumOfFactors(getSumOfFactors(n)));
    }

    private int getSumOfFactors(int n) {
        int sum = 0;
        for (int i = 1; i <= n / 2; i++) {
            if (n % i == 0) {
                sum += i;
            }
        }
        return sum;
    }
}
