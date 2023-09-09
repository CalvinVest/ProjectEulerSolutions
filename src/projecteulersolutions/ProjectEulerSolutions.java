package projecteulersolutions;

import java.io.File;

public class ProjectEulerSolutions {

    public static void main(String[] args) {

    }
    
    public static void invokeProblemByNumber(int problemNumber) {
        String problemNumberText = String.format("%04d", problemNumber);
        JavaClassLoader jcl = new JavaClassLoader();
        
        System.out.println("Problem " + problemNumber + ": ");
        jcl.invokeClassMethod("projecteulersolutions.Problem" + problemNumberText, "printAnswer");
    }
    
    public static boolean ifProblemExists(int problemNumber) {
        String problemNumberText = String.format("%04d", problemNumber);
        File file = new File(
            System.getProperty("user.dir")
            + "\\src\\projecteulersolutions\\"
            + "Problem" + problemNumberText + ".java");
        System.out.println(file.toString());
        return file.exists();
    }
}
