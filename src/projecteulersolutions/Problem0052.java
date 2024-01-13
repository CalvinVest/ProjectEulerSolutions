package projecteulersolutions;

/*
It can be seen that the number, 125874, and its double, 251748, contain exactly
the same digits, but in a different order.

Find the smallest positive integer, x, such that 2x, 3x, 4x, 5x, and 6x contain
the same digits.
 */
public class Problem0052 extends Problem {

    @Override
    public boolean isSolved() {
        return false;
    }

    @Override
    public void printSolution() {
        // flag bool, if solution is found yet for upcoming for loop
        boolean isSolFound = false;

        // for loop increments primary searching int, continues until flag bool
        // isSolFound is true, indicating problem solved
        for (int i = 100000; !isSolFound; i++) {
            // checks if 2x-6x are permutations of the string form of x
            if (hasFivePermutedMultiples(i)) {
                // solution found, print to console and update flag bool
                System.out.println(i + " is the smallest positive integer where x, 2x, 3x, 4x, 5x, and 6x contain the same digits.");
                isSolFound = true;
            }
        }
    }

    /*
    hasFivePermutedMultiples is a private checker method that returns if a given
    int n is such that multiples 2n-6n are permutations of the string of the 
    original int n
    */
    private boolean hasFivePermutedMultiples(int n) {
        // string array to hold strings of n-6n
        String[] mults = new String[6];

        // loading string array with multiples
        for (int i = 0; i < 6; i++) {
            int curr = n * (i + 1);
            mults[i] = Integer.toString(curr);
        }

        /*
        checking 2n-6n against n, by skimming false cases (since the search
        is until a valid case is found, looping through possible values and
        continuing when one fails a case is much faster than checking every
        condition against every potential value.
        
        That being said, I'm sure there's plenty of ways I can improve this
        method, and I very well may in the near future. We'll see.
        */
        for (int i = 1; i < 6; i++) {
            // if the length of the two strings are not equal, they can not be
            // permutations. exit and return false
            if (mults[i].length() != mults[0].length()) {
                return false;
            }
            // if this multiple is not a permutation, then exit and return false
            if (!isPermutedString(mults[0], mults[i])) {
                return false;
            }
        }

        // each of the multiples are permutations of the original value, true
        return true;
    }

    /*
    isPermutedString is a checker method that returns if the two provided strings
    s1 and s2 are permutations of one another, used by hasFivePermutedMultiples
    to check if each multiple 2n-6n is a permutation of the original value n
    */
    private boolean isPermutedString(String s1, String s2) {
        // convert both strings to char arrays
        char[] c1 = s1.toCharArray();
        char[] c2 = s2.toCharArray();

        // sort both arrays
        java.util.Arrays.sort(c1);
        java.util.Arrays.sort(c2);

        // return equality of arrays by values
        return java.util.Arrays.equals(c1, c2);
    }
}
