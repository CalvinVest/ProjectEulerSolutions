package projecteulersolutions;
/*
EulerConsole handles all project console level operations, for future adaptation to a better UI than just a text-based
console. For now, all interactions with the console will be encapsulated in EulerConsole.
 */

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public interface EulerConsole {

    int MENU_WIDTH = 50;

    String TOP_BORDER = "╔" + "═".repeat(MENU_WIDTH) + "╗";
    String MID_BORDER = "╠" + "═".repeat(MENU_WIDTH) + "╣";
    String BOT_BORDER = "╚" + "═".repeat(MENU_WIDTH) + "╝";

    static void println(String str) {
        System.out.println(str);
    }

    private static void printBorderedLine(String str) {
        System.out.printf("%-" + MENU_WIDTH + "s%n", "║" + str + "║");
    }

    private static void printWrappedBorderedLine(String str) {
        var strs = wrapText(str, MENU_WIDTH);
        for(String lineStr : strs) { printBorderedLine(lineStr); }
    }

    static List<String> wrapText(String text, int menuWidth) {
        String[] words = text.split(" ");
        List<String> lines = new ArrayList<>();
        StringBuilder currentLine = new StringBuilder();

        for (String word : words) {
            // Check if adding the next word would exceed the width
            if (currentLine.length() + word.length() + 1 > menuWidth) {
                // Add the current line to the list and start a new line
                lines.add(String.format("%-" + menuWidth + "s", currentLine.toString().trim()));
                currentLine = new StringBuilder(); // reset for the next line
            }

            // Add the word to the current line
            if (!currentLine.isEmpty()) {
                currentLine.append(" ");
            }
            currentLine.append(word);
        }

        // Add the last line to the list (if it's not empty)
        if (!currentLine.isEmpty()) {
            lines.add(String.format("%-" + menuWidth + "s", currentLine.toString().trim()));
        }

        return lines;
    }

    static void printCursor() {
        System.out.print("> ");
    }

    static void printHeaderAndBlock(String header, List<String> block) {
        System.out.println(TOP_BORDER);
        printWrappedBorderedLine(header);
        System.out.println(MID_BORDER);
        for(String str : block) { printWrappedBorderedLine(str); }
        System.out.println(BOT_BORDER);
    }

    static void printHeaderAndTwoBlocks(String header, List<String> block1, List<String> block2) {
        System.out.println(TOP_BORDER);
        printWrappedBorderedLine(header);
        System.out.println(MID_BORDER);
        for(String str : block1) { printWrappedBorderedLine(str); }
        System.out.println(MID_BORDER);
        for(String str : block2) { printWrappedBorderedLine(str); }
        System.out.println(BOT_BORDER);
    }

    static void printExceptionMessage(Exception e, String message) {
        System.out.println(TOP_BORDER);
        printWrappedBorderedLine("Exception Encountered.");
        System.out.println(MID_BORDER);
        printWrappedBorderedLine(message);
        printWrappedBorderedLine("Exception message: " + e.toString());
        System.out.println(BOT_BORDER);
    }

    static void printSuccessMessage(String message) {
        ArrayList<String> messageList = new ArrayList<>();
        messageList.add(message);
        printHeaderAndBlock("Success!", messageList);
    }

    static void waitForEnterPress(Scanner userIn) {
        println("\nPress Enter to Continue...");
        userIn.nextLine();
    }
}
