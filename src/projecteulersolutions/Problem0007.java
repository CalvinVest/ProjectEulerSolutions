package projecteulersolutions;

/*
The goal of problem 7 is to find the 10001st prime number
*/
public class Problem0007 extends Problem {
    
    @Override
    public void printAnswer() {
        int countPrimes = 0;
        int latestPrime = 0;
        
        for(int i = 2; countPrimes < 10001; i++) {
            if (isPrime(i)) {
                countPrimes++;
                latestPrime = i;
            }
        }
        
        System.out.println("The 10001st prime number is " + latestPrime);
    }
    
    private boolean isPrime(int n) {
        for(int i = 2; i <= n / 2; i++) {
            if (n % i == 0) return false;
        }
        return true;
    }
}
