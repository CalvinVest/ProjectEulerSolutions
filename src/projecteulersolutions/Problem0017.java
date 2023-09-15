package projecteulersolutions;

/*
The goal of problem 17 is to find the sum of all characters used to write
the numbers 1 to 1000 in words.

f(5) = "one" + "two" + "three" + "four" + "five" = 19
find f(1000).
 */
public class Problem0017 extends Problem {

    @Override
    public void printSolution() {
        int sum = 0;
        
        for(int i = 1; i <= 1000; i++) {
            sum += findCharCount(i);
            System.out.println("Added " + i);
        }
        System.out.println("The sum of the digits of 1 to 1000 is " + sum);
    }

    private int findCharCount(int n) {
        int sum = 0;
        if (n == 0) {
            return 0;
        }
        if (n >= 1000) {
            return findCharCount(n / 1000) + "thousand".length()
                    + (n % 1000 == 0 ? 0 : "and".length() + findCharCount(n % 1000));
        }
        if (n >= 100) {
            return findCharCount(n / 100) + "hundred".length()
                    + (n % 100 == 0 ? 0 : "and".length() + findCharCount(n % 100));
        }
        if (n / 90 == 1) {
            return "ninety".length() + findCharCount(n % 90);
        }
        if (n / 80 == 1) {
            return "eighty".length() + findCharCount(n % 80);
        }
        if(n/70==1) {
            return "seventy".length() + findCharCount(n%70);
        }
        if(n/60==1) {
            return "sixty".length() + findCharCount(n%60);
        }
        if(n/50==1) {
            return "fifty".length() + findCharCount(n%50);
        }
        if(n/40==1) {
            return "forty".length() + findCharCount(n%40);
        }
        if(n/30==1) {
            return "thirty".length() + findCharCount(n%30);
        }
        if(n/20==1) {
            return "twenty".length() + findCharCount(n%20);
        }
        return findCharCountSub20(n);
    }
    
    private int findCharCountSub20(int n) {
        switch (n) {
            case 19: return "nineteen".length();
            case 18: return "eighteen".length();
            case 17: return "seventeen".length();
            case 16: return "sixteen".length();
            case 15: return "fifteen".length();
            case 14: return "fourteen".length();
            case 13: return "thirteen".length();
            case 12: return "twelve".length();
            case 11: return "eleven".length();
            case 10: return "ten".length();
            case 9: return "nine".length();
            case 8: return "eight".length();
            case 7: return "seven".length();
            case 6: return "six".length();
            case 5: return "five".length();
            case 4: return "four".length();
            case 3: return "three".length();
            case 2: return "two".length();
            case 1: return "one".length();
            default: return 0;
        }
    }
}
