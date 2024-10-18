package projecteulersolutions;
/*
EulerConsole handles all project console level operations, for future adaptation to a better UI than just a text-based
console. For now, all interactions with the console will be encapsulated in EulerConsole.
 */

import java.util.ArrayList;
import java.util.List;

public interface EulerConsole {

    static final int MENU_WIDTH = 50;

    final String TOP_BORDER = "╔" + "═".repeat(MENU_WIDTH) + "╗";
    final String MID_BORDER = "╠" + "═".repeat(MENU_WIDTH) + "╣";
    final String BOT_BORDER = "╚" + "═".repeat(MENU_WIDTH) + "╝";

    public static void println(String str) {
        System.out.println(str);
    }

    public static void print(String str) {
        System.out.print(str);
    }

    private static void printBorderedLine(String str) {
        System.out.println(String.format("%-" + MENU_WIDTH + "s", "║" + str + "║"));
    }

    private static void printWrappedBorderedLine(String str) {
        var strs = wrapText(str, MENU_WIDTH);
        for(String lineStr : strs) {
            printBorderedLine(lineStr);
        }
    }

    public static List<String> wrapText(String text, int menuWidth) {
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
            if (currentLine.length() > 0) {
                currentLine.append(" ");
            }
            currentLine.append(word);
        }

        // Add the last line to the list (if it's not empty)
        if (currentLine.length() > 0) {
            lines.add(String.format("%-" + menuWidth + "s", currentLine.toString().trim()));
        }

        return lines;
    }

    static void printMainMenuOptions() {
        System.out.println(TOP_BORDER);
        printWrappedBorderedLine("Welcome! Please choose an option.");
        System.out.println(MID_BORDER);
        printWrappedBorderedLine("S: Solve problem by number");
        printWrappedBorderedLine("L: Problem List");
        printWrappedBorderedLine("V: View Progress");
        printWrappedBorderedLine("Q: Quit");
        System.out.println(BOT_BORDER);
    }

    static void printCursor() {
        System.out.print("> ");
    }

    static void printHeaderAndBlock(String header, List<String> block) {
        System.out.println(TOP_BORDER);

        printWrappedBorderedLine(header);

        System.out.println(MID_BORDER);

        for(String str : block) {
            printWrappedBorderedLine(str);
        }

        System.out.println(BOT_BORDER);
    }

    static void printHeaderAndTwoBlocks(String header, List<String> block1, List<String> block2) {
        System.out.println(TOP_BORDER);

        printWrappedBorderedLine(header);

        System.out.println(MID_BORDER);

        for(String str : block1) {
            printWrappedBorderedLine(str);
        }

        System.out.println(MID_BORDER);

        for(String str : block2) {
            printWrappedBorderedLine(str);
        }

        System.out.println(BOT_BORDER);
    }
}
