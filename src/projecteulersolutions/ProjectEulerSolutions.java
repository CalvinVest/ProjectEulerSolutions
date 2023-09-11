package projecteulersolutions;

import java.io.File; // Used to verify the existence of a given problem0000.java
import java.util.Scanner; // Used for user input in main menu

public class ProjectEulerSolutions {

    public static void main(String[] args) {
        printUserMenu();
    }
    
    public static void printUserMenu() {
        Scanner userIn = new Scanner(System.in);
        System.out.println("Welcome, select an option:");
        char userInChar;
        do {
            System.out.println("Q: Quit");
            System.out.println("S: Solve problem by number");
            System.out.print("> ");
            userInChar = userIn.next().toLowerCase().charAt(0);
            
            switch(userInChar) {
                case 'q' -> {
                }
                case 's' -> printSolutionMenu(userIn);
                default -> System.out.println("Invalid entry, please try again.");
            }
        } while(userInChar != 'q');
        
        System.out.println("Thank you!");
    }
    
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
        if(ifProblemExists(problemNumber)) { // proceeds if problem solution exists
            System.out.println("Problem " + problemNumber + ": ");
            // Problem 0000: _
            jcl.invokeClassMethod("projecteulersolutions.Problem" + problemNumberText, "printAnswer");
            // Problem 0000: The answer to this problem is whatever
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
    public static boolean ifProblemExists(int problemNumber) {
        //Convert problem number sent as integer to convention Problem0000.java
        String problemNumberText = String.format("%04d", problemNumber);
        // File object created with given problem number and convention
        File file = new File(
            System.getProperty("user.dir") // user directory
            + "\\src\\projecteulersolutions\\" // project directory
            + "Problem" + problemNumberText + ".java"); // given problem solution
        System.out.println(file.toString()); // indicates existence of file with tostring
        return file.exists(); // returns existence of file as flag
    }
}
