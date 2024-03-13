package projecteulersolutions;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Problem0096 extends Problem {

    @Override
    public boolean isSolved() {
        return false;
    }

    @Override
    public void printSolution() {
        File file = new File(Problem.FILEPATH + "problem0096.txt");
        List<String> lines = readLinesFromFile(file);

        int[][][] puzzles = new int[50][9][9];

        for (int i = 0; i < lines.size(); i++) {
            if (i % 10 == 0) {
                continue;
            }
            int puzzleIndex = i / 10, puzzleRow = i % 10;

            for (int j = 0; j < lines.get(i).length(); j++) {
                puzzles[puzzleIndex][puzzleRow - 1][j] = Integer.parseInt(lines.get(i).substring(j, j + 1));
            }
        }

        int puzzleIndex = 0;
        int sum = 0;
        for (int[][] puzzle : puzzles) {

            solveSudoku(puzzle);

            sum += puzzle[0][0] * 100 + puzzle[0][1] * 10 + puzzle[0][2];

            puzzleIndex++;
        }
        System.out.println("The sum of the fifty three-digit numbers from the Sudoku puzzles is: " + sum);
    }

    private List<String> readLinesFromFile(File file) {
        ArrayList<String> lines = new ArrayList<>();
        try {
            Scanner fileIn = new Scanner(file);
            while (fileIn.hasNext()) {
                lines.add(fileIn.nextLine());
            }
        } catch (FileNotFoundException fnfe) {
            System.out.println("Exception encountered: " + file.toString() + " does not exist.");
        }
        return lines;
    }

    private boolean solveSudoku(int[][] board) {
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[0].length; col++) {
                // Find an unassigned cell
                if (board[row][col] == 0) {
                    // Try all possible numbers
                    for (int number = 1; number <= 9; number++) {
                        // Check if the number is allowed to be placed
                        if (isSafe(board, row, col, number)) {
                            // Make tentative assignment
                            board[row][col] = number;

                            // Return, if success
                            if (solveSudoku(board)) {
                                return true;
                            }

                            // Failure, unmake & try again
                            board[row][col] = 0;
                        }
                    }
                    // Trigger backtracking
                    return false;
                }
            }
        }
        // Solution found
        return true;
    }

    private boolean isSafe(int[][] board, int row, int col, int num) {
        // Check each value in row
        for (int d : board[row]) {
            if (d == num) {
                return false;
            }
        }

        // Check each value in column
        for (int[] boardRow : board) {
            // Check if the number we are trying to place is already present in that column,
            // return false;
            if (boardRow[col] == num) {
                return false;
            }
        }

        // Check each value in square
        int boxSideLength = (int) Math.sqrt(board.length);
        int boxRowStart = row - row % boxSideLength;
        int boxColStart = col - col % boxSideLength;

        for (int r = boxRowStart; r < boxRowStart + boxSideLength; r++) {
            for (int c = boxColStart; c < boxColStart + boxSideLength; c++) {
                if (board[r][c] == num) {
                    return false;
                }
            }
        }

        // No duplicate number found, value num is safe
        return true;
    }
}
