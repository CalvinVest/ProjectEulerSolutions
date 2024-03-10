package projecteulersolutions;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Problem0059 extends Problem {

    @Override
    public boolean isSolved() {
        return false;
    }

    @Override
    public void printSolution() {
        File cipherFile = new File(Problem.FILEPATH + "problem0059.txt");
        List<Integer> cipherList = readCipherFromFile(cipherFile);
        int[] cipherArr = new int[cipherList.size()];
        for (int i = 0; i < cipherArr.length; i++) {
            cipherArr[i] = cipherList.get(i);
        }

        System.out.println("\nThe length of the cipher is " + cipherArr.length + " items");
        // key values can range from 97 'a' to 122 'z'.
        int[] key = findKeyWithCommonWords(cipherArr);

        String msgStr = getDecipherString(cipherArr, key);

        int sum = 0;
        for (char c : msgStr.toCharArray()) {
            sum += (int) c;
        }
        System.out.println("The sum of the ASCII values of the original message is " + sum);
    }

    private String getDecipherString(int[] cipherArr, int[] key) {
        int[] currDecipherIntArr = new int[cipherArr.length];
        for (int i = 0; i < cipherArr.length; i++) {
            currDecipherIntArr[i] = cipherArr[i] ^ key[i % key.length];
        }
        StringBuilder msgStrBldr = new StringBuilder();
        for (int j = 0; j < currDecipherIntArr.length; j++) {
            msgStrBldr.append((char) currDecipherIntArr[j]);
        }
        return msgStrBldr.toString();
    }

    private int[] findKeyWithCommonWords(int[] cipherArr) {
        int[] key = new int[3];
        for (key[0] = (int) 'a'; key[0] <= (int) 'z'; key[0]++) {
            for (key[1] = (int) 'a'; key[1] <= (int) 'z'; key[1]++) {
                for (key[2] = (int) 'a'; key[2] <= (int) 'z'; key[2]++) {
                    System.out.println("Testing: " + (char) key[0] + " " + (char) key[1] + " " + (char) key[2]);
                    String msgStr = getDecipherString(cipherArr, key);
                    if (msgStr.contains("this") && msgStr.contains("the")) {
                        return key;
                    }
                }
            }
        }
        return new int[]{97, 97, 97};
    }

    private List<Integer> readCipherFromFile(File cipherFile) {
        ArrayList<Integer> ciphers = new ArrayList<>();
        try {
            Scanner fileIn = new Scanner(cipherFile);
            fileIn.useDelimiter(",");
            while (fileIn.hasNext()) {
                ciphers.add(Integer.valueOf(fileIn.next()));
            }
        } catch (FileNotFoundException fnfe) {
            System.out.println("Exception encountered: " + cipherFile.toString() + " does not exist.");
        }
        return ciphers;
    }
}
