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
        System.out.println("File path is " + matrixFile.getAbsolutePath());
        String[] matrixSubstrs = loadMatrixData(matrixFile);
        int[][] matrix = convertSubstringsToMatrix(matrixSubstrs);
        
        System.out.println("The maximum adjacent value product is " + findMaxProd(matrix));
    }
    
    private String[] loadMatrixData(File file) {
        try {
            Scanner matrixFileScanner = new Scanner(file);
            String[] matrixSubstrs = new String[400];
            int index = 0;
            while(matrixFileScanner.hasNext()) {
                matrixSubstrs[index++] = matrixFileScanner.next();
            }
            System.out.println("Successfully loaded " + index + " numbers from file.");
            return matrixSubstrs;
            
        }
        catch (FileNotFoundException fnfe) {
            System.out.println("Exception thrown: File " + file.getName() + " not found.");
        }
        return new String[400];
    }
    
    private int findMaxProd(int[][] mx) {
        int maxProd = 0;
        
        // Horizontal
        for(int i = 0; i < mx.length - 3; i++) {
            for(int j = 0; j < mx[0].length; j++) {
                int product = mx[i][j] * mx[i+1][j] * mx[i+2][j] * mx[i+3][j];
                if(product > maxProd)
                    maxProd = product;
            }
        }
        
        // Vertical
        for(int i = 0; i < mx.length; i++) {
            for(int j = 0; j < mx[0].length - 3; j++) {
                int product = mx[i][j] * mx[i][j+1] * mx[i][j+2] * mx[i][j+3];
                if(product > maxProd)
                    maxProd = product;
            }
        }
        
        // Diagonal Descending
        for(int i = 0; i < mx.length - 3; i++) {
            for(int j = 0; j < mx[0].length - 3; j++) {
                int product = mx[i][j] * mx[i+1][j+1] * mx[i+2][j+2] * mx[i+3][j+3];
                if(product > maxProd)
                    maxProd = product;
            }
        }
        
        // Diagonal Ascending
        for(int i = 3; i < mx.length; i++) {
            for(int j = 0; j < mx[0].length - 3; j++) {
                int product = mx[i][j] * mx[i-1][j+1] * mx[i-2][j+2] * mx[i-3][j+3];
                if(product > maxProd)
                    maxProd = product;
            }
        }
        return maxProd;
    }
    
    private int[][] convertSubstringsToMatrix(String[] substrings) {
        int[][] matrix = new int[20][20];
        int substringIndex = 0;
        
        for(int i = 0; i < matrix.length; i++) {
            for(int j = 0; j < matrix[i].length; j++) {
                matrix[i][j] = Integer.parseInt(substrings[substringIndex]);
                substringIndex++;
            }
        }
        return matrix;
    }
}
