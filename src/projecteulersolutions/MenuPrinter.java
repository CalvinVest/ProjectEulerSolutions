package projecteulersolutions;

import java.io.File;
import java.util.Date;
import java.util.Scanner;

public class MenuPrinter {

    private final Scanner userIn;

    public MenuPrinter() {
        userIn = new Scanner(System.in);
    }

    /*
    printMainMenu() is the primary UI for this application.
    The menu contains options for showing particular solutions,
    listing all solutions, showing project progress, and provides simple
    invalid entry catching
     */
    public void printMainMenu() {
        System.out.println("Welcome, select an option.");

        char userInChar;
        do {
            printMainMenuOptions();

            userInChar = getUserChar();

            switch (userInChar) {
                case 's' ->
                    printSolutionMenu();
                case 'l' ->
                    printProblemList();
                case 'v' ->
                    printProgressMenu();
                case 'q' ->
                    System.out.println("Thank you!");
                default ->
                    System.out.println("Invalid entry, please try again.");
            }
        } while (userInChar != 'q');
    }

    /*
    printSolutionMenu(Scanner) prints the UI for executing a problem solution.
    this function calls invokeProblemByNumber() to execute the particular solution
    based on the user input obtained within this function.
     */
    private void printSolutionMenu() {
        System.out.print("Enter the Project Euler Problem #: "
                + "\n> ");
        int userProblemNumber = userIn.nextInt();
        if (userProblemNumber > 0 && userProblemNumber <= ProgressWriter.PROBLEM_COUNT) {
            invokeProblemByNumber(userProblemNumber);
        } else {
            System.out.println("Failed: The number is not a valid problem number.");
        }
    }

    /*
    invokeProblemByNumber executes the particular problem
    requested by the user by taking the problem number as an input
    and invokes the printSolution method of the particular problem.
    
    The existence of the particular class is verified by ifProblemExists(int)
    and all exceptions related to the classloader are handled within
    JavaClassLoader.
     */
    private void invokeProblemByNumber(int problemNumber) {
        String problemNumberText = String.format("%04d", problemNumber);
        //Initiates new classloader to invoke the problem itself
        JavaClassLoader jcl = new JavaClassLoader();
        if (ProgressWriter.existsProblemFile(problemNumber)) { // proceeds if problem solution exists
            Date dStart = new Date();
            System.out.println("Problem " + problemNumber + ": ");
            // Problem 0: _
            jcl.invokeClassMethod("projecteulersolutions.Problem" + problemNumberText, "printSolution");
            // Problem 0: The solution to this problem is whatever
            Date dEnd = new Date();
            long durationMS = dEnd.getTime() - dStart.getTime();
            System.out.println("The problem took " + durationMS / 1000
                    + " seconds (" + durationMS + "ms) to complete.");
        } else {
            System.out.println("Problem solution does not exist.");
        }
    }

    /*
    printProblemList() is a print function to display
    all problems which have a solution as a file list.
     */
    private void printProblemList() {
        // list of strings to represent source folder contents:
        String[] pathnames = new File(Problem.FILEPATH).list();

        // print list of valid files
        System.out.println("\n============================");
        for (String pathname : pathnames) {
            // if file is in format of "Problem0000.java"
            if (pathname.matches("Problem\\d{4}.java")) {
                int problemNumber = Integer.parseInt(pathname.substring(7, 11));
                String problemStatus;
                switch (new ProgressWriter().getProblemTypeNum(problemNumber)) {
                    case 0 ->
                        problemStatus = "Complete";
                    case 1 ->
                        problemStatus = "In Progress";
                    case 2 ->
                        problemStatus = "Broken";
                    default ->
                        problemStatus = "Incomplete";
                }
                System.out.println("Problem " + problemNumber + " - " + problemStatus);
            }
        }
        System.out.println("============================");
    }

    private void printProgressMenu() {
        char userChoice;
        ProgressWriter pw = new ProgressWriter();

        System.out.println("\nTo edit or view project progress values, select an option:");
        do {
            printProgressMenuOptions();

            userChoice = getUserChar();
            switch (userChoice) {
                case 'l' ->
                    pw.printValues();
                case 'v' ->
                    viewStatus(pw);
                case 'r' ->
                    regenerateProgress(pw);
                case 'q' ->
                    System.out.println("Returning to Main Menu");
                default ->
                    System.out.println("Invalid entry, please try again");
            }
        } while (userChoice != 'q');
    }

    private void viewStatus(ProgressWriter pw) {
        System.out.print("============================\n"
                + "Enter the problem number: \n> ");
        int problemNumber = userIn.nextInt();
        System.out.print(Problem.getFileName(problemNumber)
                + "\nProblem Status: " + pw.getProblemStatus(problemNumber)
                + "\nEdit Status? y/n:"
                + "\n> ");

        switch (getUserChar()) {
            case 'y' ->
                editStatus(pw, problemNumber);
            case 'n' ->
                System.out.println("Success: Returning to progress menu.");
            default ->
                System.out.println("Failed: Invalid entry.");
        }
    }

    private void editStatus(ProgressWriter pw, int problemNumber) {
        printEditMenuOptions();
        int progressType = userIn.nextInt();

        if (progressType == 0) {
            return;
        }

        if (progressType < pw.TYPE.length) {
            System.out.print("Updating status to " + pw.TYPE[progressType]
                    + "\nConfirm? y/n:"
                    + "\n> ");
            switch (getUserChar()) {
                case 'y' -> {
                    pw.setProblemStatus(problemNumber, pw.TYPE[progressType]);
                    System.out.println("Success: Status updated.");
                }
                case 'n' ->
                    System.out.println("Aborted: Operation cancelled by user.");
                default ->
                    System.out.println("Failed: Invalid entry.");
            }
        } else {
            System.out.println("Failed: Value " + progressType + " is out of bounds.");
        }
    }

    private void regenerateProgress(ProgressWriter pw) {
        System.out.print("Confirm regenerate? Data will be lost! y/n\n> ");
        switch (getUserChar()) {
            case 'y' -> {
                pw.regenerateValues();
                System.out.println("Success: All progress has been defaulted.");
            }
            case 'n' ->
                System.out.println("Aborted: Operation cancelled by user.");
            default ->
                System.out.println("Failed: Invalid entry.");
        }
    }

    /*
    getUserChar() is a scanner function to return
    the next char input from the user.
    This function prints a simple user menu with options
    and gets the next input from user, sanitizes it, and returns it.
     */
    private char getUserChar() {
        return userIn.next().toLowerCase().charAt(0);
    }

    private void printMainMenuOptions() {
        System.out.print("\n-------------------------------------"
                + "\nS: Solve problem by number"
                + "\nL: Problem List"
                + "\nV: View Progress"
                + "\nQ: Quit"
                + "\n-------------------------------------"
                + "\n> ");
    }

    private void printProgressMenuOptions() {
        System.out.print("-------------------------------------"
                + "\nL: List progress."
                + "\nV: View problem status."
                + "\nR: Regenerate all progress."
                + "\nQ: Return to main menu."
                + "\n-------------------------------------"
                + "\n> ");
    }

    private void printEditMenuOptions() {
        System.out.print("-------------------------------------"
                + "\nSelect a progress value:"
                + "\n1: In progress."
                + "\n2: Broken."
                + "\n3: Incomplete."
                + "\n0: Escape"
                + "\n-------------------------------------"
                + "\n> ");
    }
}
