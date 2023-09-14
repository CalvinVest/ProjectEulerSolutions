package projecteulersolutions;

import java.io.File; // Used to verify the existence of a given problem0000.java
import java.util.Date;
import java.util.Scanner; // Used for user input in main menu

public class ProjectEulerSolutions {

    public static void main(String[] args) {
        printUserMenu();
    }

    /*
    printUserMenu() is the primary UI for this application.
    The menu contains options for showing particular solutions,
    quitting the application, and has catches for invalid inputs.
     */
    public static void printUserMenu() {
        Scanner userIn = new Scanner(System.in);
        System.out.println("Welcome, select an option.");
        char userInChar;
        do {
            userInChar = getNextUserChar(userIn);

            switch (userInChar) {
                case 'q' ->
                    System.out.println("Thank you!");
                case 's' ->
                    printSolutionMenu(userIn);
                case 'p' ->
                    printProblemSolutionList();
                default ->
                    System.out.println("Invalid entry, please try again.");
            }
        } while (userInChar != 'q');
    }

    /*
    printSolutionMenu(Scanner) prints the UI for executing a problem solution.
    this function calls invokeProblemByNumber() to execute the particular solution
    based on the user input obtained in this function.
     */
    public static void printSolutionMenu(Scanner userIn) {
        System.out.println("Enter the Project Euler Problem #: ");
        System.out.print("> ");
        int userProblemNumber = userIn.nextInt();
        invokeProblemByNumber(userProblemNumber);
    }

    /*
    invokeProblemByNumber executes the particular problem
    requested by the user by taking the problem number as an input
    and invokes the printAnswer method of the particular problem.
    
    The existence of the particular class is verified by ifProblemExists(int)
    and all exceptions related to the classloader are handled within
    JavaClassLoader.
     */
    public static void invokeProblemByNumber(int problemNumber) {
        String problemNumberText = String.format("%04d", problemNumber);
        //Initiates new classloader to invoke the problem itself
        JavaClassLoader jcl = new JavaClassLoader();
        if (existsProblem(problemNumber)) { // proceeds if problem solution exists
            Date dStart = new Date();
            System.out.println("Problem " + problemNumber + ": ");
            // Problem 0: _
            jcl.invokeClassMethod("projecteulersolutions.Problem" + problemNumberText, "printAnswer");
            // Problem 0: The answer to this problem is whatever
            Date dEnd = new Date();
            long durationSec = (dEnd.getTime() - dStart.getTime()) / 1000;
            System.out.println("The problem took " + durationSec + " seconds to complete.");
        } else {
            System.out.println("Problem solution does not exist.");
        }
    }

    /*
    ifProblemExists(int) is a flag that indicates existence of
    a given problem number solution's file.
    Problems are named with the convention Problem0000.java
    
    The requested problem number is sent as an integer and if the
    given problem's solution exists within the naming convention above,
    return true.
     */
    public static boolean existsProblem(int problemNumber) {
        // File object created with given problem number and convention
        try {
            File file = new File(Problem.FILEPATH + Problem.getFileName(problemNumber));
            System.out.println("\n============================");
            System.out.println("Loading " + Problem.getFileName(problemNumber)); // Loading message
            System.out.println(file.exists() ? "Success" : "Failed: File does not exist.");
            System.out.println("============================\n");
            return file.exists(); // returns existence of file as flag
        }
        catch(IllegalArgumentException iae) {
            System.out.println(iae.getMessage());
            return false;
        }

    }

    /*
    printProblemSolutionList() is a print function to display
    all problems which have a solution as a file list.
    All problem solutions are in classes of the format
        Problem0000.java
    and are listed in ascending order in this function.
     */
    public static void printProblemSolutionList() {
        // list of strings to represent source folder contents:
        String[] pathnames = new File(Problem.FILEPATH).list();

        // print list of valid files
        System.out.println("\n============================");
        for (String pathname : pathnames) {
            // if file is in format of "Problem0000.java"
            if (pathname.matches("Problem\\d\\d\\d\\d\\.java")) {
                System.out.println(pathname);
            }
        }
        System.out.println("============================\n");
    }

    /*
    getNextUserChar(Scanner) is a scanner function to return
    the next char input from the user.
    This function prints a simple user menu with options
    and gets the next input from user, sanitizes it, and returns it.
     */
    private static char getNextUserChar(Scanner userIn) {
        System.out.println("\nQ: Quit");
        System.out.println("S: Solve problem by number");
        System.out.println("P: Problem List");
        System.out.print("> ");
        return userIn.next().toLowerCase().charAt(0);
    }
}
