package im.kmg.sibniar2;

import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: maxim
 * Date: 9/6/13
 * Time: 9:54 PM
 * To change this template use File | Settings | File Templates.
 */
public class MainPushCalculatorTest
{
    public static void main(String argv[]) {

        ////////////////
        // test Push
        ///////////////
        AppStackCalculate appStack = new AppStackCalculate();
        List<String> list = new ArrayList<String>();
        list.add("Push 1");
        list.add("Push 16");
        for (String s : list) {
            List<String> item = new ArrayList<String>();
            Collections.addAll(item, s.split(" "));
            try {
                appStack.executeCommand(item);
            } catch (CommandNotFoundException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            } catch (BadParamException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }
        }

        Stack<String> dbgS = appStack.getStack();
        List<String> dbgList = dbgS.subList(0, dbgS.size());

        boolean PassPushTest = false;

        if ( dbgList.size() == 2 ) {
            if (dbgList.get(0).compareTo("1") == 0) {
                if ( dbgList.get(1).compareTo("16") == 0 ) {
                     PassPushTest = true;
                }
            }
        }

        if (PassPushTest == true) {
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
        List<String> item = new ArrayList<String>();
        Collections.addAll(item, list.get(0).split(" "));
        try {
            appStack.executeCommand(item);
        } catch (BadParamException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (CommandNotFoundException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

        dbgS = appStack.getStack();
        dbgList = dbgS.subList(0, dbgS.size());
        String dbgResult = dbgList.get(dbgList.size()-1);
        if ( dbgResult.compareTo("4.0") == 0 ) {
            System.out.println("Test Sqrt: PASS");
        } else {
            System.out.println("Test Sqrt: FAIL");
        }
    }
}