package projecteulersolutions;

public class Problem0077 extends Problem {

    @Override
    public boolean isSolved() {
        return true;
    }

    @Override
    public void printSolution() {
        int target = 2;
        int threshold = 5000;
        java.util.List<Integer> primes = new java.util.ArrayList<>();
        primes.add(2); // Initialize primes list with first prime

        while (true) {
            int[] primeSumCounts = new int[target + 1];
            primeSumCounts[0] = 1;

            for (int prime : primes) {
                for (int i = prime; i <= target; i++) {
                    primeSumCounts[i] += primeSumCounts[i - prime];
                }
            }

            if (primeSumCounts[target] > threshold) {
                System.out.println(target + " is the first value which can be written as more than " + threshold + " prime summations.");
                break;
            }

            target++;
            // Generate the next prime and add it to the list
            primes.add(EulerMath.getNextPrime(primes.get(primes.size() - 1)));
        }
    }
}
