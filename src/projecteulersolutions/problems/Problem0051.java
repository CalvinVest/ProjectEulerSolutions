package projecteulersolutions.problems;

import projecteulersolutions.EulerMath;

import java.util.ArrayList;
import java.util.List;

public class Problem0051 extends Problem {

    @Override
    public boolean isSolved() {
        return true;
    }

    @Override
    public void printSolution() {
        int indexPrime = 11;
        
        while(!checkForFamilyPrimes(indexPrime, 8)) {
            indexPrime = EulerMath.getNextPrime(indexPrime);
        }
        
    }
    
    private boolean checkForFamilyPrimes(int primeValue, int familyMemberCount) {
        List<String> wildcardStrings = generateFamilyValueWildcards(primeValue);
        
        for(String wildcardStr : wildcardStrings) {
            List<Integer> primes = getPrimeFamilyMembersFromWildcard(wildcardStr);
            if(primes.size() >= familyMemberCount) {
                System.out.println("SOLUTION FOUND: " + primes.get(0));
                System.out.println("Original Prime: " + primeValue);
                System.out.println("Template for family members: " + wildcardStr);
                System.out.print("Prime Family Members: [");
                for(int prime : primes) {
                    System.out.print(" " + prime);
                }
                System.out.println("]");
                return true;
            }
        }
        return false;
    }
    
    private List<Integer> getPrimeFamilyMembersFromWildcard(String wildStr) {
        int startNum = (wildStr.charAt(0) == 'x') ? 1 : 0;
        int[] values = new int[10];
        
        for(int i = startNum; i <= 9; i++) {
            String unwildStr = wildStr.replace('x', Integer.toString(i).charAt(0));
            values[i] = Integer.parseInt(unwildStr);
        }
        
        List<Integer> primes = new ArrayList<>();
        for(int val : values) {
            if(EulerMath.isPrime(val)) {
                primes.add(val);
            }
        }
        
        return primes;
    }
    
    private List<String> generateFamilyValueWildcards(int num) {
        List<String> familyValues = new ArrayList<>();
        String numStr = Integer.toString(num);
        familyValues.add(numStr);
        
        for(int i = 0; i < numStr.length(); i++) {
            List<String> newFamilyValues = new ArrayList<>();
            for(String familyValue : familyValues) {
                String newFamilyValue = replaceCharAtIndex(familyValue, i, 'x');
                newFamilyValues.add(newFamilyValue);
            }
            familyValues.addAll(newFamilyValues);
        }
        familyValues.remove(familyValues.size() - 1);
        familyValues.remove(0);
        return familyValues;
    }
    
    private static String replaceCharAtIndex(String str, int index, char replacement) {
        if (index < 0 || index >= str.length()) {
            throw new IndexOutOfBoundsException("Index is out of bounds");
        }

        char[] charArray = str.toCharArray();
        charArray[index] = replacement;
        return new String(charArray);
    }
}
