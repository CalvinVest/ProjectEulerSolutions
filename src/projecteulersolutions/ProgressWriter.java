package projecteulersolutions;

import java.util.ArrayList;

public class ProgressWriter {

    public static final int PROBLEM_COUNT = 855;
    private final ProgressFileWriter pfw;
    private final ArrayList<String> values;

    public final String[] TYPE = {
        /*0*/"COMPLETE",
        /*1*/ "IN_PROGRESS",
        /*2*/ "BROKEN",
        /*3*/ "INCOMPLETE"};

    public ProgressWriter() {
        pfw = new ProgressFileWriter();
        values = pfw.getProgress();
    }

    /*
    setProblemStatus sets the given problemNumber to the given
    status type.
     */
    public void setProblemStatus(int problemNumber, int type) {
        int index = (problemNumber - 1) * 2;
        values.set(index, Integer.toString(problemNumber));
        values.set(index + 1, TYPE[type]);

        pfw.setProgress(values);
    }

    /*
    regenerateValues regenerates all statuses by first setting all
    non-broken problems to incomplete, then generating in progress and
    complete problems by checking for the existence of the problem file
    and state of the Problem0000.isSolved method respectively.
     */
    public void regenerateValues() {
        System.out.println("-------------------------------------");

        // clear values list and reset all statuses to incomplete
        for (int i = 1; i <= PROBLEM_COUNT; i++) {
            values.set(2 * (i - 1), Integer.toString(i));

            if (!isBroken(i)) {
                values.set(2 * (i - 1) + 1, TYPE[3]);
            }
        }
        // all files in the project folder
        String[] pathnames = Problem.getProblemFiles();
        ArrayList<String> problems = new ArrayList<>();
        // set all of the completed or in progress files based on existence of files
        for (String pathname : pathnames) {
            if (pathname.matches("^Problem\\d{4}.java$")) {
                problems.add(pathname.substring(7, 11));
            }
        }

        problems.forEach(problem -> {
            int problemNumber = Integer.parseInt(problem);
            int index = 2 * (problemNumber - 1);
            boolean isSolved = (boolean) new JavaClassLoader().invokeClassMethod("projecteulersolutions.Problem" + problem, "isSolved");
            int type;

            if (isSolved) {
                System.out.println(problem + " is solved.");
                type = 0;
            } else if (isBroken(problemNumber)) {
                System.out.println(problem + " is broken.");
                type = 2;
            } else {
                System.out.println(problem + " is in progress.");
                type = 1;
            }
            values.set(index + 1, TYPE[type]);
        });

        System.out.println("-------------------------------------");
        pfw.setProgress(values);
    }

    /*
    isBroken returns if a given problem number's status is set to broken.
     */
    private boolean isBroken(int problemNumber) {
        return values.get(2 * (problemNumber - 1) + 1).trim().equalsIgnoreCase(TYPE[2]);
    }

    /*
    getValues returns the arraylist of problem numbers and their statuses
    as the arraylist values.
     */
    public ArrayList<String> getValues() {
        return values;
    }

    /*
    getProblemStatus returns the status of a given problem number.
     */
    public String getProblemStatus(int problemNumber) {
        int progressIndex = (problemNumber - 1) * 2;
        return values.get(progressIndex + 1);
    }

    /*
    getProblemTypeNum returns the status of a given problem number as
    its index in TYPE[].
     */
    public int getProblemTypeNum(int problemNumber) {
        return switch (getProblemStatus(problemNumber)) {
            case "COMPLETE" ->
                0;
            case "IN_PROGRESS" ->
                1;
            case "BROKEN" ->
                2;
            default ->
                3;
        };
    }

    /*
    printValues prints the problem number and status of all available
    problem files.
     */
    public void printValues() {
        for (int i = 0; i < values.size(); i += 2) {
            System.out.println(values.get(i) + " | " + values.get(i + 1));
        }
    }
}
