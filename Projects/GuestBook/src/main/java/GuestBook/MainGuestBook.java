package GuestBook;
import GuestBook.DataModel.DataModelGuestBooks;
import GuestBook.DataModel.GuestRecord;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.sql.*;
import java.util.Date;
import java.util.List;
import java.util.Scanner;


/**
 * Hello world!
 *
 */
public class MainGuestBook
{
    public static void main( String[] args ) throws SQLException, ClassNotFoundException, IOException {
        DataModelGuestBooks dataModelGuestBooks = new DataModelGuestBooks();
        IGuestBookController guestBookController = new GuestBookController(dataModelGuestBooks);
        GuestBookView guestBookView = new GuestBookView();
        dataModelGuestBooks.addObserver(guestBookView);

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        while(true) {
            System.out.println("You can add post message: ");
            System.out.print(">> ");
            String str  = in.readLine();
            if ( str.equals("exit")) {
                break;
            }

            guestBookController.addRecord(str);
        }
    }
}
