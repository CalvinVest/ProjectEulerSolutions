package projecteulersolutions;
/*
EulerConsole handles all project console level operations, for future adaptation to a better UI than just a text-based
console. For now, all interactions with the console will be encapsulated in EulerConsole.
 */

public interface EulerConsole {

    public static void println(String str) {
        System.out.println(str);
    }

    public static void print(String str) {
        System.out.print(str);
    }
}
