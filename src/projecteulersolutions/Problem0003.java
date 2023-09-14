package projecteulersolutions;

public class Problem0003 extends Problem {
    /*
    The goal of problem 3 is to find the
    largest prime factor of 600851475143.
    */
    @Override
    public void printAnswer() {
        // foo is the number to factor out into primes.
        // foo is divided by prime factors in ascending order
        long foo = 600851475143l;
        
        System.out.print("The largest prime factor of " + foo + " is ");
        // an index value starting at 2 and incrementing to half of
        // the current foo value.
        for(long i = 2; i < foo / 2; i++) {
            // as long as the big number is divisible by the index value
            while (foo % i == 0) {
                // divide the big number by the index value
                foo /= i;
            }
        }
        // The final value of foo after this for loop
        // is the largest single prime factor of the original
        // foo value, as all other prime factors have been discovered
        // and the answer is this final value of foo:
        System.out.println(foo);
    }
}
