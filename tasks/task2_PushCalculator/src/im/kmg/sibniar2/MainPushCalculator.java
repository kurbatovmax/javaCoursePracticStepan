package im.kmg.sibniar2;

import java.io.*;
import java.util.Scanner;
import static java.lang.System.err;
import static java.lang.System.out;

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
    public static void main( String argv[]) {
        // Invoker command
        AppStackCalculate appMain = new AppStackCalculate();

        while (running) {

            FileReader s = null;
            BufferedReader in = null;
            if (argv.length > 0) {
                try {
                    s = new FileReader( new File(argv[0]));
                    in = new BufferedReader(s);
                } catch (FileNotFoundException e) {
                    System.err.println(e.getLocalizedMessage());
                    System.exit(-1);
                }
            }

            String s1 = null;
            try {
                s1 = in.readLine();
            } catch (IOException e) {
                System.err.println(e.getLocalizedMessage());
            }

            printStartMsg();
            //String s1 = new Scanner(System.in).nextLine();
            CliParser cmdParser = new CliParser(s1);

            try {
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
        out.println("-----------------------------------");
        out.println("enter \"Help\" for more information");
        out.print("cli>>> ");
    }
}
