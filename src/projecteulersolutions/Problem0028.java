package projecteulersolutions;

public class Problem0028 extends Problem {
    
    public static final int MAX = 1001;
    
    @Override
    public boolean isSolved() {
        return false;
    }
    
    @Override
    public void printSolution() {
        long sum = 1l;
        int inc = 2;
        int clock = 0;
        
        for(int i = 3; i <= MAX * MAX; i += inc) {
            sum += i;
            System.out.println("Added " + i + ", sum is " + sum);
            clock++;
            if(clock == 4) {
                inc += 2;
                clock = 0;
                System.out.println("Clock is reset. Incrementer is now " + inc);
            }
        }
        System.out.println("The sum of the number spiral diagonals for a " + MAX + "x" + MAX + " spiral is " + sum);
    }
}
