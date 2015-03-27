import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.BufferedWriter;

public class Cesar {

    private static final boolean TESTING = false;
    private static String INPUT = "in.txt";
    private static String OUTPUT = "out.txt";

    public static void main(String[] args) {
        if (TESTING) {
            args = new String[] {"abc"};
        }

        String msg = parseFlags(args);
        System.out.println("File Mode: " + FILE_MODE);
        System.out.println("Verbose: " + VERBOSE);
        if (FILE_MODE) {
            readNWrite();
        }
        if (VERBOSE) {
            verbosePrint(msg);
        }
    }

    private static boolean VERBOSE = false;
    private static boolean FILE_MODE = false;

    // Parses for flags and returns the message
    private static String parseFlags(String[] args) {
        int len = args.length;
        if (len == 0) {
            printHelpAndQuit();
        }
        if (len == 1 && args[0].length() > 2 && !"-verbose".equalsIgnoreCase(s)) {
            VERBOSE = true;
            return args[0];
        }

        String oddity = null;
        for (count = 0; count < len; count++) {
            String s = args[count];
            if ("-v".equalsIgnoreCase(s) || "-verbose".equals(s)) {
                VERBOSE = true;
                if (len == 1) {
                    System.out.println("Need to give a message! ");
                    printHelpAndQuit();
                }
            } else if ("-f".equalsIgnoreCase(s) || "-file".equalsIgnoreCase(s)) {
                FILE_MODE = true;
                if (count == len - 3) {
                    INPUT = args[];
                }
            } else if ("--help".equalsIgnoreCase(s)) {
                printHelpAndQuit();
            } else {
                if (oddity == null) {
                    oddity = s;
                } else {
                    System.out.print("Malformed input! ");
                    printHelpAndQuit();
                }
            }
        }
        return oddity;
    }

    private static void printHelpAndQuit() {
        String helpMsg = "java Cesar ";
        helpMsg += "[-v/-verbose] [-f/-file] [msg in quotes]";
        System.out.println(helpMsg);
        System.exit(0);
    }

    private static void verbosePrint(String msg) {
        for (int encVal = 0; encVal < 26; encVal++) {
            System.out.print(encVal + ": ");
            System.out.println(shift(msg, encVal));
        }
    }

    private static StringBuilder readFile() throws FileNotFoundException, IOException {
        FileReader myFR = new FileReader(INPUT);
        BufferedReader br = new BufferedReader(new FileReader(INPUT));
        StringBuilder sb = new StringBuilder();

        String line = br.readLine();
        while (line != null) {
            sb.append(line);
            sb.append(System.lineSeparator());
            line = br.readLine();
        }
        System.out.println("Reading file successful!");
        return sb;
    }

    private static final int NUM_LINES_SEP = 1;

    // Will attempt to read from INPUT and output to OUTPUT
    private static void readNWrite() {
        try {
            BufferedReader br = new BufferedReader(new FileReader(INPUT));
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();

            while (line != null) {
                sb.append(line);
                sb.append(System.lineSeparator());
                line = br.readLine();
            }
            String everything = sb.toString();
            StringBuilder outputSB = new StringBuilder();
            for (int encVal = 0; encVal < 26; encVal++) {
                outputSB.append(encVal + System.lineSeparator());
                outputSB.append(shift(everything, encVal));
                for (int sep = 0; sep < NUM_LINES_SEP; sep++) {
                    outputSB.append(System.lineSeparator());
                }
            }
            // File deleteMe = new File(OUTPUT);
            // deleteMe.delete();
            // deleteMe.createNewFile();
            FileWriter fOut = new FileWriter(OUTPUT, false);
            BufferedWriter bw = new BufferedWriter(fOut);
            System.out.println("Length of output: " + outputSB.length());
            bw.write(outputSB.toString());
            bw.close();
            fOut.close();
            br.close();
            System.out.println("File reading successful!");
        } catch(FileNotFoundException e) {
            System.out.println("File " + INPUT + " not found!");
            return;
        } catch (IOException e) {
            System.out.println("IOException!");
            return;
        }
    }

    private static String shift(String str, int shiftAmt) {
        StringBuilder out = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            char curr = str.charAt(i);
            if (isAlpha(curr)) {
                out.append("" + shift(curr, shiftAmt));
            } else {
                out.append("" + curr);
            }
        }
        return out.toString();
    }

    private static boolean isAlpha(char curr) {
        return isCap(curr) || isLower(curr);
    }

    private static boolean isCap(char curr) {
        int v = (int) curr;
        return v - 'A' >= 0 && v - 'A' < 26;
    }

    private static boolean isLower(char curr) {
        int v = (int) curr;
        return v - 'a' >= 0 && v - 'a' < 26;
    }

    private static char shift(char curr, int shiftAmt) {
        int v = (int) curr;
        if (isLower(curr)) {
            int x = v - 'a';
            return (char) (Math.floorMod(x + shiftAmt, 26) + 'a');
        } else {
            int x = v - 'A';
            return (char) (Math.floorMod(x + shiftAmt, 26) + 'A');
        }
    }
}