package projecteulersolutions;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/*
JavaClassLoader allows dynamic invocation of methods
during runtime.

JavaClassLoader extends ClassLoader for the purpose of inheriting
the .loadClass() and .getMethod() methods.
It then uses the Method.invoke() method to initiate the desired
object and its method to run at users request.

This can be used to effectively run a given Euler problem's code
based on user input.
 */
public class JavaClassLoader extends ClassLoader {

    public Object invokeClassMethod(String className, String methodName) {
        try {
            // new ClassLoader for invocation
            ClassLoader cLoader = this.getClass().getClassLoader();

            // loads class of given class name, can throw ClassNotFoundException
            Class<?> myClass = cLoader.loadClass(className);

            // uses class constructor to instantiate new object of given class
            // can throw NoSuchMethodException
            Constructor constructor = myClass.getConstructor();

            // instantiates the given class (problem) 
            // can throw IllegalAccessException, InstantiationException,
            // and InvocationTargetException
            Object myClassObject = constructor.newInstance();

            // uses class to get the proper given method
            Method myMethod = myClass.getMethod(methodName);

            // uses instatiated class object to invoke the given method
            return myMethod.invoke(myClassObject);

        } catch (ClassNotFoundException // from .loadClass();
                | IllegalAccessException // from .newInstance(); and .invoke(Object);
                | InstantiationException // from .newInstance();
                | InvocationTargetException // from .newInstance(); and .invoke(Object);
                | NoSuchMethodException e) {  // from .getMethod(); and .getConstructor();
            System.out.println("EXCEPTION ENCOUNTERED.");
            System.out.println(e.toString() + " occurred, program failed to run successfully.");
            System.out.println("Cause: " + e.getCause().toString());
        }
        return null;
    }
}
