package projecteulersolutions.problems;

import projecteulersolutions.EulerMath;

/*
Take the number 192 and multiply it by each of 1, 2, and 3:
192 x 1 = 192
192 x 2 = 384
192 x 3 = 576

By concatenating each product we get the 1 to 9 pandigital, 192384576. We will
call 192384576 the concatenated product of 192 and (1,2,3).

The same can be achieved by starting with 9 and multiplying by 1, 2, 3, 4, and 5,
giving the pandigital, 918273645, which is the concatenated product of 9 and (1,2,3,4,5).

What is the largest 1 to 9 pandigital 9-digit number that can be formed as the
concatenated product of an integer with (1,2,...,n) where n > 1?
 */
public class Problem0038 extends Problem {

    @Override
    public boolean isSolved() {
        return true;
    }

    @Override
    public void printSolution() {

        // largest pandigital number made from concatenated products
        int largestPan = 0;

        // arbitrary upper bound of one million, simply because the smallest
        // possible i value for a product array of {1,2} would be 
        for (int i = 9; i < 100000; i++) {
            // for arrays {1,2} to {1,2,3,4,5} since a 5 member array is the largest
            // that can product an answer, 9 x {1,2,3,4,5}.
            for (int j = 2; j < 5; j++) {
                // concatenated string, e.g. "9" + "18" + "27" + "36" + "45"
                // for i = 9, j = 5, concat str = 918273645
                String concatStr = getConcatProdStr(i, j);
                // if the string isnt 9 digits long, it's not a 1-9 pandigital
                if (concatStr.length() != 9) {
                    continue;
                }
                // if the found number is pandigital and larger than the greatest
                // found pandigital number, replace it.
                int concatNum = Integer.parseInt(concatStr);
                if (EulerMath.isPandigital(concatNum) && concatNum > largestPan) {
                    largestPan = concatNum;
                }
            }
        }

        System.out.println("The largest concatenated pandigital number is " + largestPan);
    }

    // for given inputs n and iter, returns the concatenation of all multiples of n
    // up to n * iter, for example getConcatProdStr(9, 5) returns "9" + "18" + "27"
    // + "36" + "45" = "918273645".
    private String getConcatProdStr(int n, int iter) {
        String concatStr = "";
        // for every value 1 to iter, concatenate the result of n*iter to concatStr
        for (int i = 1; i <= iter; i++) {
            concatStr += (i * n) + "";
        }
        return concatStr;
    }
}
