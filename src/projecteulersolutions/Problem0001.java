package projecteulersolutions;

public class Problem0001 extends Problem {
    /*
    The goal of problem 1 is to find the sum of all
    natural numbers divisible by 3 or 5 below 1000
    */
    @Override
    public void printAnswer() {
        int sum = 0;
        for(int i = 1; i < 1000; i++)
            if(i % 3 == 0 || i % 5 == 0)
                sum += i;
        System.out.println("The sum of all numbers divisible by 3 or 5 below 1000 is: " + sum);
    }
}
