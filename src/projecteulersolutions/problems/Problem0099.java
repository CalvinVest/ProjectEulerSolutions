package projecteulersolutions.problems;

import java.io.*;

/*
Comparing two numbers written in index form like 2^11 and 3^7 is not difficult,
as any calculator would confirm that 2^11 = 2048 < 3^7 = 2187.

However, confirming that
632382^518061 > 519432^525806
would be much more difficult, as both numbers contain over three million digits.

Using problem0099.txt, a text file containing one thousand lines with a base/
exponent pair on each line, determine which line number has the greatest
numerical value.
*/
public class Problem0099 extends Problem {

    @Override
    public boolean isSolved() {
        return true;
    }

    @Override
    public void printSolution() {
        int maxLineNum = 0;
        try {
            maxLineNum = findMaxLineNum();
        } catch (IOException e) {
            System.out.println("Exception Encountered: problem0099.txt can not be found.");
        }


        System.out.println("The maximum value is found at problem0099.txt line #" + maxLineNum);
    }

    private int findMaxLineNum() throws IOException {
        File problemFile = new File(Problem.FILEPATH + "problem0099.txt");
        BufferedReader reader = new BufferedReader(new FileReader(problemFile));
        String line;
        int lineNumber = 0;
        int maxLine = 0;
        double maxLogValue = Double.NEGATIVE_INFINITY;

        while ((line = reader.readLine()) != null) {
            lineNumber++;
            String[] strs = line.split(",");
            double base = Double.parseDouble(strs[0]);
            double exponent = Double.parseDouble(strs[1]);

            double logValue = exponent * Math.log(base);

            if (logValue > maxLogValue) {
                maxLogValue = logValue;
                maxLine = lineNumber;
            }
        }

        reader.close();
        return maxLine;
    }
}
