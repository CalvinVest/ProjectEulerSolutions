package projecteulersolutions.problems;

/*
The goal of problem 21 is to find the sum of all amicable numbers below 10,000

Let d(n) be the sum of n's proper divisors.

Numbers a and b are amicable numbers if a != b, d(a) = b, and d(b) = a.

A note for the method isAmicable, (d(a) = b && d(b) = a) -> d(d(a)) = a
 */
public class Problem0021 extends Problem {

    @Override
    public boolean isSolved() {
        return true;
    }

    @Override
    public void printSolution() {
        // int to hold sum of all amicable numbers
        int sumOfAmicables = 0;
        // for all numbers below 10000
        for (int i = 1; i < 10000; i++) {
            // if the number is amicable, add it to the sum of amicables
            if (isAmicable(i)) {
                sumOfAmicables += i;
            }
        }

        System.out.println("The sum of all amicable numbers below 10,000 is " + sumOfAmicables);
    }

    /*
    isAmicable(int) returns whether the given int is amicable.
    
    where f(n) = getSumOfFactors(n)
    if
        n != f(n)
        && n == f(f(n))
    then
        n is amicable
     */
    private boolean isAmicable(int n) {
        return (n != getSumOfFactors(n)) // the number is not equal to the sum of its factors
                && (n == getSumOfFactors(getSumOfFactors(n)));
    }

    /*
    getSumOfFactors(int) returns the sum of all proper divisors of given int
    
    where n = a*b*c
    getSumOfFactors(n) = a + b + c
     */
    private int getSumOfFactors(int n) {
        // int to hold sum of factors
        int sum = 0;
        // for all numbers up to half of given value
        for (int i = 1; i <= n / 2; i++) {
            // if i is a factor of n then add i to sum of factors
            if (n % i == 0) {
                sum += i;
            }
        }
        return sum;
    }
}
