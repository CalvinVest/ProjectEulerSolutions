package projecteulersolutions.problems;

import projecteulersolutions.EulerMath;

import java.util.ArrayList;

/*
The goal of problem 32 is to find the sum of all products whose
multiplicand/multiplier/product can be written as a 1 through 9
pandigital.

We shall say that an n-digit number is pandigital if it makes 
use of all the digits 1 to n exactly once; for example, the
5-digit number, 15234 is 1 through 5 pandigital.

The product 7254 is unusual, as the identity 39 * 186 = 7254,
containing multiplicand, multiplier, and product is 1 through 9
pandigital.
 */
@SuppressWarnings("unused")
public class Problem0032 extends Problem {

    @Override
    public boolean isSolved() {
        return true;
    }

    @Override
    public void printSolution() {
        ArrayList<Integer> listOfProducts = new ArrayList<>();
        int sum = 0;

        /*
        In establishing upper bounds:
        The smallest five digit number 10000 is made
        by multiplying 100 * 100
        100 * 100 = 10000; 11 digits
        20 * 500 = 10000; 10 digits
        5 * 2000 = 10000; 10 digits
        upper bound for i * j can be 10000.
        Anything larger would be too many digits for isPandigitalIdentity
        
        let i = 100:
        i * j = 100 * 100 = 10000; 11 digits;
        upper bound for i can be 100.
         */
        for (int i = 1; i <= 100; i++) {
            System.out.println(i);
            for (int j = i; i * j <= 10000; j++) {
                String str = "" + i + j + (i * j);
                if (str.length() != 9) {
                    continue;
                }
                int parsedInt = Integer.parseInt(str);
                if (EulerMath.isPandigital(parsedInt)) {
                    System.out.println(i + " * " + j + " = " + i * j);
                    if (!listOfProducts.contains(i * j)) {
                        listOfProducts.add(i * j);
                        sum += i * j;
                    }
                }
            }
        }

        System.out.println("The sum of all pandigital products is " + sum);
    }
}
