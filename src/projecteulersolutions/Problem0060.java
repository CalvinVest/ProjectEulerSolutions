package projecteulersolutions;

/*
The primes 3, 7, 109, and 673 are quite remarkable. By taking any two primes and
concatenating them in any order the result will always be prime. For example,
taking 7 and 109, both 7109 and 1097 are prime. The sum of these four primes,
792, represents the lowest sum for a set of four primes with this property.

Find the lowest sum for a set of five primes for which any two primes concatenate
to produce another prime.
 */
public class Problem0060 extends Problem {

    @Override
    public boolean isSolved() {
        return false;
    }

    @Override
    public void printSolution() {
        int[] primes = getFiveConcatPrimes();

        System.out.println("This problem has not yet been solved.");

        System.out.println("Array of primes is " + getArrayString(primes));
        System.out.println("Sum: " + calcSumIntArr(primes));
    }

    private int[] getFiveConcatPrimes() {
        int[] primes = new int[5];
        int ceiling = 100;
        primes[0] = 3;

        for (int i = 1; i < primes.length; i++) {
            primes[i] = EulerMath.getNextPrime(primes[i - 1]);
        }

        while (primes[0] < ceiling) {
            while (primes[1] < ceiling) {
                while (primes[2] < ceiling) {
                    while (primes[3] < ceiling) {
                        while (primes[4] < ceiling) {
                            System.out.println(getArrayString(primes));

                            if (isSolutionArray(primes)) {
                                return primes;
                            }

                            primes[4] = EulerMath.getNextPrime(primes[4]);
                        }
                        primes[3] = EulerMath.getNextPrime(primes[3]);
                        primes[4] = EulerMath.getNextPrime(primes[3]);
                    }
                    primes[2] = EulerMath.getNextPrime(primes[2]);
                    primes[3] = EulerMath.getNextPrime(primes[2]);
                }
                primes[1] = EulerMath.getNextPrime(primes[1]);
                primes[2] = EulerMath.getNextPrime(primes[1]);
            }
            primes[0] = EulerMath.getNextPrime(primes[0]);
            primes[1] = EulerMath.getNextPrime(primes[0]);
        }
        return null;
    }
    
    private boolean isSolutionArray(int[] primes) {
        for(int i = 0; i < primes.length; i++) {
            for(int j = 0; j < primes.length; j++) {
                if(i != j) {
                    int k = Integer.parseInt(String.valueOf(primes[i]) + String.valueOf(primes[j]));
                    if(!EulerMath.isPrime(k)) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    private String getArrayString(int[] arr) {
        String resultStr = "";
        for (int i = 0; i < arr.length; i++) {
            resultStr += arr[i] + " ";
        }
        return resultStr.trim();
    }
    
    private int calcSumIntArr(int[] arr) {
        int sum = 0;
        for(int i : arr) {
            sum += i;
        }
        return sum;
    }
}
