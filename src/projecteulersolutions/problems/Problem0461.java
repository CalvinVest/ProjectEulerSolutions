package projecteulersolutions.problems;

import java.util.Arrays;

/*
Let fn(k) = e^(k/n) - 1 for k is Z > 0
We are given f200(6) + f200(75) + f200(89) + f200(226)
 = 3.141592644529 or almost pi.
It is the best approximation of pi for n = 200
with the expression fn(a) + fn(b) + fn(c) + fn(d)
 */
@SuppressWarnings("unused")
public class Problem0461 extends Problem {

    @Override
    public boolean isSolved() {
        return true;
    }

    @Override
    public void printSolution() {
        int n = 10000;
        int max = (int) (Math.log(Math.PI + 1) * n);
        // array of all valid numbers for myFunc(i, n)
        double[] numArr = calcNums(max, n);
        double[] sumArr = calcSums(numArr, max);

        int[] ab_cd = findSumIndeces(sumArr, Math.PI);
        int[] a_b = findSumIndeces(numArr, sumArr[ab_cd[0]]);
        int[] c_d = findSumIndeces(numArr, sumArr[ab_cd[1]]);

        int a = a_b[0];
        int b = a_b[1];
        int c = c_d[0];
        int d = c_d[1];

        System.out.println("The closest approximation for pi with n = " + n + ":\n"
                + "f_" + n + "(" + a + ") + "
                + "f_" + n + "(" + b + ") + "
                + "f_" + n + "(" + c + ") + "
                + "f_" + n + "(" + d + ") = "
                + calcPiSum(a, b, c, d, n) + "\n"
                + "The solution is g(" + a + ", " + b + ", " + c + ", " + d + ") = "
                + a + "^2 + "
                + b + "^2 + "
                + c + "^2 + "
                + d + "^2 = "
                + getSumOfSquares(new int[]{a, b, c, d}));
    }

    /*
    calcPiSum({4 ints}, n) returns the pi approximation for the given
    four values in the form of
    f_n(a) + f_n(b) + f_n(c) + f_n(d)
    where f_n(x) is expFunc(x, n)
     */
    private double calcPiSum(int a, int b, int c, int d, int n) {
        return expFunc(a, n) + expFunc(b, n) + expFunc(c, n) + expFunc(d, n);
    }

    /*
    calcNums returns an array of size max + 1 full of the first max
    values of f_n(i), where i is an incrementer starting at 1 and 
    f_n(i) is expFunc(i, n).
     */
    private double[] calcNums(int max, int n) {
        double[] result = new double[max + 1];
        for (int i = 1; i <= max; i++) {
            result[i] = expFunc(i, n);
        }
        return result;
    }

    /*
    expFunc is the f_n(x) of the problem.
    Returns the mathematical result of e^(k/n) - 1 for a given k and n
     */
    private double expFunc(int k, int n) {
        return Math.exp((double) k / n) - 1;
    }

    /*
    calcSums(nums, max) finds and returns a sorted array of all
    the sums nums[i] + nums[j] for the given array nums.
    This function does not consider any values where j >= i.
    The sums array is triangular (since i > j) with sides
    max and max-1, so the returned
    sums array size is (max * (max-1)) / 2
     */
    private double[] calcSums(double[] nums, int max) {
        int index = 0;
        // result is a triangular array with width L from nested for loops
        // so size will be (L * (L-1)) / 2. L-1 offset is because of index offset
        // for interior loop.
        double[] result = new double[max * (max - 1) / 2];
        for (int i = max - 1; i >= 2; i--) {
            for (int j = i - 2; j >= 1; j--) {
                result[index] = nums[i] + nums[j];
                index++;
            }
        }
        Arrays.sort(result);
        return result;
    }

    /*
    findSumIndeces(array, num) finds the indeces of the two values
    in the array that sum to the given sum value the closest.
     */
    private int[] findSumIndeces(double[] arr, double sum) {
        double diff = Double.MAX_VALUE;
        int u = getUpperBoundIndex(arr, sum);
        int l = 0;
        int[] result = new int[2];
        while (l < u) {
            // diffTemp is the difference between the sum of the 
            //current values and the given sum
            double diffTemp = sum - (arr[l] + arr[u]);
            // if the given pair sum closer to the given sum
            if (Math.abs(diffTemp) < diff) {
                // new closest difference is set
                diff = Math.abs(diffTemp);
                // lower index is recorded
                result[0] = l;
                // upper index is recorded
                result[1] = u;
            }
            if (diffTemp < 0) { // found sum was too large
                u--; // decrease upper bound
            } else if (diffTemp > 0) { // found sum was too small
                l++; // increase lower bound
            } else { // found sum was correct
                break; // found upper and lower values
            }
        }
        return result; // return upper and lower value
    }

    /*
    getUpperBound returns the index of a given array that is the
    largest value less than the given number
     */
    private int getUpperBoundIndex(double[] arr, double num) {
        int low = 0;
        int high = arr.length;
        while (low < high) {
            int mid = (low + high) / 2;
            if (arr[mid] <= num) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }
        return low - 1;
    }

    /*
    getSumOfSquares is the g(x) for this problem.
    Returns for a given array the sum of the squares of all its values
     */
    private int getSumOfSquares(int[] arr) {
        int sum = 0;
        for (int n : arr) {
            sum += n * n;
        }
        return sum;
    }
}
