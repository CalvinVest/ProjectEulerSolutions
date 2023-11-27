package projecteulersolutions;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/*
The nth term of the sequence of triangle numbers is given by tn = n(n+1)/2; so the
first ten triangle numbers are:

1,3,6,10,15,21,28,36,45,55

By converting each letter in a word to a number corresponding to its alphabetical
position and adding these values we form a word value. For example, the word value
for SKY is 19 + 11 + 25 = 55 = t10. If the word value is a triangle number then
we shall call the word a triangle word.

Using words.txt, (renamed to problem0042.txt for this project), a 16K text file
containing nearly two thousand common English words, how many are triangle words?
 */
public class Problem0042 extends Problem {

    @Override
    public boolean isSolved() {
        return false;
    }

    @Override
    public void printSolution() {
        int count = 0;
        File namesFile = new File(Problem.FILEPATH + "problem0042.txt");
        List<String> words = readWordsFromFile(namesFile);

        words.forEach(word -> {
            int wordVal = getAlphaSum(word);
            /*if(EulerMath.isTriangle(wordVal)) {
                count++;
            }*/
        });

        System.out.println(count + " words in the list are triangle words.");
    }

    private List<String> readWordsFromFile(File file) {
        ArrayList<String> words = new ArrayList<>();
        try {
            Scanner fileIn = new Scanner(file);
            fileIn.useDelimiter(",");
            while (fileIn.hasNext()) {
                String word = fileIn.next();
                words.add(word.substring(1, word.length() - 1));
            }
        } catch (FileNotFoundException fnfe) {
            System.out.println("Exception encountered: " + file.toString() + " does not exist.");
        }
        return words;
    }

    private int getAlphaSum(String name) {
        int sum = 0;
        for (int i = 0; i < name.length(); i++) {
            sum += name.charAt(i) - 64;
        }
        return sum;
    }
}
