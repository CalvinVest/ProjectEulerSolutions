package projecteulersolutions;

import java.io.File;

public class ProjectEulerSolutions {

    public static void main(String[] args) {

    }
    
    public static void invokeProblemByNumber(int problemNumber) {
        String problemNumberText = String.format("%04d", problemNumber);
        JavaClassLoader jcl = new JavaClassLoader();
        if(ifProblemExists(problemNumber)) {
            System.out.println("Problem " + problemNumber + ": ");
            jcl.invokeClassMethod("projecteulersolutions.Problem" + problemNumberText, "printAnswer");
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
