package projecteulersolutions;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

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
    public Object invokeClassMethod(String className, String methodName) {
        try {
            // new ClassLoader for invocation
            ClassLoader cLoader = this.getClass().getClassLoader();
            // loads class of given class name
            Class myClass = cLoader.loadClass(className); // can throw cnfe
            // uses class constructor to instantiate new object of given class
            Constructor constructor = myClass.getConstructor(); // can throw nsme
            Object myClassObject = constructor.newInstance(); // can throw ie, iae, ite
            // uses class to get the proper given method
            Method myMethod = myClass.getMethod(methodName);
            // uses instatiated class object to invoke the given method
            return myMethod.invoke(myClassObject);
        } catch (ClassNotFoundException // from .loadClass();
                | NoSuchMethodException // from .getMethod(); and .getConstructor();
                | InstantiationException // from .newInstance();
                | IllegalAccessException // from .newInstance(); and .invoke(Object);
                | InvocationTargetException e) { // from .newInstance(); and .invoke(Object);
            // basic exception printing
            // this should be expanded to more granular solutions in the future
            System.out.println("Exception encountered - " + e);   
        }
        return null;
    }
}
