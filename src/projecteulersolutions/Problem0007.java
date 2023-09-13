package projecteulersolutions;

/*
The goal of problem 7 is to find the 10001st prime number
*/
public class Problem0007 extends Problem {
    
    @Override
    public void printAnswer() {
        int countPrimes = 0;
        int latestPrime = 0;
        
        for(int i = 1; countPrimes < 10001; i++) {
            if (isPrime(i)) {
                countPrimes++;
                latestPrime = i;
            }
        }
        
        System.out.println("The 10001st prime number is " + latestPrime);
    }
}
