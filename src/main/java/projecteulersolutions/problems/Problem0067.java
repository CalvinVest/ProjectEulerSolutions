package projecteulersolutions.problems;

import projecteulersolutions.EulerUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/*
The goal of problem 67 is to find the maximum path traversal through the 
tree given in problem0067.txt
 */
@SuppressWarnings("unused")
public class Problem0067 extends Problem {

    @Override
    public boolean isSolved() {
        return true;
    }

    @Override
    public void printSolution() {
        // number of rows in the tree
        int treeWidth = 100;

        // triangular matrix of size treeWidth*treeWidth is instantiated
        // with values loaded from problem0067.txt
        int[][] tree = readFileValues(treeWidth);

        // finds max path sum by condensing rows backward
        int treeMaxPath = condenseRows(tree);

        System.out.println("The maximum path sum is " + treeMaxPath);
    }

    private int[][] readFileValues(int treeWidth) {
        int[][] tree = new int[treeWidth][treeWidth];
        File file = new File(EulerUtils.DATA_FILEPATH + "problem0067.txt");

        try {
            Scanner fileIn = new Scanner(file);

            // read in all values of tree as a triangular matrix.
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
        // starting with the second-to-last row of the tree and going backward
        // since the row after current is referenced in the loops
        for (int i = tree.length - 2; i >= 0; i--) {
            // j up to i since the matrix used is triangular
            for (int j = 0; j <= i; j++) {
                // adds to current value the greater of two children
                tree[i][j] += Math.max(tree[i + 1][j], tree[i + 1][j + 1]);
            }
        }
        // this algorithm retroactively adds child values to parent values
        // working upward to find the maximum path sum and returns that value
        return tree[0][0];
    }
}
