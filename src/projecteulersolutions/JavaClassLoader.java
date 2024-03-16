package projecteulersolutions;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.logging.Logger;
import java.util.logging.Level;

/*
JavaClassLoader allows dynamic invokation of methods
during runtime.

JavaClassLoader extends ClassLoader for the purpose of inheriting
the .loadClass() and .getMethod() methods.
It then uses the Method.invoke() method to initiate the desired
object and its method to run at users request.

This can be used to effectively run a given Euler problem's code
based on user input.
 */
public class JavaClassLoader extends ClassLoader {

    private static final Logger logger = Logger.getLogger(JavaClassLoader.class.getName());

    public Object invokeClassMethod(String className, String methodName) {

        try {
            // loads class of given class name
            Class<?> myClass = Class.forName(className);

            // uses class constructor to instantiate new object of given class
            Object myClassObject = myClass.getDeclaredConstructor().newInstance();

            // uses class to get the proper given method
            Method myMethod = myClass.getMethod(methodName);

            // uses instatiated class object to invoke the given method
            return myMethod.invoke(myClassObject);

        } catch (ClassNotFoundException
                | IllegalAccessException
                | IllegalArgumentException
                | InstantiationException
                | NoSuchMethodException
                | SecurityException
                | InvocationTargetException e) {
            logger.log(Level.SEVERE, "An error occurred while invoking class method", e);
            return null;
        }
    }
}
