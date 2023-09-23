package projecteulersolutions;

/*
The goal of problem 30 is to find the sum of all numbers which can be
expressed as the sum of the fifth power of their digits.

Where
1634 = 1^4 + 6^4 + 3^4 + 4^4
1634 is a fourth power digit value.

Find the sum of all fifth power digit values.
 */
public class Problem0030 extends Problem {

    @Override
    public boolean isSolved() {
        return false;
    }

    @Override
    public void printSolution() {
        int sum = 0;
        // upper bound 354294 is taken from the understanding that
        // the upper bound can have at most 6 digits of all 9s.
        // the fifth power digit sum of this upper bound would be
        // 6 * 9^5 or 354294.
        for (int i = 2; i < 354294; i++) {
            if (isFifthPowerDigitValue(i)) {
                sum += i;
            }
        }

        System.out.println("The sum of all fifth power digit values is " + sum);
    }

    private boolean isFifthPowerDigitValue(int n) {
        int temp = n;
        int sum = 0;
        while (temp > 0) {
            sum += Math.pow(temp % 10, 5);
            temp /= 10;
        }
        return sum == n;
    }
}
