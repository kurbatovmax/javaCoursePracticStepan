package im.kmg.task3;

import org.omg.CORBA.MARSHAL;

import java.io.File;
import java.io.FilenameFilter;
import java.util.Arrays;
import java.util.Map;
import java.util.Scanner;

import static java.lang.System.out;

/**
 * Created with IntelliJ IDEA.
 * User: user
 * Date: 07.09.13
 * Time: 15:52
 */
public class Main
{

    public static String tesString = "Во саду ли во городе";


    /**
     *
     * @param argv
     */
    public static void main(String argv[]) {
    //    while (true) {
            // read input
            String inBuffer = Main.tesString; // new Scanner(System.in).nextLine();

            // split
            inBuffer = inBuffer.toLowerCase();
            inBuffer = inBuffer.trim();
            String listStr[] = inBuffer.split(" ");

            for (String s : listStr ) {
                out.println("s: " + s);
            }


            Map<String, Integer> popul;

      //  }
    }

}


