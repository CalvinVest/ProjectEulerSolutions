package projecteulersolutions;

import projecteulersolutions.problems.Problem;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Scanner;

public class EulerPrinter {

    private static final int MENU_WIDTH = 47;

    private static final String TOP_BORDER = "╔" + "═".repeat(MENU_WIDTH - 1) + "╗\n";
    private static final String MID_BORDER = "╠" + "═".repeat(MENU_WIDTH - 1) + "╣\n";
    private static final String BOT_BORDER = "╚" + "═".repeat(MENU_WIDTH - 1) + "╝\n";

    private final Scanner userIn;

    public EulerPrinter() {
        userIn = new Scanner(System.in);
    }

    /*
    printMainMenu is the primary UI for this application.
    The menu contains options for showing particular solutions,
    listing all solutions, showing project progress, and provides simple
    invalid entry catching
    
    printMainMenu is the only public method in EulerPrinter and is the
    only point of access for external calls. All other methods are called
    through printMainMenu.
     */
    public void printMainMenu() {
        char userChoice;
        do {
            printMainMenuOptions();

            userChoice = userIn.nextLine().toLowerCase().charAt(0);
            System.out.println();

            switch (userChoice) {
                case 's' ->
                    printSolveProblem();
                case 'v' ->
                    printProgressMenu();
                case 'q' ->
                    System.out.println("Thank you!");
                default ->
                    System.out.println("Invalid entry, please try again.");
            }
        } while (userChoice != 'q');
    }

