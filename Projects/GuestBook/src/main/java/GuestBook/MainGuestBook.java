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


        /*
        List<GuestRecord> lists = guestBookController.getRecords();

        for (GuestRecord s : lists) {
            System.out.println(s);
        }
        */
    }

    /**
     *
     * @param connection
     */
    public static void createTatle(Connection connection) throws SQLException {
        connection.createStatement().execute(
                "CREATE TABLE IF NOT EXISTS GUESTBOOK(ID INT PRIMARY KEY AUTO_INCREMENT NOT NULL, DATE TIMESTAMP , MSG VARCHAR(1024))"
        );
    }

    /**
     *
     * @param msg
     * @param connection
     */
    public static  void addPostMsg(String msg, Connection connection) throws SQLException {
        PreparedStatement ps = connection.prepareStatement("INSERT INTO GUESTBOOK(DATE, MSG) VALUES (?,?)");
        ps.setTimestamp(1, new Timestamp(new Date().getTime()));
        ps.setString(2, msg);
        if ( ps.execute() == false) {
            System.out.println("error");
        }
    }
}
