package im.kmg.pc;

import im.kmg.pc.commands.api.ExceptionBadParam;
import im.kmg.pc.commands.api.ExceptionStackEmpty;

import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: maxim
 * Date: 9/6/13
 * Time: 9:54 PM
 */
public class MainPushCalculatorTest
{
    public static void main(String argv[]) {

        ////////////////
        // test Push
        ///////////////
        AppStackCalculate appStack = new AppStackCalculate(null);
        List<String> list = new ArrayList<>();
        list.add("Push 1");
        list.add("Push 16");
        for (String s : list) {
            List<String> item = new ArrayList<>();
            Collections.addAll(item, s.split(" "));
            try {
                appStack.executeCommand(item);
            } catch (CommandNotFoundException | ExceptionStackEmpty | ExceptionBadParam e) {
                e.printStackTrace();
            }
        }

        Stack<String> dbgS = CommandPluginLoader.Instance().getContainerData().getStack();
        List<String> dbgList = dbgS.subList(0, dbgS.size());

        boolean PassPushTest = false;

        if ( dbgList.size() == 2 ) {
            if (dbgList.get(0).compareTo("1") == 0) {
                if ( dbgList.get(1).compareTo("16") == 0 ) {
                     PassPushTest = true;
                }
            }
        }

        if (PassPushTest) {
            System.out.println("Test Push: PASS");
        } else {
            System.out.println("Test Push: FAIL");
        }

        // End Push test--------------

        //////////////
        // test Sqrt
        /////////////
        list.clear();
        list.add("Sqrt");
        List<String> item = new ArrayList<>();
        Collections.addAll(item, list.get(0).split(" "));
        try {
            appStack.executeCommand(item);
        } catch (CommandNotFoundException | ExceptionStackEmpty | ExceptionBadParam e) {
            e.printStackTrace();
        }

        dbgS = CommandPluginLoader.Instance().getContainerData().getStack();
        dbgList = dbgS.subList(0, dbgS.size());
        String dbgResult = dbgList.get(dbgList.size()-1);
        if ( dbgResult.compareTo("4.0") == 0 ) {
            System.out.println("Test Sqrt: PASS");
        } else {
            System.out.println("Test Sqrt: FAIL");
        }
    }
}