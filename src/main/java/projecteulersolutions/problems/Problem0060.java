package main.java.projecteulersolutions.problems;

import java.util.ArrayList;
import java.util.List;

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
        System.out.println("This problem has not yet been solved.");

        int upperBound = 10000;

        // Generate prime numbers up to 10000
        List<Integer> primes = getPrimesFromSieve(sieveOfEratosthenes(10000));
        
        // Generate concatenation table for primes up to 10000
        boolean[][] concatenationTable = generateConcatenationTable(primes, upperBound);

        // Find a set of five primes with the desired property
        List<Integer> primeSet = findPrimeSet(primes, concatenationTable);

        // Output the result
        if (primeSet != null) {
            System.out.println("Set of five primes with concatenation property: " + primeSet);
        } else {
            System.out.println("No set of five primes with concatenation property found.");
        }
    }

    private boolean[] sieveOfEratosthenes(int n) {
        boolean[] truthArray = new boolean[n + 1];
        for (int i = 0; i <= n; i++) {
            truthArray[i] = true;
        }

        for (int i = 2; i * i <= n; i++) {
            if (truthArray[i]) {
                for (int j = i * i; j < n; j += i) {
                    truthArray[j] = false;
                }
            }
        }
        return truthArray;
    }

    private List<Integer> getPrimesFromSieve(boolean[] sieve) {
        List<Integer> list = new ArrayList<>();

        for (int i = 2; i < sieve.length - 1; i++) {
            if (sieve[i]) {
                list.add(i);
                System.out.println("Added " + i);
            }
        }

        return list;
    }

    public static List<Integer> findPrimeSet(List<Integer> primes, boolean[][] concatenationTable) {
        int size = primes.size();

        // Iterate through all combinations of five primes
        for (int i = 0; i < size; i++) {
            for (int j = i + 1; j < size; j++) {
                for (int k = j + 1; k < size; k++) {
                    for (int l = k + 1; l < size; l++) {
                        for (int m = l + 1; m < size; m++) {
                            List<Integer> primeSet = new ArrayList<>();
                            primeSet.add(primes.get(i));
                            primeSet.add(primes.get(j));
                            primeSet.add(primes.get(k));
                            primeSet.add(primes.get(l));
                            primeSet.add(primes.get(m));

                            // Check if concatenating any two primes in the set produces another prime
                            boolean validSet = true;
                            for (int p = 0; p < 5; p++) {
                                for (int q = p + 1; q < 5; q++) {
                                    if (!concatenationTable[primeSet.get(p)][primeSet.get(q)]) {
                                        validSet = false;
                                        break;
                                    }
                                }
                                if (!validSet) {
                                    break;
                                }
                            }

                            // If all concatenations within the set are prime, return the set
                            if (validSet) {
                                return primeSet;
                            }
                        }
                    }
                }
            }
        }

        // No set of five primes found
        return null;
    }

    public static boolean isConcatenationPrime(int prime1, int prime2, boolean[] isPrime) {
        // Concatenate primes in both orders
        int concatenated1 = Integer.parseInt(prime1 + "" + prime2);
        int concatenated2 = Integer.parseInt(prime2 + "" + prime1);

        // Check if both concatenated numbers are prime
        return isPrime[concatenated1] && isPrime[concatenated2];
    }

    public static boolean[][] generateConcatenationTable(List<Integer> primes, int upperBound) {
        boolean[] isPrime = new boolean[upperBound * upperBound + 1];
        for (int prime : primes) {
            isPrime[prime] = true;
        }

        int size = primes.size();
        boolean[][] concatenationTable = new boolean[size][size];

        // Check concatenations between all pairs of primes
        for (int i = 0; i < size; i++) {
            for (int j = i + 1; j < size; j++) {
                concatenationTable[i][j] = isConcatenationPrime(primes.get(i), primes.get(j), isPrime);
            }
        }

        return concatenationTable;
    }
}
