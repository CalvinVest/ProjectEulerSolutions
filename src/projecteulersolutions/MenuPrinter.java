package projecteulersolutions;

import java.io.File;
import java.util.Date;
import java.util.Scanner;

public class MenuPrinter {

    private Scanner userIn;

    public MenuPrinter() {
        userIn = new Scanner(System.in);
    }

    public void printMainMenu() {
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

            userInChar = getUserChar();

            switch (userInChar) {
                case 's' ->
                    printSolutionMenu();
                case 'l' ->
                    printProblemList();
                case 'r' ->
                    printReadmeMenu();
                case 'q' ->
                    System.out.println("Thank you!");
                default ->
                    System.out.println("Invalid entry, please try again.");
            }
        } while (userInChar != 'q');
    }

    private void printSolutionMenu() {
        System.out.print("Enter the Project Euler Problem #: "
                + "\n> ");
        int userProblemNumber = userIn.nextInt();
        if (userProblemNumber > 0 && userProblemNumber < 10000) {
            invokeProblemByNumber(userProblemNumber);
        } else {
            System.out.println("The number is not a valid problem number.");
        }
    }

    private void invokeProblemByNumber(int problemNumber) {
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

    private boolean existsProblem(int problemNumber) {
        // File object created with given problem number and convention
        boolean existsFile = new File(Problem.FILEPATH + Problem.getFileName(problemNumber)).exists();
        System.out.println("\n============================"
                + "\nLoading " + Problem.getFileName(problemNumber)
                + (existsFile ? "\nSuccess" : "\nFailed: File does not exist.")
                + "\n============================");
        return existsFile; // returns existence of file as flag
    }

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

    private void printReadmeMenu() {
        char userChoice;
        ProgressWriter pw = new ProgressWriter();

        System.out.println("\nTo edit or view project progress values, select an option:");
        do {
            System.out.print("-------------------------------------"
                    + "\nL: List progress."
                    + "\nE: Edit problem status."
                    + "\nR: Regenerate all progress."
                    + "\nQ: Return to main menu."
                    + "\n-------------------------------------"
                    + "\n> ");
            userChoice = getUserChar();

            switch (userChoice) {
                case 'l' ->
                    pw.printValues();
                case 'e' ->
                    printReadmeEditMenu(pw);
                case 'r' ->
                    printReadmeGenerateMenu(pw);
                case 'q' ->
                    System.out.println("Returning to Main Menu");
                default ->
                    System.out.println("Invalid entry, please try again");
            }
        } while (userChoice != 'q');
    }

    private void printReadmeEditMenu(ProgressWriter pw) {
        System.out.print("============================\n"
                + "Enter the problem number: \n> ");
        int problemNumber = userIn.nextInt();
        String filename = Problem.getFileName(problemNumber);
        String status = pw.getProblemStatus(problemNumber);
        System.out.print(filename
                + "\nProblem Status: " + status
                + "\nEdit Status? y/n:"
                + "\n> ");

        char userEditChoice = getUserChar();
        switch (userEditChoice) {
            case 'y' -> {
                int userProgressType = getUserEditChoice();

                if (userProgressType == 0) {
                    break;
                }

                if (userProgressType <= pw.TYPE.length) {
                    System.out.print("Updating status to " + pw.TYPE[userProgressType]
                            + "\nConfirm? y/n:"
                            + "\n> ");
                    switch (getUserChar()) {
                        case 'y' -> {
                            pw.setProblemStatus(problemNumber, pw.TYPE[userProgressType]);
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

    private void printReadmeGenerateMenu(ProgressWriter pw) {
        System.out.print("Confirm overwrite? Data will be lost! y/n\n> ");
        char userConfirmChar = getUserChar();
        switch (userConfirmChar) {
            case 'y' -> {
                pw.regenerateValues();
                System.out.println("Success: All progress has been defaulted.");
                printReadmeGenerateEditMenu(pw);
            }
            case 'n' ->
                System.out.println("Aborted: Operation cancelled by user.");
            default ->
                System.out.println("Failed: Invalid entry.");
        }
    }

    private void printReadmeGenerateEditMenu(ProgressWriter pw) {
        System.out.print("-------------------------------------"
                + "\nWould you like to add edited values to the newly generated list? y/n"
                + "\n> ");
        char confirmChar = getUserChar();
        switch (confirmChar) {
            case 'y' -> {
                int status;
                do {
                    status = getUserEditChoice();

                    if (status > 0 && status < 4) {
                        userIn.nextLine();
                        System.out.print("Status: " + pw.TYPE[status]
                                + "\nEnter the problems, separated with a comma:"
                                + "\n> ");
                        String problemsString = userIn.nextLine();
                        pw.setStatusFromString(problemsString, pw.TYPE[status]);
                    }
                } while (status != 0);
            }
            case 'n' ->
                System.out.println("Aborted: No edits made to regenerated progress values.");
            default ->
                System.out.println("Failed: Invalid entry.");
        }
    }

    private int getUserEditChoice() {
        System.out.print("-------------------------------------"
                + "\nSelect a progress value:"
                + "\n1: In progress."
                + "\n2: Broken."
                + "\n3: Incomplete."
                + "\n0: Escape"
                + "\n-------------------------------------"
                + "\n> ");
        return userIn.nextInt();
    }

    private char getUserChar() {
        return userIn.next().toLowerCase().charAt(0);
    }
}
