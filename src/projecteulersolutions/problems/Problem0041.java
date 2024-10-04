package projecteulersolutions.problems;

import projecteulersolutions.EulerMath;

/*
The goal of problem 41 is to find the largest n-digit pandigital prime
that exists. An n-digit pandigital number makes use of all the digits
1 to n exactly once.
For example, 2143 is a 4-digit pandigital number.
 */
@SuppressWarnings("unused")
public class Problem0041 extends Problem {

    /*
    When establishing an upper bound for this problem, I can assert that since the
    greatest pandigital number is 987654321, the upper bound is at most this value.
    However, every 9-digit pandigital number intrinsically has a digital sum of
    9 + 8 + 7 + 6 + 5 + 4 + 3 + 2 + 1 = 45.
    
    It is understood that any integer with a digital sum divisible by 3 is itself
    divisible by 3. Hence, for any given 9-digit pandigital number, it is not prime.
    
    Actually, any 9-, 8-, 6-, 5-, 3-, and 2-digit pandigital number will not be
    prime for this same reason.
    The only 1-digit pandigital number is 1, which is not prime.
    
    This means that our bounds can be narrowed to be only 4- and 7-digit pandigital
    numbers. Since we're looking for the LARGEST pandigital prime, the 4- digit
    numbers can be ommitted for convenience's sake.
    
    Therefore, we're just left with 7-digit numbers between 1234567 and 7654321.
    Our result is somewhere within these bounds.
     */
    private static final int LOWER_BOUND = 1234567;
    private static final int UPPER_BOUND = 7654321;

    @Override
    public boolean isSolved() {
        return true;
    }

    @Override
    public void printSolution() {
        /*
        Since we're looking for the LARGEST pandigital prime, we can start at
        the upper bound and work downwards, breaking when the first (and largest)
        result is found.
         */
        for (int i = UPPER_BOUND; i >= LOWER_BOUND; i--) {
            /*
            This checks if the number is pandigital and prime for EVERY number.
            I was trying to find a more efficient method but really this is the
            most straightfoward for the given problem.
             */
            if (EulerMath.isPandigital(i) && EulerMath.isPrime(i)) {
                System.out.println("The largest pandigital prime is " + i);
                break;
            }
        }
    }
}
