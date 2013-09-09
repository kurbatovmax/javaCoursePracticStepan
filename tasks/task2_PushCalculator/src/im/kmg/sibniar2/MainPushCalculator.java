package im.kmg.sibniar2;

import java.io.*;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;
import static java.lang.System.err;

/**
 * Created with IntelliJ IDEA.
 * User: KurbatovM
 * Date: 9/5/13
 * Time: 3:46 PM
 */
public class MainPushCalculator
{
    final public static  String VERSION = "0.01";
    public static boolean running = true;

    /**
     *
     * @param argv for cmd line
     */
    public static void main( String argv[]) throws Exception {
        // Invoker command
        AppStackCalculate appMain = new AppStackCalculate();

        BufferedReader inBufferedReader = null;
        boolean isFromFile = false;

        if (argv.length > 0) {
            try {
                inBufferedReader = new BufferedReader(new FileReader(argv[0]));
                isFromFile = true;
            } catch (FileNotFoundException e) {
                System.err.println(e.getLocalizedMessage());
                System.out.println("Use std stream");
            }
        }

        if (inBufferedReader == null) {
            inBufferedReader = new BufferedReader( new InputStreamReader(System.in));
            isFromFile = false;
        }

        Scanner scanner = new Scanner(inBufferedReader);
        String cmdLine = null;
        CliParser cmdParser = null;
        while (running) {
            if (!isFromFile) {
                printStartMsg();
            }

            try {
                cmdLine = scanner.nextLine();
                cmdParser = new CliParser(cmdLine);
            } catch (NoSuchElementException e) {
                System.out.println("Programme done\nGood bay!");
                System.exit(0);
            }

            if ( !cmdParser.hasCommand() ) {
                continue;
            }

            try {
                if (isFromFile) {
                    System.out.println("exec cmd: " + cmdLine);
                }
                appMain.executeCommand(cmdParser.getCmdWithParam());
            } catch (CommandNotFoundException e) {
                err.println("Error: " + e.getLocalizedMessage());
            } catch (BadParamException e) {
                err.println("Error: " + e.getLocalizedMessage());
            }
        }
    }

    /**
     *
     */
    private static void printStartMsg() {
        System.out.println("-----------------------------------");
        System.out.println("enter \"HELP\" for more information");
        System.out.print("cli>>> ");
    }
}
