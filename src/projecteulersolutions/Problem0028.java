package projecteulersolutions;

public class Problem0028 extends Problem {
    
    public static final int MAX = 1001;
    
    @Override
    public boolean isSolved() {
        return false;
    }
    
    @Override
    public void printSolution() {
        // 1 is the center of any spiral, so it is auto added to the sum
        long sum = 1l;
        // incrementer starts at 2 and increases by 2 every rotation, or 4 cycles
        int inc = 2;
        // clock to hold number of cycles within a given rotation. [0, 3]
        int clock = 0;
        
        // range is any value in spiral matrix of size MAX*MAX
        // only diagonal values of the matrix are considered and added to sum
        for(int i = 1 + inc; i <= MAX * MAX; i += inc) {
            sum += i;
            clock++;
            if(clock == 4) {
                inc += 2;
                clock = 0;
            }
        }
        System.out.println("The sum of the diagonals for a " + MAX + "x" + MAX + " spiral matrix is " + sum);
    }
}
