package projecteulersolutions;

public class Problem0085 extends Problem {

    @Override
    public boolean isSolved() {
        return true;
    }

    @Override
    public void printSolution() {
        long area = 0;
        long minError = 2000000l;

        for (int h = 2; h < 1000; h++) {
            for (int w = 2; w < 1000; w++) {
                var currError = Math.abs(2000000 - getRectangles(h, w));
                if (currError < minError) {
                    minError = currError;
                    area = h * w;
                }
            }
        }
        System.out.println("The area of a grid that can fit closest to two million rectangles is " + area);
    }

    private long getRectangles(int h, int w) {
        return (h + 1) * h * (w + 1) * w / 4;
    }
}
