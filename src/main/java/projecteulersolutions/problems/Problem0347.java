package main.java.projecteulersolutions.problems;

/*
The largest integer <= 100 that is only divisible by both the primes 2 and 3 is
96, as 96 = 32 * 3 = 2^5 * 3. For two distinct primes p and q let M(p, q, N) be
the largest positive integer <= N only divisible by both p and q and M(p,q,N) = 0
if such a positive integer does not exist.

E.b. M(2,3,100) = 96.
M(3,5,100) = 75 and not 90 because 90 is divisible by 2, 3, and 5.
M(2,73,100) = 0 because there does not exist a positive integer <= 100 that is
divisible by both 2 and 73.

Let S(N) be the sum of all distinct M(p,q,N). S(100) = 2262.

Find S(10 000 000).
*/
public class Problem0347 extends Problem {

    @Override
    public boolean isSolved() {
        return false;
    }

    @Override
    public void printSolution() {
        System.out.println("This problem has not yet been solved.");
    }
}
