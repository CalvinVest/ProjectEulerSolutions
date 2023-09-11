package projecteulersolutions;

/*
The goal of problem 4 is to find the largest palindromic
product of two three-digit numbers.

Find max(x)
Where x is y * z, 100 <= y < 1000, 100 <= z < 1000
*/
public class Problem0004 extends Problem {
    
    @Override
    public void printAnswer() {
        int largestPalindrome = 0;
        
        for (int x = 100; x < 1000; x++)
            for (int y = 100; y < 1000; y++)
                if (isPalindrome(x * y) && largestPalindrome < x * y)
                    largestPalindrome = x * y;
                    
        System.out.println("The largest palindromic product of"
                + " two 3-digit numbers is " + largestPalindrome);
    }
    
    private boolean isPalindrome(int i) {
        String str = Integer.toString(i);
        int n = 0, m = str.length() - 1;
        while(n < m) {
            if(str.charAt(n) != str.charAt(m))
                return false;
            n++;
            m--;
        }
        return true;
    }
}
