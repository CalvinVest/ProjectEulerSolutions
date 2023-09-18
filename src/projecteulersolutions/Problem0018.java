package projecteulersolutions;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/*
The goal of problem 18 is to find the maximum path traversal through the 
tree given in problem0018.txt
 */
public class Problem0018 extends Problem {

    @Override
    public void printSolution() {
        
        int treeWidth = 15; // number of rows in the tree
        
        int[][] tree = readFileValues(treeWidth);
        
        int treeMaxPath = condenseRows(tree);
        
        System.out.println("The maximum path sum is " + treeMaxPath);
    }

    private int[][] readFileValues(int treeWidth) {
        int[][] tree = new int[treeWidth][treeWidth];
        File file = new File(Problem.FILEPATH + "problem0018.txt");

        try {
            Scanner fileIn = new Scanner(file);

            for (int i = 0; i < treeWidth; i++) {
                for (int j = 0; j <= i; j++) {
                    tree[i][j] = fileIn.nextInt();
                }
            }
        } catch (FileNotFoundException fnfe) {
            System.out.println("Exception: File " + file.getName() + " not found.");
        }

        return tree;
    }

    private int condenseRows(int[][] tree) {
        for (int i = tree.length - 2; i >= 0; i--) {
            for (int j = 0; j <= i; j++) {
                tree[i][j] += (tree[i + 1][j] > tree[i + 1][j + 1]) ? tree[i + 1][j] : tree[i + 1][j + 1];
            }
        }
        
        return tree[0][0];
    }
}
