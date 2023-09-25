package projecteulersolutions;

import java.io.File;
import java.util.Collections;
import java.util.Date;
import java.util.Scanner;

public class MenuPrinter {

    private final Scanner userIn;

    public MenuPrinter() {
        userIn = new Scanner(System.in);
    }

    /*
    printMainMenu is the primary UI for this application.
    The menu contains options for showing particular solutions,
    listing all solutions, showing project progress, and provides simple
    invalid entry catching
    
    printMainMenu is the only public method in MenuPrinter and is the
    only point of access for external calls. All other methods are called
    through printMainMenu.
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
    printSolutionMenu is a menu function for executing a
    problem solution. This function calls invokeProblemByNumber()
    to execute the particular solution based on the user input.
     */
    private void printSolutionMenu() {
        System.out.print("""
                         Enter the Project Euler Problem #: 
                         > """);
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
        if (existsProblemFile(problemNumber)) { // proceeds if problem solution exists
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
    printProblemList is a print function to display
    all problems which have a solution as a file list.
     */
    private void printProblemList() {
        ProgressWriter pw = new ProgressWriter();
        // list of strings to represent source folder contents:
        String[] pathnames = new File(Problem.FILEPATH).list();

        // print list of valid files
        System.out.println("\n============================");
        for (String pathname : pathnames) {
            // if file is in format of "Problem0000.java"
            if (pathname.matches("Problem\\d{4}.java")) {
                int problemNumber = Integer.parseInt(pathname.substring(7, 11));
                String problemStatus;
                switch (pw.getProblemTypeNum(problemNumber)) {
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

        int[] typeCounts = new int[pw.TYPE.length];
        for (int i = 0; i < typeCounts.length; i++) {
            typeCounts[i] = Collections.frequency(pw.getValues(), pw.TYPE[i]);
        }
        System.out.println("Total Complete: " + typeCounts[0]
                + "\nTotal In Progress: " + typeCounts[1]
                + "\nTotal Broken: " + typeCounts[2]);
    }

    /*
    printProgressMenu is a menu function that prints its options and,
    based on a user input, executes a function related to project progress.
    This includes printing progress values, viewing/editing the status of
    a problem status, and regenerating progress values.
     */
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

    /*
    viewStatus is a menu function that prints the status of a problem
    number from user input and gives the option to edit the status of
    that problem.
     */
    private void viewStatus(ProgressWriter pw) {
        System.out.print("""
                         ============================
                         Enter the problem number: 
                         > """);
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

    /*
    editStatus is a menu function that updates the status of the given
    problem number and sets it to a status from user input.
     */
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
                    pw.setProblemStatus(problemNumber, progressType);
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

    /*
    regenerateProgress will, upon user confirmation, regenerate all
    progress values for the project. This process sets all nonbroken
    problems to incomplete, then generates in progress and complete
    problems based on the existence of the problem file and the return
    of Problem0000.isSolved() respectively.
     */
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
    getUserChar is a scanner function to return the next char input
    from the user. This function prints a simple user menu with options
    and gets the next input from user, sanitizes it, and returns it.
     */
    private char getUserChar() {
        return userIn.next().toLowerCase().charAt(0);
    }

    /*
    printMainMenuOptions is a print function to display the options for
    the main menu, including solving a particular problem, listing all
    available problem solutions, and viewing project progress.
     */
    private void printMainMenuOptions() {
        System.out.print("""
                         
                         -------------------------------------
                         S: Solve problem by number
                         L: Problem List
                         V: View Progress
                         Q: Quit
                         -------------------------------------
                         > """);
    }

    /*
    printProgressMenuOptions is a print function to display the options
    for the progress menu, including listing all progress values,
    viewing/editing problem status, and regenerating project progress.
     */
    private void printProgressMenuOptions() {
        System.out.print("""
                         
                         -------------------------------------
                         L: List progress.
                         V: View problem status.
                         R: Regenerate all progress.
                         Q: Return to main menu.
                         -------------------------------------
                         > """);
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
        System.out.print("""
                         
                         -------------------------------------
                         Select a progress value:
                         1: In progress.
                         2: Broken.
                         3: Incomplete.
                         0: Escape
                         -------------------------------------
                         > """);
    }

    /*
    existsProblemFile(int) is a flag that indicates existence of
    a given problem number's solution file.
    
    The requested problem number is sent as an integer and if the
    given problem's solution exists return true.
     */
    public static boolean existsProblemFile(int problemNumber) {
        boolean existsFile = new File(Problem.FILEPATH + Problem.getFileName(problemNumber)).exists();
        System.out.print("""
                           
                           ============================
                           Loading """
                + Problem.getFileName(problemNumber)
                + (existsFile ? "\nSuccess" : "\nFailed: File does not exist.")
                + """
                ============================
                """
        );
        return existsFile; // returns existence of file as flag
    }
}
