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
        System.out.println("This problem has not yet been solved.");

        boolean isSolFound = false;

        for (int i = 100000; !isSolFound; i++) {
            if (hasFivePermutedMultiples(i)) {
                System.out.println(i + " is the smallest positive integer where x, 2x, 3x, 4x, 5x, and 6x contain the same digits.");
                isSolFound = true;
            }
        }
    }

    private boolean hasFivePermutedMultiples(int n) {
        String[] mults = new String[6];

        for (int i = 0; i < 6; i++) {
            int curr = n * (i + 1);
            mults[i] = Integer.toString(curr);
        }

        for (int i = 1; i < 6; i++) {
            if (mults[i].length() != mults[0].length()) {
                return false;
            }
            if (!isPermutedString(mults[0], mults[i])) {
                return false;
            }
        }

        return true;
    }

    private boolean isPermutedString(String s1, String s2) {
        char[] c1 = s1.toCharArray();
        char[] c2 = s2.toCharArray();

        java.util.Arrays.sort(c1);
        java.util.Arrays.sort(c2);

        return java.util.Arrays.equals(c1, c2);
    }
}