    /*
    printSolutionMenu is a menu function for executing a
    problem solution. This function calls invokeProblemByNumber()
    to execute the particular solution based on the user input.
     */
    private void printSolveProblem() {
        String header = "Solve Problem";
        var textBlock = new ArrayList<String>();
        textBlock.add("Enter the problem number to solve.");
        EulerConsole.printHeaderAndBlock(header, textBlock);
        EulerConsole.printCursor();

        int userProblemNumber = userIn.nextInt();
        userIn.nextLine();

        if (userProblemNumber > 0 && userProblemNumber <= EulerWriter.PROBLEM_COUNT) {
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
        try {
            if (existsProblemFile(problemNumber)) { // proceeds if problem solution exists
                System.out.println("Problem " + problemNumber);
                Thread.sleep(500);

                System.out.println("Calculating...");
                Thread.sleep(500);

                Date dStart = new Date();
                jcl.invokeClassMethod("projecteulersolutions.problems.Problem" + problemNumberText, "printSolution");
                Date dEnd = new Date();
                long durationMS = dEnd.getTime() - dStart.getTime();

                Thread.sleep(500);
                System.out.println("The problem took " + durationMS / 1000
                        + " seconds (" + durationMS + "ms) to complete.");
                Thread.sleep(500);
            } else {
                System.out.println("Problem solution does not exist.");
            }

        } catch (InterruptedException ex) {
            System.out.println(ex.getMessage());
        }

        System.out.println("\nPress Enter to continue...");
        userIn.nextLine();
    }

    /*
    printProblemList is a print function to display
    all problems which have a solution as a file list.
     */
    private void printProblemList() {
        EulerWriter writer = new EulerWriter();
        // list of strings to represent source folder contents:
        String[] pathnames = new File(Problem.PROBLEM_FILEPATH).list();

        var problemStrs = new ArrayList<String>();

        // list of valid files
        for (String pathname : pathnames) {
            // if file is in format of "Problem0000.java"
            if (pathname.matches("Problem\\d{4}.java")) {
                int problemNumber = Integer.parseInt(pathname.substring(7, 11));
                String problemStatus;
                switch (writer.getProblemStatusNum(problemNumber)) {
                    case 0 ->
                        problemStatus = "Complete";
                    case 1 ->
                        problemStatus = "In Progress";
                    default ->
                        problemStatus = "Incomplete";
                }
                problemStrs.add("Problem " + problemNumber + " - " + problemStatus);
            }
        }

        int[] problemStatusCounts = new int[writer.STATUS.length];
        for (int i = 0; i < problemStatusCounts.length; i++) {
            problemStatusCounts[i] = Collections.frequency(writer.getValues(), writer.STATUS[i]);
        }
        var footerStrs = new ArrayList<String>();
        footerStrs.add("Total Complete: " + problemStatusCounts[0]);
        footerStrs.add("\nTotal In Progress: " + problemStatusCounts[1]);

        EulerConsole.printHeaderAndTwoBlocks("All Problems", problemStrs, footerStrs);
        System.out.println("\nPress Enter to continue...");
        userIn.nextLine();
    }

    /*
    printProgressMenu is a menu function that prints its options and,
    based on a user input, executes a function related to project progress.
    This includes printing progress values, viewing/editing the status of
    a problem status, and regenerating progress values.
     */
    private void printProgressMenu() {
        char userChoice;
        EulerWriter writer = new EulerWriter();

        System.out.println("To edit or view project progress values, select an option:");
        do {
            /*
            Options:
            L: List all problem progress statuses.
            V: View status of particular problem.
            R: Regenerate all problem progress values.
            Q: Return to main menu.
             */
            printProgressMenuOptions();

            userChoice = userIn.nextLine().toLowerCase().charAt(0);
            switch (userChoice) {
                case 'v' ->
                    printProblemStatus(writer);
                case 'l' ->
                        printProblemList();
                case 'r' ->
                    printRegenProgress(writer);
                case 'q' ->
                    System.out.println("Returning to Main Menu");
                default ->
                    System.out.println("Invalid entry, please try again");
            }
        } while (userChoice != 'q');
    }

    /*
    viewStatus is a menu function that prints the status of a problem
    number from user input and gives the option to edit the status of
    that problem.
     */
    private void printProblemStatus(EulerWriter writer) {
        String header = "View Problem Status";
        var textBlock = new ArrayList<String>();
        textBlock.add("Enter the problem number to view.");
        EulerConsole.printHeaderAndBlock(header, textBlock);
        EulerConsole.printCursor();

        int problemNumber = userIn.nextInt();
        userIn.nextLine();

        String statusHeader = "Problem " + problemNumber;
        var statusBlock = new ArrayList<String>();
        statusBlock.add(Problem.getFileName(problemNumber));
        statusBlock.add("Problem Status: " + writer.getProblemStatus(problemNumber));
        EulerConsole.printHeaderAndBlock(statusHeader, statusBlock);

        System.out.println("\nPress Enter to continue...");
        userIn.nextLine();
    }

    /*
    regenerateProgress will, upon user confirmation, regenerate all
    progress values for the project. This process sets all nonbroken
    problems to incomplete, then generates in progress and complete
    problems based on the existence of the problem file and the return
    of Problem0000.isSolved() respectively.
     */
    private void printRegenProgress(EulerWriter writer) {
        System.out.print("Confirm regenerate? Data will be lost! y/n\n> ");
        char userChoice = userIn.nextLine().toLowerCase().charAt(0);
        switch (userChoice) {
            case 'y' -> {
                writer.regenerateValues();
                System.out.println("Success: All progress has been defaulted.");
            }
            case 'n' ->
                System.out.println("Aborted: Operation cancelled by user.");
            default ->
                System.out.println("Failed: Invalid entry.");
        }
    }

    /*
    printMainMenuOptions is a print function to display the options for
    the main menu, including solving a particular problem, listing all
    available problem solutions, and viewing project progress.
     */
    private void printMainMenuOptions() {
        String header = "Welcome! Please choose an option.";
        var textBlock = new ArrayList<String>();
        textBlock.add("S: Solve Problem");
        textBlock.add("V: View Progress.");
        textBlock.add("Q: Quit.");
        EulerConsole.printHeaderAndBlock(header, textBlock);
        EulerConsole.printCursor();
    }

    /*
    printProgressMenuOptions is a print function to display the options
    for the progress menu, including listing all progress values,
    viewing/editing problem status, and regenerating project progress.
     */
    private void printProgressMenuOptions() {
        String header = "View Problem Status";
        var textBlock = new ArrayList<String>();
        textBlock.add("V: View Individual Problem Status");
        textBlock.add("L: List all Problems");
        textBlock.add("R: Regenerate Progress");
        textBlock.add("Q: Quit to Main Menu");
        EulerConsole.printHeaderAndBlock(header, textBlock);
        EulerConsole.printCursor();
    }

    /*
    printEditMenuOptions is a print function that displays the options
    for the edit progress menu, including a list of all potential
    problem statuses, as well as an escape option.
    
    The final option is specifically "Escape" because in one
    implementation it is a "cancel" function and in another it is "confirm"
    Escape was the most generic verbiage for this mixed purpose.
     */
    private void printEditMenuOptions() {
        String header = "Select a Progress Value";
        var textBlock = new ArrayList<String>();
        textBlock.add("1: In progress");
        textBlock.add("3: Incomplete");
        textBlock.add("0: Escape");
        EulerConsole.printHeaderAndBlock(header, textBlock);
        EulerConsole.printCursor();
    }

    /*
    existsProblemFile(int) is a flag that indicates existence of
    a given problem number's solution file.
    
    The requested problem number is sent as an integer and if the
    given problem's solution exists return true.
     */
    public static boolean existsProblemFile(int problemNumber) {
        boolean existsFile = new File(Problem.PROBLEM_FILEPATH + Problem.getFileName(problemNumber)).exists();

        var textBlock = new ArrayList<String>();
        textBlock.add("Loading " + Problem.getFileName(problemNumber));
        textBlock.add((existsFile ? "Success" : "Failed: File does not exist."));
        EulerConsole.printHeaderAndBlock("Problem " + problemNumber, textBlock);

        return existsFile; // returns existence of file as flag
    }
}
