package projecteulersolutions;

/*
If p is the perimeter of a right angle triangle with integral length
sides, {a, b, c}, there are exactly three solutions for p = 120.
{20, 48, 52}, {24, 45, 51}, {30, 40, 50}
Find which value for p <= 1000 the number of solutions is maximised.
*/
public class Problem0039 extends Problem {
    
    @Override
    public boolean isSolved() {
        return false;
    }
    
    @Override
    public void printSolution() {
        // max p is largest allowable perimeter
        // a and b can be up to max p
        // c^2 = a^2 + b^2 
        // p = a + b + c
        int maxP = 120;
        int[] perimeterCount = new int[maxP + 1];
        for(int a = 1; a <= maxP; a++) {
            for(int b = a; a + b <= maxP; b++) {
                int c = (int) Math.sqrt(a*a + b*b);
                if(isPythagorean(a, b, c) && a + b + c <= maxP) {
                    int p = a + b + c;
                    perimeterCount[p]++;
                    System.out.println("Pythagorean: " + a + ", " + b + ", " + c + " --- " + p);
                }
            }
        }
        int maxCountPerimeter = getMaxIndex(perimeterCount);
        
        System.out.println("Perimeter with most solutions is " + maxCountPerimeter);
    }
    
    private int getMaxIndex(int[] nums) {
        int max = nums[0];
        int maxIndex = 0;
        
        for(int i = 1; i < nums.length; i++) {
            if(nums[i] > max) {
                max = nums[i];
                maxIndex = i;
            }
        }
        
        return maxIndex;
    }
    
    private boolean isPythagorean(int a, int b, int c) {
        return a * a + b * b == c * c;
    }
}
