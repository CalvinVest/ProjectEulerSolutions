package projecteulersolutions;

import projecteulersolutions.problems.Problem;

import java.io.File;
import java.util.*;

public class EulerPrinter {

    private final Scanner userIn;

    public EulerPrinter() {
        userIn = new Scanner(System.in);
    }

    public void menuHome() {
        char userChoice;
        do {
            try {
                String welcomeHeader = "Welcome! Please choose an option.";
                var welcomeBlock = new ArrayList<String>();
                welcomeBlock.add("S: Solve Problem");
                welcomeBlock.add("V: View Progress.");
                welcomeBlock.add("Q: Quit.");
                EulerConsole.printHeaderAndBlock(welcomeHeader, welcomeBlock);
                EulerConsole.printCursor();

                userChoice = userIn.nextLine().toLowerCase().charAt(0);
                EulerConsole.println("");

                switch (userChoice) {
                    case 's' -> menuSolveProblem();
                    case 'v' -> menuProgress();
                    case 'q' -> EulerConsole.println("Thank you!");
                    default -> EulerConsole.println("Invalid entry, please try again.");
                }
            } catch(StringIndexOutOfBoundsException sioobe) {
                EulerConsole.printExceptionMessage(sioobe, "Fatal exception encountered, program will be returned to the main menu.");
                userChoice = ' ';
                EulerConsole.waitForEnterPress(userIn);
            }
        } while (userChoice != 'q');
    }

    private void menuSolveProblem() {
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
            var invalidProblemMessageList = new ArrayList<String>();
            invalidProblemMessageList.add("The number is not a valid problem number.");
            EulerConsole.printHeaderAndBlock("Failure.", invalidProblemMessageList);
        }
    }

    private void menuProgress() {
        char userChoice;
        EulerWriter writer = new EulerWriter();

        do {
            String header = "View Problem Status";
            var textBlock = new ArrayList<String>();
            textBlock.add("V: View Individual Problem Status");
            textBlock.add("L: List all Problems");
            textBlock.add("R: Regenerate Progress");
            textBlock.add("Q: Quit to Main Menu");
            EulerConsole.printHeaderAndBlock(header, textBlock);
            EulerConsole.printCursor();

            userChoice = userIn.nextLine().toLowerCase().charAt(0);
            switch (userChoice) {
                case 'v' -> menuViewProgress(writer);
                case 'l' -> printProgressList();
                case 'r' -> menuRegenProgress(writer);
                case 'q' -> EulerConsole.println("Returning to Main Menu");
                default -> EulerConsole.println("Invalid entry, please try again");
            }
        } while (userChoice != 'q');
    }

    private void menuViewProgress(EulerWriter writer) {
        String header = "View Problem Status";
        var textBlock = new ArrayList<String>();
        textBlock.add("Enter the problem number to view.");
        EulerConsole.printHeaderAndBlock(header, textBlock);
        EulerConsole.printCursor();

        int problemNumber = userIn.nextInt();
        userIn.nextLine();

        String statusHeader = "Problem " + problemNumber;
        var statusBlock = new ArrayList<String>();
        statusBlock.add(EulerUtils.getProblemFileName(problemNumber));
        statusBlock.add("Problem Status: " + writer.getProblemStatus(problemNumber));
        EulerConsole.printHeaderAndBlock(statusHeader, statusBlock);

        EulerConsole.waitForEnterPress(userIn);
    }

    private void printProgressList() {
        EulerWriter writer = new EulerWriter();
        // list of strings to represent source folder contents:
        String[] pathnames = new File(EulerUtils.PROBLEM_FILEPATH).list();

        var problemStrs = new ArrayList<String>();
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

        var footerStrs = new ArrayList<String>();
        footerStrs.add("Total Complete: " + Collections.frequency(writer.getValues(), writer.STATUS[0]));
        footerStrs.add("\nTotal In Progress: " + Collections.frequency(writer.getValues(), writer.STATUS[1]));

        EulerConsole.printHeaderAndTwoBlocks("All Problems", problemStrs, footerStrs);

        EulerConsole.waitForEnterPress(userIn);
    }

    private void menuRegenProgress(EulerWriter writer) {
        String regenHeader = "Regenerate Values";
        var regenBlock = new ArrayList<String>();
        regenBlock.add("Would you like to generate all values?");
        regenBlock.add("WARNING: Data may be lost.");
        regenBlock.add("Problem statuses should be regenerated any time a problem is completed, or new problems are added to be solved.");
        regenBlock.add("");
        regenBlock.add("To confirm regeneration, type \"regen\" below.");
        EulerConsole.printHeaderAndBlock(regenHeader, regenBlock);
        EulerConsole.printCursor();

        String userChoice = userIn.nextLine().toLowerCase();
        var outcomeBlock = new ArrayList<String>();
        if (userChoice.equals("regen")) {
            writer.regenerateValues();
            outcomeBlock.add("Success: All progress has been defaulted.");
        } else {
            outcomeBlock.add("Failed: Regeneration aborted.");
        }

        EulerConsole.printHeaderAndBlock("Result", outcomeBlock);
        EulerConsole.waitForEnterPress(userIn);
    }

    /*
    invokeProblemByNumber takes the problem number as an int and executes that Project
    Euler problem if the solution file exists.

    Checks with existsProblemFile(problemNumber) that the problem file exists, then uses
    JavaClassLoader to invoke the solution getter method with reflection.
     */
    private void invokeProblemByNumber(int problemNumber) {
        String problemNumberText = String.format("%04d", problemNumber);
        //Initiates new classloader to invoke the problem itself
        JavaClassLoader jcl = new JavaClassLoader();
        try {
            if (existsProblemFile(problemNumber)) { // proceeds if problem solution exists
                EulerConsole.println("Calculating...");

                Thread.sleep(500);

                Date dStart = new Date();
                jcl.invokeClassMethod("projecteulersolutions.problems.Problem" + problemNumberText, "printSolution");
                Date dEnd = new Date();
                long durationMS = dEnd.getTime() - dStart.getTime();

                EulerConsole.println("The problem took " + durationMS / 1000
                        + " seconds (" + durationMS + "ms) to complete.");
            } else {
                var dneMessageList = new ArrayList<String>();
                dneMessageList.add("Problem solution does not exist.");
                EulerConsole.printHeaderAndBlock("Problem File Missing!", dneMessageList);
            }

        } catch (InterruptedException ex) {
            EulerConsole.printExceptionMessage(ex, "Interrupted!");
        }

        EulerConsole.waitForEnterPress(userIn);
    }

    public static boolean existsProblemFile(int problemNumber) {
        boolean existsFile = new File(EulerUtils.PROBLEM_FILEPATH + EulerUtils.getProblemFileName(problemNumber)).exists();

        var textBlock = new ArrayList<String>();
        textBlock.add("Loading " + EulerUtils.getProblemFileName(problemNumber) + "...");
        textBlock.add((existsFile ? "Success: File loaded." : "Failed: File does not exist."));
        EulerConsole.printHeaderAndBlock("Problem " + problemNumber, textBlock);

        return existsFile; // returns existence of file as flag
    }
}
