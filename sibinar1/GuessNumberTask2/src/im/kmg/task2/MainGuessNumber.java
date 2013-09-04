package im.kmg.task2;

import java.util.Scanner;

/**
 * Created with IntelliJ IDEA.
 * User:Kurbatov Maxim
 * Date: 9/5/13
 * Time: 12:29 AM
 * To change this template use File | Settings | File Templates.
 */
public class MainGuessNumber
{

    final static int MAX_TRY=10; /** Maximum try */

    /**
     *
     * @param argv
     */
    public static void main(String argv[]) {
        boolean isRunning = true;
        int dwCurrentTry = 0;

        while (isRunning) {
            int randNumber = MainGuessNumber.getRandNumber();
            System.out.println("I put forth a number from 0 to 100");

            while (dwCurrentTry < MainGuessNumber.MAX_TRY) {
                System.out.println("You have a " + (MainGuessNumber.MAX_TRY - dwCurrentTry) + " try" );
                System.out.println("Enter number or ^exit for finished app");
                String inUser = new Scanner(System.in).next();

                if (inUser.compareTo("^exit") == 0) {
                    System.out.print("Good bay");
                    System.exit(0);
                }

                int dwUserNum;
                try {
                    dwUserNum = Integer.valueOf(inUser);
                } catch (NumberFormatException e)  {
                    System.out.println("Bad number");
                    continue;
                }

                if ( dwUserNum == randNumber) {
                    System.out.println("=====================\n" +
                            "You wining\n" +
                            "New game!\n" +
                            "=====================");
                    break;
                }

                if (dwUserNum > randNumber) {
                    System.out.println("my number <, try agane");
                } else {
                    System.out.println("my number >, try agane");
                }
                dwCurrentTry++;
            }
        }
    }

    /**
     *
     * @return random number 0...100
     */
    public static int getRandNumber() {
        // 0 ... 100
        int dwRandNumber = (int)( (Math.random() * 1000) % 101 );

        // fro debug
        //System.out.println("Rand Number: " + dwRandNumber);

        return dwRandNumber;

    }
}