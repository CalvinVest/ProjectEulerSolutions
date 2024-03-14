package projecteulersolutions;

public class Problem0808 extends Problem {

    @Override
    public boolean isSolved() {
        return false;
    }

    @Override
    public void printSolution() {

        int count = 0;
        int i = 5;

        while (count < 50) {
            long currSquare = i * i;

            if (!EulerMath.isPalindrome(Long.toString(currSquare))) {
                long reverse = getReversedLong(currSquare);
                if (isPerfectSquare(reverse)) {
                    int reverseRoot = (int) Math.sqrt(reverse);
                    if (EulerMath.isPrime(reverseRoot)) {
                        count++;
                        System.out.println("Reversible Prime Square #" + count + " found: " + currSquare);
                    }
                }
            }

            i = EulerMath.getNextPrime(i);
        }
    }

    private long getReversedLong(long num) {
        long rev = 0l;

        while (num != 0) {
            int d = (int) (num % 10);
            rev = rev * 10 + d;
            num /= 10;
        }

        return rev;
    }

    private boolean isPerfectSquare(long num) {
        if (num < 0) {
            return false; // Negative numbers are not perfect squares
        }

        int root = (int) Math.sqrt(num); // Integer square root
        long checker = root * root;
        // Check if square of root equals n
        return checker == num;
    }
}
