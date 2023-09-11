package projecteulersolutions;

public class Problem0002 extends Problem {
    @Override
    public void printAnswer() {
        int curr = 0;
        int last1 = 1;
        int last2 = 1;
        long sum = 0;
        
        while (curr < 4000000) {
            curr = last1 + last2;
            if(curr % 2 == 0)
                sum += curr;
            last2 = last1;
            last1 = curr;
            curr = last1 + last2;
        }
        
        System.out.println("The sum of all even Fibonacci numbers below 4,000,000 is " + sum);
    }
}