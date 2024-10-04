package projecteulersolutions.problems;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@SuppressWarnings("unused")
public class Problem0059 extends Problem {

    @Override
    public boolean isSolved() {
        return true;
    }

    @Override
    public void printSolution() {
        File cipherFile = new File(Problem.FILEPATH + "problem0059.txt");
        List<Character> cipherList = readCipherFromFile(cipherFile);
        char[] cipherArr = new char[cipherList.size()];
        for (int i = 0; i < cipherArr.length; i++) {
            cipherArr[i] = cipherList.get(i);
        }

        System.out.println("\nThe length of the cipher is " + cipherArr.length + " items");

        char[] key = findKeyWithCommonWords(cipherArr);
        String msgStr = getDecipherString(cipherArr, key);

        System.out.println("The sum of the ASCII values of the original message is " + getSumOfASCII(msgStr));
    }

    private int getSumOfASCII(String asciiStr) {
        int sum = 0;
        for (char c : asciiStr.toCharArray()) {
            sum += c;
        }
        return sum;
    }

    private String getDecipherString(char[] cipherArr, char[] key) {
        StringBuilder msgStrBldr = new StringBuilder();
        for (int i = 0; i < cipherArr.length; i++) {
            msgStrBldr.append((char) (cipherArr[i] ^ key[i % key.length]));
        }
        return msgStrBldr.toString();
    }

    private char[] findKeyWithCommonWords(char[] cipherArr) {
        char[] key = {'a', 'a', 'a'};

        while (key[0] < 'z') {
            String msgStr = getDecipherString(cipherArr, key);
            if (msgStr.contains("this") && msgStr.contains("the")) {
                return key;
            }
            key = incrLowercaseCharArr(key);
        }
        return key;
    }

    private List<Character> readCipherFromFile(File cipherFile) {
        ArrayList<Character> ciphers = new ArrayList<>();
        try {
            Scanner fileIn = new Scanner(cipherFile);
            fileIn.useDelimiter(",");
            while (fileIn.hasNext()) {
                ciphers.add((char) fileIn.nextInt());
            }
        } catch (FileNotFoundException fnfe) {
            System.out.println("Exception encountered: " + cipherFile.toString() + " does not exist.");
        }
        return ciphers;
    }

    private char[] incrLowercaseCharArr(char[] charArr) {
        int index = charArr.length - 1;
        while (index >= 0) {
            if (charArr[index] == 'z') {
                charArr[index] = 'a'; // Reset to 'a' if 'z' is encountered
                index--; // Move to the previous character
            } else {
                charArr[index]++; // Increment the rightmost character
                break; // Exit the loop once incremented
            }
        }
        return charArr;
    }
}
