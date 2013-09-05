import java.util.Stack;

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

    /**
     *
     * @param argv
     */
    public static void main( String argv[]) {
        IKmgStackString stack = new KmgStack();

        stack.Push("1");

        stack.Push("10");

        String s = stack.Pop();
        s +=  stack.Pop();
        s +=  stack.Pop();

        stack.Push("20");



        System.out.println("Dump stack: " + stack.toString());
    }
}
