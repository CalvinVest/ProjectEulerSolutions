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
        System.out.println("This problem has not been solved.");

        File file = new File(Problem.FILEPATH + "problem0096.txt");
        List<String> lines = readLinesFromFile(file);

        int[][][] puzzles = new int[50][9][9];

        for (int i = 0; i < lines.size(); i++) {
            if (i % 10 == 0) {
                continue;
            }
            int puzzleIndex = i / 10;
            int puzzleRow = i % 10;

            for (int j = 0; j < lines.get(i).length(); j++) {
                puzzles[puzzleIndex][puzzleRow - 1][j] = Integer.parseInt(lines.get(i).substring(j, j + 1));
            }
        }

        int puzzleIndex = 0;
        for (int[][] puzzle : puzzles) {
            System.out.println("Puzzle #" + puzzleIndex);

            printPuzzle(puzzle);
            
            String isSolveableStr = (solveSudoku(puzzle) ? "true" : "false");
            System.out.println("Is the puzzle solvable? " + isSolveableStr);

            puzzleIndex++;
        }
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

    private void printPuzzle(int[][] puzzle) {
        for (int[] row : puzzle) {
            for (int num : row) {
                System.out.print(num);
            }
            System.out.println();
        }
    }

    private boolean solveSudoku(int[][] board) {
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
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
        // Row has the unique number
        for (int d = 0; d < board.length; d++) {
            // Check if the number we are trying to place is already present in that row,
            // return false;
            if (board[row][d] == num) {
                return false;
            }
        }

        // Column has the unique number 
        for (int[] boardRow : board) {
            // Check if the number we are trying to place is already present in that column,
            // return false;
            if (boardRow[col] == num) {
                return false;
            }
        }

        // Corresponding square has the unique number
        int sqrt = (int) Math.sqrt(board.length);
        int boxRowStart = row - row % sqrt;
        int boxColStart = col - col % sqrt;

        for (int r = boxRowStart; r < boxRowStart + sqrt; r++) {
            for (int d = boxColStart; d < boxColStart + sqrt; d++) {
                if (board[r][d] == num) {
                    return false;
                }
            }
        }

        // If there is no clash, it's safe
        return true;
    }
}
