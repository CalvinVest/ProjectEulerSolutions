package projecteulersolutions;

public class ProjectEulerSolutions {

    public static void main(String[] args) {

    }
    
    public static void invokeProblemByNumber(int problemNumber) {
        String problemNumberText = String.format("%04d", problemNumber);
        JavaClassLoader jcl = new JavaClassLoader();
        
        System.out.println("Problem " + problemNumber + ": ");
        jcl.invokeClassMethod("projecteulersolutions.Problem" + problemNumberText, "printAnswer");
    }
}
