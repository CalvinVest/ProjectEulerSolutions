package projecteulersolutions;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/*
The goal of problem 11 is to find the largest product of four
adjacent values in the given matrix.
Values can be adjacent up, down, left, right, and diagonally.
 */
public class Problem0011 extends Problem {

    @Override
    public void printAnswer() {
        File matrixFile = new File(System.getProperty("user.dir") + "\\src\\projecteulersolutions\\problem0011.txt");
        String[] matrixSubstrs = loadMatrixData(matrixFile);
        int[][] matrix = convertSubstringsToMatrix(matrixSubstrs);

        System.out.println("The maximum adjacent value product is " + findMaxProd(matrix));
    }

    private String[] loadMatrixData(File file) {
        try {
            Scanner matrixFileScanner = new Scanner(file);
            String[] matrixSubstrs = new String[400];
            int index = 0;
            while (matrixFileScanner.hasNext()) {
                matrixSubstrs[index++] = matrixFileScanner.next();
            }
            return matrixSubstrs;

        } catch (FileNotFoundException fnfe) {
            System.out.println("Exception thrown: File " + file.getName() + " not found.");
        }
        return new String[400];
    }

    private int findMaxProd(int[][] mx) {
        int maxProd = 0;

        for (int i = 0; i < mx.length; i++) {
            for (int j = 0; j < mx[0].length; j++) {
                // Horizontal, i is all but last three rows
                if (i < mx.length - 3) {
                    int hProd = mx[i][j] * mx[i + 1][j] * mx[i + 2][j] * mx[i + 3][j];
                    if (hProd > maxProd) {
                        maxProd = hProd;
                    }
                }
                // Vertical, j is all but last three columns
                if (j < mx[0].length - 3) {
                    int vProd = mx[i][j] * mx[i][j + 1] * mx[i][j + 2] * mx[i][j + 3];
                    if (vProd > maxProd) {
                        maxProd = vProd;
                    }
                }
                // Diagonal Descending, i and j are all but last three rows and columns
                if (i < mx.length - 3 && j < mx[0].length - 3) {
                    int ddProd = mx[i][j] * mx[i + 1][j + 1] * mx[i + 2][j + 2] * mx[i + 3][j + 3];
                    if (ddProd > maxProd) {
                        maxProd = ddProd;
                    }
                }
                // Diagonal Ascending, i is all but first three rows and j is all but last three columns
                if (i >= 3 && j < mx[0].length - 3) {
                    int daProd = mx[i][j] * mx[i - 1][j + 1] * mx[i - 2][j + 2] * mx[i - 3][j + 3];
                    if (daProd > maxProd) {
                        maxProd = daProd;
                    }
                }
            }
        }

        return maxProd;
    }

    private int[][] convertSubstringsToMatrix(String[] substrings) {
        int[][] matrix = new int[20][20];
        int substringIndex = 0;

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                matrix[i][j] = Integer.parseInt(substrings[substringIndex]);
                substringIndex++;
            }
        }
        return matrix;
    }
}
