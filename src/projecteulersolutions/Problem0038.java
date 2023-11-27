package projecteulersolutions;

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
        return false;
    }

    @Override
    public void printSolution() {
        
        int largestPan = 0;
        
        for(int i = 1; i < 1000000; i++) {
            for(int j = 2; j <= 9; j++) {
                String concatStr = getConcatProdStr(i, j);
                if(concatStr.length() != 9) {
                    continue;
                }
                int concatNum = Integer.parseInt(concatStr);
                if(EulerMath.isPandigital(concatNum) && concatNum > largestPan) {
                    largestPan = concatNum;
                }
            }
        }
        
        System.out.println("The largest concatenated pandigital number is " + largestPan);
    }
    
    private String getConcatProdStr(int n, int iter) {
        String concatStr = "";
        for(int i = 1; i <= iter; i++) {
            concatStr += (i * n) + "";
        }
        return concatStr;
    }
}
