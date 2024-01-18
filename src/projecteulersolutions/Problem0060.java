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
        int ceiling = 1000;
        primes[0] = 3;
        primes[1] = 7;

        for (int i = 2; i < primes.length; i++) {
            primes[i] = EulerMath.getNextPrime(primes[i - 1]);
        }

        while (primes[0] < ceiling) {
            System.out.println(primes[0]);
            while (primes[1] < ceiling) {
                while (primes[2] < ceiling) {
                    while (primes[3] < ceiling) {
                        while (primes[4] < ceiling) {
                            // System.out.println(getArrayString(primes));

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
        return new int[]{0, 0, 0, 0, 0};
    }

    private boolean isSolutionArray(int[] primes) {
        for (int i = 0; i < primes.length; i++) {
            for (int j = 0; j < primes.length; j++) {
                if (i != j) {
                    int k = Integer.parseInt(String.valueOf(primes[i]) + String.valueOf(primes[j]));
                    if (!EulerMath.isPrime(k)) {
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
        for (int i : arr) {
            sum += i;
        }
        return sum;
    }
    
    private int[] getArrayOfPrimes(int ceilingInclusive) {
        int primeCount = 0;
        boolean[] sieve = sieveOfEratosthenes(ceilingInclusive);
        for(boolean isPrimeFlag : sieve) {
            if(isPrimeFlag) primeCount++;
        }
        int[] primes = new int[primeCount];
        int primeIndex = 0;
        for(int sieveIndex = 0; sieveIndex < sieve.length; sieveIndex++) {
            if(sieve[sieveIndex]) {
                primes[primeIndex++] = sieveIndex;
            }
        }
        return primes;
    }
    
    public static boolean[] sieveOfEratosthenes(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("Negative array size");
        }
        // make a bool array for values 0 to n
        boolean[] sieve = new boolean[n + 1];
        // if array is 2 or more, 2 is prime
        if (n >= 2) {
            sieve[2] = true;
        }
        // for all odd values, set to true
        for (int i = 3; i <= n; i += 2) {
            sieve[i] = true;
        }
        // for all odd values up to sqrt n, set to false if not prime
        for (int i = 3; i <= (int) Math.sqrt(n); i += 2) {
            if (sieve[i]) {
                int increment = 2 * i;
                for (int j = i * i; j <= n; j += increment) {
                    sieve[j] = false;
                }
            }
        }
        return sieve;
    }
}
