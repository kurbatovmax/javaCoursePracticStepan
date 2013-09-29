package im.kmg.pc;

import im.kmg.pc.commands.api.ExceptionBadParam;
import im.kmg.pc.commands.api.ExceptionStackEmpty;

import java.io.*;
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
    public static void main( String argv[]) {
        String PluginListFile = "/plugins.list";
        CommandPluginLoader plugins = CommandPluginLoader.Instance();

        try {
            plugins.reload(PluginListFile);
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }

        // Invoker command
        AppStackCalculate appMain = new AppStackCalculate(plugins.getContainerData());

        BufferedReader inBufferedReader = null;
        boolean isFromFile = false;

        if (argv.length > 0) {
            File file = new File(argv[0]);
            try {
                inBufferedReader = new BufferedReader(new FileReader(file));
                isFromFile = true;
            } catch (FileNotFoundException e) {
                System.err.print(file.getAbsolutePath());
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

            printCmd(cmdLine, isFromFile);

            try {
                appMain.executeCommand(cmdParser.getCmdWithParam());
            } catch (CommandNotFoundException | ExceptionStackEmpty | ExceptionBadParam e) {
                err.println(e.getLocalizedMessage());
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

    private static void printCmd(String strCmd, boolean isEnabled) {
        if (isEnabled) {
            System.out.println("exec cmd: " + strCmd);
        }
    }
}
