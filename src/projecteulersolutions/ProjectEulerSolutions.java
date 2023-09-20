package projecteulersolutions;

import java.io.File; // Used to verify the existence of a given problem0000.java
import java.util.Date;
import java.util.Scanner; // Used for user input in main menu

public class ProjectEulerSolutions {

    public static void main(String[] args) {
        printMainMenu();
    }

    /*
    printMainMenu() is the primary UI for this application.
    The menu contains options for showing particular solutions,
    quitting the application, and has catches for invalid inputs.
     */
    public static void printMainMenu() {
        Scanner userIn = new Scanner(System.in);
        System.out.println("Welcome, select an option.");
        char userInChar;
        do {
            System.out.print("\n-------------------------------------"
                    + "\nS: Solve problem by number"
                    + "\nL: Problem List"
                    + "\nR: Update Project Readme"
                    + "\nQ: Quit"
                    + "\n-------------------------------------"
                    + "\n> ");
            userInChar = getUserChar(userIn);

            switch (userInChar) {

                case 's' ->
                    printSolutionMenu(userIn);
                case 'l' ->
                    printProblemList();
                case 'r' ->
                    printReadmeMenu(userIn);
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
    based on the user input obtained in this function.
     */
    public static void printSolutionMenu(Scanner userIn) {
        System.out.println("Enter the Project Euler Problem #: ");
        System.out.print("> ");
        int userProblemNumber = userIn.nextInt();
        if (userProblemNumber > 0 && userProblemNumber < 10000) {
            invokeProblemByNumber(userProblemNumber);
        } else {
            System.out.println("The number is not a valid problem number.");
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
    public static void invokeProblemByNumber(int problemNumber) {
        String problemNumberText = String.format("%04d", problemNumber);
        //Initiates new classloader to invoke the problem itself
        JavaClassLoader jcl = new JavaClassLoader();
        if (existsProblem(problemNumber)) { // proceeds if problem solution exists
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
    existsProblem(int) is a flag that indicates existence of
    a given problem number's solution.
    
    The requested problem number is sent as an integer and if the
    given problem's solution exists return true.
     */
    private static boolean existsProblem(int problemNumber) {
        // File object created with given problem number and convention
        boolean existsFile = new File(Problem.FILEPATH + Problem.getFileName(problemNumber)).exists();
        System.out.println("\n============================\n"
                + "Loading " + Problem.getFileName(problemNumber) + "\n"
                + (existsFile ? "Success\n" : "Failed: File does not exist.\n")
                + "============================");
        return existsFile; // returns existence of file as flag
    }

    /*
    printProblemList() is a print function to display
    all problems which have a solution as a file list.
     */
    public static void printProblemList() {
        // list of strings to represent source folder contents:
        String[] pathnames = new File(Problem.FILEPATH).list();

        // print list of valid files
        System.out.println("\n============================");
        for (String pathname : pathnames) {
            // if file is in format of "Problem0000.java"
            if (pathname.matches("Problem\\d\\d\\d\\d\\.java")) {
                int problemNumber = Integer.parseInt(pathname.substring(7, 11));
                String problemStatus = new ProgressWriter().getProblemStatus(problemNumber);
                System.out.println(pathname + " - " + problemStatus);
            }
        }
        System.out.println("============================");
    }

    private static void printReadmeMenu(Scanner userIn) {
        char userChoice;
        ProgressWriter pw = new ProgressWriter();

        System.out.println("\nTo edit or view project progress values, select an option:");
        do {
            System.out.print("\n-------------------------------------"
                    + "\nL: List progress."
                    + "\nE: Edit problem status."
                    + "\nR: Regenerate all progress."
                    + "\nQ: Return to main menu."
                    + "\n-------------------------------------"
                    + "\n> ");
            userChoice = getUserChar(userIn);

            switch (userChoice) {
                case 'l' ->
                    pw.printValues();
                case 'e' ->
                    printReadmeEditMenu(userIn, pw);
                case 'r' ->
                    printReadmeGenerateMenu(userIn, pw);
                case 'q' ->
                    System.out.println("Returning to Main Menu");
                default ->
                    System.out.println("Invalid entry, please try again");
            }
        } while (userChoice != 'q');
    }

    private static void printReadmeEditMenu(Scanner userIn, ProgressWriter ppp) {
        System.out.print("============================\n"
                + "Enter the problem number: \n> ");
        int problemNumber = userIn.nextInt();
        String filename = Problem.getFileName(problemNumber);
        System.out.println(filename);
        String status = ppp.getProblemStatus(problemNumber);
        System.out.println("Problem Status: " + status);

        System.out.print("\nEdit Status? y/n:\n> ");
        char userEditChoice = getUserChar(userIn);

        switch (userEditChoice) {
            case 'y' -> {

                int userProgressType = getUserEditChoice(userIn);

                if (userProgressType <= ppp.TYPE.length) {
                    System.out.print("Updating status to " + ppp.TYPE[userProgressType] + "\nConfirm? y/n:\n> ");
                    char userEditConfirm = getUserChar(userIn);
                    switch (userEditConfirm) {
                        case 'y' -> {
                            ppp.setProblemStatus(problemNumber, ppp.TYPE[userProgressType]);
                            System.out.println("Success: Status updated.");
                        }
                        case 'n' ->
                            System.out.println("Aborted: Operation cancelled by user.");
                        default ->
                            System.out.println("Failed: Invalid entry.");
                    }
                } else {
                    System.out.println("Failed: Value " + userProgressType + " is out of bounds.");
                }
            }
            case 'n' ->
                System.out.println("Success: Returning to progress menu.");
            default ->
                System.out.println("Failed: Invalid entry.");
        }
    }

    private static void printReadmeGenerateMenu(Scanner userIn, ProgressWriter pw) {
        System.out.print("Confirm overwrite? Data will be lost! y/n\n> ");
        char userConfirmChar = getUserChar(userIn);
        switch (userConfirmChar) {
            case 'y' -> {
                pw.regenerateValues();
                System.out.println("Success: All progress has been defaulted.");
                printReadmeGenerateEditMenu(userIn, pw);
            }
            case 'n' ->
                System.out.println("Aborted: Operation cancelled by user.");
            default ->
                System.out.println("Failed: Invalid entry.");
        }
    }

    private static void printReadmeGenerateEditMenu(Scanner userIn, ProgressWriter pw) {
        System.out.println("Would you like to add edited values to the newly generated list? y/n\n> ");
        char confirmChar = getUserChar(userIn);
        switch (confirmChar) {
            case 'y' -> {
                int status;
                do {
                    System.out.print("-------------------------------------"
                            + "\nSelect a progress value:"
                            + "\n1: Complete, but not on GitHub."
                            + "\n2: In progress."
                            + "\n3: Broken."
                            + "\n0: Finish"
                            + "\n-------------------------------------"
                            + "\n> ");
                    status = userIn.nextInt();

                    if (status > 0 && status <= 4) {
                        userIn.nextLine();
                        System.out.println("Status: " + pw.TYPE[status]);
                        System.out.print("Enter the problems, separated with a comma:\n> ");
                        String problemsString = userIn.nextLine();
                        pw.setStatusFromString(problemsString, pw.TYPE[status]);
                    }
                } while (status != 0);
            }
            case 'n' ->
                System.out.println("No edits made to regenerated progress values.");
            default ->
                System.out.println("Failed: Invalid entry.");
        }
    }

    private static int getUserEditChoice(Scanner userIn) {
        System.out.println("-------------------------------------"
                + "\nSelect a progress value:"
                + "\n1: Complete, but not on GitHub."
                + "\n2: In progress."
                + "\n3: Broken."
                + "\n4: Incomplete."
                + "\n-------------------------------------");
        return userIn.nextInt();
    }

    /*
    getNextUserChar(Scanner) is a scanner function to return
    the next char input from the user.
    This function prints a simple user menu with options
    and gets the next input from user, sanitizes it, and returns it.
     */
    private static char getUserChar(Scanner userIn) {
        return userIn.next().toLowerCase().charAt(0);
    }
}
