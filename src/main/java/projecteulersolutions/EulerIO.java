package projecteulersolutions;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/*
EulerIO's primary responsibility is reading and writing to files,
particularly individual problem files found in
main.java.projecteulersolutions.data

This is just a helper class for some of the Problems that read data from their
respective problemxxxx.txt data files, in order to declutter some of the Problem
classes that have just started to get a little stale.

Most of the stuff I'm going to add to this class during my spring-cleaning session
of this project should be re-reviewed later for some optimization, I already know
my method of attack with building this class is just lumping all the duplicate
methods from Problem classes into one big wall of code, and I just don't want to
deal with that again. (See: EulerMath.java)
 */
public interface EulerIO {

    static List<String> readLinesFromFile(File file) {
        ArrayList<String> lines = new ArrayList<>();
        try {
            Scanner fileIn = new Scanner(file);
            while (fileIn.hasNext()) {
                lines.add(fileIn.nextLine());
            }
        } catch (FileNotFoundException fnfe) {
            System.out.println("Exception encountered: " + file + " does not exist.");
        }
        return lines;
    }
}
