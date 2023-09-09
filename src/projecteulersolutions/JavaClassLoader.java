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
    public void invokeClassMethod(String className, String methodName) {
        try {
            ClassLoader cLoader = this.getClass().getClassLoader();
            Class myClass = cLoader.loadClass(className); // can throw cnfe
            
            Constructor constructor = myClass.getConstructor(); // can throw nsme
            Object myClassObject = constructor.newInstance(); // can throw ie, iae, ite
            
            Method myMethod = myClass.getMethod(methodName);
            myMethod.invoke(myClassObject);
        } catch (ClassNotFoundException cnfe) {
            
        } catch (NoSuchMethodException nsme) {
            
        } catch (InstantiationException ie) {
            
        } catch (IllegalAccessException iae) {
            
        } catch (InvocationTargetException ite) {
            
        }
    }
}
