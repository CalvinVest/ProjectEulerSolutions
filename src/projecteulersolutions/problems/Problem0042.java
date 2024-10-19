package projecteulersolutions.problems;

import projecteulersolutions.EulerUtils;

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
@SuppressWarnings("unused")
public class Problem0042 extends Problem {

    @Override
    public boolean isSolved() {
        return true;
    }

    @Override
    public void printSolution() {
        // count of triangle words
        int count = 0;
        // loads name file and list of words from it, trimming unecessary text
        File namesFile = new File(EulerUtils.DATA_FILEPATH + "problem0042.txt");
        List<String> words = readWordsFromFile(namesFile);

        // for every word in the list
        for (String word : words) {
            // get alphanumeric value of word
            int wordVal = getAlphaSum(word);
            // if word value is triangular, increment the count of triangle words
            if (isTriangle(wordVal)) {
                count++;
            }
        }

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

    private int getAlphaSum(String word) {
        int sum = 0;
        for (int i = 0; i < word.length(); i++) {
            sum += word.charAt(i) - 64;
        }
        return sum;
    }

    private boolean isTriangle(int n) {
        int x = 0;
        int step = 1;
        while (x < n) {
            x += step++;
        }
        return x == n;
    }
}
