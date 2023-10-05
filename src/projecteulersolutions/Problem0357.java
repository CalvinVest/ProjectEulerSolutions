package projecteulersolutions;

/*
The goal of problem 357:

Consider the divisors of 30: 1, 2, 3, 5, 6, 10, 15, 30.
It can be seen that for every divisor d of 30, d + 30/d is prime.

Find the sum of all positive integers n not exceeding 100,000,000
such that for every divisor d of n, d + n/d is prime.
 */
public class Problem0357 extends Problem {

    @Override
    public boolean isSolved() {
        return false;
    }

    @Override
    public void printSolution() {
        long sum = 0l;

        for (int i = 4; i <= 100000000; i++) {
            if (i % 100000 == 0) {
                System.out.println(i + " =======================================");
            }
            if (!isPrime(i) && hasAllPrimeCofactorSums(i)) {
                sum += i;
            }
        }

        System.out.println("The sum of all numbers with all prime cofactor sums below 100000000 is " + sum);
        // need to loop through all values
        // for(int i = 1; i <= 100000000; i++) {

        // for each number that may be a factor within given value
        // for(int j = 2; j <= (int) Math.sqrt(i); j++) {
        // is it a divisor?
        // if(i % j == 0) {
        // if so is the divisor plus cofactor prime?
        // if(isPrime(j + i/j)
        // if it isn't then this number isn't valid, break
        // if(!isPrime(j + i/j)) {
        // if it is then continue through the loop for each divisor
        // go through all divisors for the number, then do it again
    }

    /*
    hasAllPrimeCofactorSums(int) returns if a given ints divisors are all prime
    such that for every divisor d of n, d + n/d is prime.
     */
    private boolean hasAllPrimeCofactorSums(int n) {
        for (int i = 2; i <= n / 2; i++) {

            if (n % i == 0) {
                if (!isPrime(i + n / i)) {
                    return false;
                }
            }
        }
        //System.out.println(n + " has all prime cofactor sums.");
        return true;
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
