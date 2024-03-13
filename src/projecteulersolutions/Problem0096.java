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
}
