package projecteulersolutions.problems;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Problem0079 extends Problem {

    @Override
    public boolean isSolved() {
        return false;
    }

    @Override
    public void printSolution() {
        System.out.println("This problem has not been solved.");
        File keylogFile = new File(Problem.FILEPATH + "problem0079.txt");
        List<String> keylogs = readFile(keylogFile);
    }

    private List<String> readFile(File file) {
        List<String> vals = new ArrayList<>();
        try {
            Scanner fileIn = new Scanner(file);
            while (fileIn.hasNext()) {
                vals.add(fileIn.next());
            }
        } catch (FileNotFoundException fnfe) {
            System.out.println("Exception encountered: " + file.toString() + " does not exist.");
        }
        return vals;
    }

    private class Graph {

        private int vertices;
        private LinkedList<Integer>[] adjacencyList;

        public Graph(int vertices) {
            this.vertices = vertices;
            adjacencyList = new LinkedList[vertices];
            for (int i = 0; i < vertices; ++i) {
                adjacencyList[i] = new LinkedList();
            }
        }

        // Add an edge to the graph
        public void addEdge(int source, int destination) {
            adjacencyList[source].add(destination);
        }

        // Print the graph
        public void printGraph() {
            for (int i = 0; i < vertices; ++i) {
                System.out.print("Vertex " + i + " is connected to: ");
                for (Integer vertex : adjacencyList[i]) {
                    System.out.print(vertex + " ");
                }
                System.out.println();
            }
        }
    }
}
