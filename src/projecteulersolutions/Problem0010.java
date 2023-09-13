package projecteulersolutions;

/*
The goal of problem 10 is to find the sum of
all primes below two million
*/
public class Problem0010 extends Problem {
    
    @Override
    public void printAnswer() {
        long sum = 2l;
        
        for(int i = 3; i < 2000000; i+=2) {
            if(isPrime(i)){
                sum += i;
                System.out.println(i + " is prime");
            }
            
        }
        
        System.out.println("The sum of all primes below two million is " + sum);
    }
    
    
    
    private boolean isPrime(int n) {
        for(int i = 2; i <= n / 2; i++) {
            if (n % i == 0) return false;
        }
        return true;
    }
}
