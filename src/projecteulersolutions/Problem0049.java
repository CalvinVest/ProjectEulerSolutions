package projecteulersolutions;

/*
The goal of problem 49 is to find an existing "prime permutation".

From projecteuler.net/problem=49:

"The arithmetic sequence, 1487, 4817, 8147, in which each of the terms
increases by 3330, is unusual in two ways:
(i) each of the three terms are prime, and,
(ii) each of the 4-digit numbers are permutations of one another.

There are no arithmetic sequences made up of three 
1-, 2-, or 3-digit primes, exhibiting this property, but there is one other
4-digit increasing sequence.

What 12-digit number do you form by concatenating the three terms
in this sequence?"

Plan:
- make isPrime function for ints
- make isPermutation for ints
- make main loop to go through all valid int triples
- valid is when j = i + 3330, k = j + 3330, and i j and k are permutative
- solution is when i != 1487 (given solution, find other)
*/
public class Problem0049 extends Problem {

    @Override
    public boolean isSolved() {
        return false;
    }

    @Override
    public void printSolution() {
        System.out.println("This solution has not been found yet.");
    }
    
    
}
