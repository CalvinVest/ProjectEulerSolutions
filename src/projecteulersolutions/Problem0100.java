package projecteulersolutions;

/*
If a box contains twenty-one colored discs, composes of fifteen blue discs and six
red discs, and two discs were taken at random, it can be seen that the probability
of taking two blue discs,
P(BB) = (15/21) * (14/20) = 1/2

The next such arrangement, for which there is exactly 50% chance of taking two blue
discs at random, is a box containing 85 blue discs and 35 red discs.

By finding the arrangement to contain over 10^12 = 1000000000000 discs in total,
determine the number of blue discs that the box would contain.
 */
public class Problem0100 extends Problem {

    @Override
    public boolean isSolved() {
        return false;
    }

    /*
    The purpose of this problem is to find how many of the discs need to be blue to
    have a 50% chance of drawing two of them in a row.
    Let Pbb = b(b-1)/(t(t-1))
    
    We know that b, the number of blue discs, is proportional to t, the total number
    of discs. b = at.
    Pbb = at(at-1)/(t(t-1))
    
    Cancel the t's, and distribute the a in the numerator:
    [(a^2)t - a]/[t-1]
    
    Divide both sides by t:
    [a^2 - a/t]/[1 - 1/t]
    
    Now let's assume there's an infinite number of discs.
    lim_t -> INF_([a^2 - a/t]/[1 - 1/t]) = a^2
    As t -> INF, Pbb -> a^2.
    a^2 = 0.5
    a = sqrt(0.5)
    
    Now, Pbb may be a discrete function since b is in the set of Natural numbers
    but it does follow the trend of if b is in the set of Real numbers.
    
    This means that we can find the value of b for b = sqrt(0.5) * t. This is the 
    starting point. From there, we'll calculate if the values on either side of it
    have a smaller error. If they do, we'll start searching in that direction until
    we no longer encounter values with smaller errors. That means we've reached the
    ideal value of blue discs. This value is the solution.
     */
    @Override
    public void printSolution() {
        
    }
}
