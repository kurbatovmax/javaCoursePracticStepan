package im.kmg.task3;

import java.util.Map;
import java.util.Scanner;

import static java.lang.System.out;

/**
 * Created with IntelliJ IDEA.
 * User: user
 * Date: 07.09.13
 * Time: 15:52
 * To change this template use File | Settings | File Templates.
 */
public class Main
{

    /**
     *
     * @param argv
     */
    public static void main(String argv[]) {
        while (true) {
            // read input
            String inBuffer = new Scanner(System.in).nextLine();

            // split
            String listStr[] = inBuffer.split(" ");

            for (String s : listStr ) {
                out.println("s: " + s);
            }


            Map<String, Integer> popul;

        }
    }

}
