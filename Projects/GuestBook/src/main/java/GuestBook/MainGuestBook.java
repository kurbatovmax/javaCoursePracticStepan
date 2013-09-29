package GuestBook;

import GuestBook.DataModel.DataModelGuestBook;
import GuestBook.DataModel.GuestBookView;

import java.sql.*;
import java.util.Date;


/**
 * Hello world!
 *
 */
public class MainGuestBook
{
    public static void main( String[] args ) throws SQLException {
        //DataModelGuestBook modelGuestBook = new DataModelGuestBook();
        //GuestBookView guestBookView = new GuestBookView();
        //modelGuestBook.addObserver(guestBookView);


        try {
            Class.forName("org.h2.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }


        Connection connection = null;
        try {
            connection = DriverManager.getConnection("jdbc:h2:file:guestbook", "admin", "admin");
            createTatle(connection);
            addPostMsg("post1", connection);
            addPostMsg("post2", connection);
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
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
