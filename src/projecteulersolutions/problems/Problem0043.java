package projecteulersolutions.problems;

/*
The number 1406357289, is a 0 to 9 pandigital number because it is made up of each
of the digits 0 to 9 in some order, but it also has a rather interesting sub-string
divisibility property.

Let d1 be the 1st digit, d2 be the 2nd digit, and so on. In this way, we note the
following:

d2d3d4 = 406 is divisible by 2
d3d4d5 = 063 is divisible by 3
d4d5d6 = 635 is divisible by 5
d5d6d7 = 357 is divisible by 7
d6d7d8 = 572 is divisible by 11
d7d8d9 = 728 is divisible by 13
d8d9d10 = 289 is divisible by 17

Find the sum of all 0 to 9 pandigital numbers with this property.
 */
public class Problem0043 extends Problem {

    @Override
    public boolean isSolved() {
        return true;
    }

    @Override
    public void printSolution() {
        long sum = 0;
        String pandigital = "0123456789";

        while (pandigital != null) {
            if (isDivisibleSubstrings(pandigital)) {
                sum += Long.parseLong(pandigital);
            }

            pandigital = nextPermutation(pandigital);
        }

        System.out.println("The sum of all 0 to 9 pandigital numbers with prime substrings is " + sum);
    }

    private static boolean isDivisibleSubstrings(String pandigital) {
        // array of primes for divisibility
        int[] primes = {2, 3, 5, 7, 11, 13, 17};
        // for each prime in the aforementioned array
        for (int i = 0; i < primes.length; i++) {
            // split the number into its respective substrings for division
            int num = Integer.parseInt(pandigital.substring(i + 1, i + 4));
            // if the numbers are not appropriately divisible return false
            if (num % primes[i] != 0) {
                return false;
            }
        }
        // all substrings are appropriately divisible, return true
        return true;
    }

    private static String nextPermutation(String str) {
        // array of characters for permutating
        char[] array = str.toCharArray();
        // start at last index
        int i = array.length - 1;
        // find correct place for index i based on comparison of neighboring indeces
        while (i > 0 && array[i - 1] >= array[i]) {
            i--;
        }

        if (i <= 0) {
            return null; // No more permutations
        }

        int j = array.length - 1;
        while (array[j] <= array[i - 1]) {
            j--;
        }

        // Swap the elements at positions i-1 and j
        char temp = array[i - 1];
        array[i - 1] = array[j];
        array[j] = temp;

        // Reverse the suffix
        j = array.length - 1;
        while (i < j) {
            temp = array[i];
            array[i] = array[j];
            array[j] = temp;
            i++;
            j--;
        }

        return new String(array);
    }
}
