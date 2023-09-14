package projecteulersolutions;

/*
The goal of Problem 9 is to find the product of the values of the
only existing Pythagorean triplet whose three values sum to 1000.

That is,
a^2 + b^2 = c^2, a + b + c = 1000, a < b < c
find a*b*c
*/
public class Problem0009 implements Problem {
    @Override
    public void printAnswer() {
        int[] triplet = getPythgTripletValues();
        System.out.println("The Pythagorean Triplet is ["
            + triplet[0] + ", "
            + triplet[1] + ", "
            + triplet[2] + "]");
        int tripletProduct = triplet[0] * triplet[1] * triplet[2];
        System.out.println("The product of the triplet is " + tripletProduct);
    }
    
    
    private int[] getPythgTripletValues() {
        for(int a = 1; a < 1000; a++) {
            for(int b = a + 1; b < 1000; b++) {
                int c = 1000 - a - b;
                if(isPythagorean(a, b, c)) {
                    return new int[]{a, b, c};
                }
            }
        }
        return new int[]{0,0,0};
    }
    
    private boolean isPythagorean(int a, int b, int c) {
        return a*a + b*b == c*c;
    }
}
