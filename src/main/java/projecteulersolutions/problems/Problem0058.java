package projecteulersolutions.problems;

import projecteulersolutions.EulerMath;

/*
Starting with 1 and spiralling anticlockwise in the following way, a square
spiral with side length 7 is formed.

37 36 35 34 33 32 31
38 17 16 15 14 13 30
39 18  5  4  3 12 29
40 19  6  1  2 11 28
41 20  7  8  9 10 27
42 21 22 23 24 25 26
43 44 45 46 47 48 49

It is interesting to note that the odd squares lie along the bottom right
diagonal, but what is more interesting is that 8 out of the 13 numbers lying
along both diagonals are prime; that is, a ratio of 8/13 approx. = 62%.

If one complete new layer is wrapped around the spiral above, a square spiral
with side length 9 will be formed. If this process is continued, what is the
side length of the square spiral for which the ratio of primes along both
diagonals first falls below 10%?
 */
@SuppressWarnings("unused")
public class Problem0058 extends Problem {

    @Override
    public boolean isSolved() {
        return true;
    }

    /*
    The algorithm for finding all valid values for the diagonals of the spiral
    can simply start at 1, the center value, and add a given incrementer four
    times, emulating the four sides of the square spiral.
    For example, the center value is 1.
    The four diagonals of the spiral when the side length is 3 are:
    1 + 2 = 3, 3 + 2 = 5, 5 + 2 = 7, 7 + 2 = 9.
    The complete array of diagonal values at this point is now:
    [1, 3, 5, 7, 9]
    The next four diagonals, when side = 5, are:
    9 + 4 = 13, 13 + 4 = 17, 17 + 4 = 21, 21 + 4 = 25.
    The complete array is now:
    [1, 3, 5, 7, 9, 13, 17, 21, 25]
    It can be seen now that the starting value is 1. Each rotation of the spiral,
    the number is incremented four times, by 2, then by 4, etc. incrementer = 2 (+= 2...)
    The side length can be calculated by solving incrementer + 1.
    
    Using this method, I can easily calculate every diagonal value, by incrementing
    through them until a running ratio of primes to total count of evaluated diagonal
    values is < 10%, and the side length for when this occurs can be expressed as
    the incrementer value at the time the 10% case is fulfilled, + 1.
     */
    @Override
    public void printSolution() {
        // current number within the spiral to evaluate
        int num = 1;
        // incrementer simulates advancing to the next spiral diagonal in each rotation
        int incrementer;
        // number of prime diagonal values
        int valid = 0;
        // total number of diagonal values
        int total = 1;
        // ratio of primes to total number of diagonal values
        double ratio = 1.0;
        // length of a side of the spiral
        int side;

        // outer loop increases incrementer value until the ratio falls below 10%
        for (incrementer = 2; ratio >= 0.1; incrementer += 2) {
            // inner loop simulates a rotation of the spiral
            for (int iter = 0; iter < 4; iter++) {
                num += incrementer;

                // if the current num is prime, increment prime count.
                if (EulerMath.isPrime(num)) {
                    valid++;
                }
                // increment total regardless of primality.
                total++;
            }

            // recalculates the ratio of primes to total diagonal values after
            // each layer of the spiral
            ratio = (double) valid / total;

        }

        // the length of a side is the incrementer value minus one, answer value
        // this value is now inc - 1 instead of inc + 1 since the spiral is
        // stepped an extra time in the loop above.
        side = incrementer - 1;

        System.out.println("The ratio of primes along the spiral diagonals first falls below 10% at side length = " + side);
    }
}
