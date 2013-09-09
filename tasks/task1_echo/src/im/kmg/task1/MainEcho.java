package im.kmg.task1;

import java.util.Scanner;

/**
 * Created with IntelliJ IDEA.
 * User: Kurbatov Maxim
 * Date: 9/4/13
 * Time: 11:39 PM
 */
public class MainEcho
{
    public static void main(String argv[]) {
        boolean loopApp = true;

        String encoding = System.getProperty("file.encoding");
        MainEcho.printMessage("encoding file: " + encoding + "\n");


        while (loopApp) {
            MainEcho.printMessage("Please any  char and press enter or enter ^exit for end appication\n");
            String inBuffer = new Scanner(System.in).nextLine();

            if (inBuffer.compareTo("^exit") == 0) {
                MainEcho.printMessage("good bay\n\n");
                loopApp=false;
                continue;
            }

            MainEcho.printMessage("Your enter: \n" + inBuffer + "\n");
        }
    }

    /**
     * short method for print string
     * @param str
     */
    private static void printMessage(String str) {
        System.out.print(str);
    }
}
