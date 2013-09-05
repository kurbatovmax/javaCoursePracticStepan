package im.kmg.sibniar2;

import java.util.List;
import java.util.Scanner;

import static java.lang.System.console;
import static java.lang.System.out;

/**
 * @brif
 * Created with IntelliJ IDEA.
 * User: KurbatovM
 * Date: 9/5/13
 * Time: 3:46 PM
 * To change this template use File | Settings | File Templates.
 */
public class MainPushCalculator
{

    final public static  String VERSION = "0.01";

    /**
     *
     * @param argv
     */
    public static void main( String argv[]) throws BadParamException {

        /*
        IStackString stack = new KmgStack();
        stack.Push("1");
        stack.Push("10");
        String s = stack.Pop();
        //s +=  stack.Pop();
        //s +=  stack.Pop();
        stack.Push("20");
        System.out.println("Dump stack: " + stack.toString());
        */

        AppStackCalculate appMain = new AppStackCalculate();

        while (true) {
            printStartMesg();
            String s1 = new Scanner(System.in).nextLine();

            CommandsParser cmdParser = new CommandsParser(s1);

            if (cmdParser.isValid() == false) {
                System.out.println("Unknow cammand");
                continue;
            }

            if (cmdParser.isHelp() == true ) {
                cmdParser.printHelp();
                continue;
            }

            if ( cmdParser.isExit() == true ) {
                out.println("Good bay");
                System.exit(0);
            }

            List<String> dataComand = cmdParser.getParamCmd();

            ICommand cmd = null;
            try {
                cmd = appMain.getCommand(dataComand);
            } catch (CommandNotFoundException e) {
                System.err.println("\n" + e.getLocalizedMessage() + "\n");
                continue;
            }


            cmd.execute();

        }
    }

    /**
     *
     */
    private static void printStartMesg() {
        out.println("-----------------------------------");
        out.println("enter --help for more information");
        out.print("enter command: >>> ");
    }

    /**
     * Parser command string
     * @param strParam
     * @return
     */
    /*
    public static CommandsType CommnadParser(String strParam) {
        String []param = strParam.split(" ");

        if ( param.length >= 1 ) {
            // only java 7
            switch (param[0]) {
                case "--exit": {
                    out.println("Good bay");
                    System.exit(0);
                    break;
                }
                case "--help": {
                    MainPushCalculator.printHelp();
                    return CommandsType.HELP;
                }
                case "Pop": {
                    if ( param.length == 1) {
                        return CommandsType.POP;
                    }
                    break;
                }
                case "Push": {
                    if (param.length == 2) {
                        return CommandsType.PUSH;
                    }
                    break;
                }
            }
        }
        return CommandsType.NONE;
    }
    */
}
