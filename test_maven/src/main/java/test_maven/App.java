package test_maven;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws ClassNotFoundException, SQLException {
        Class.forName("org.h2.Driver");
        Connection connection =
                DriverManager.getConnection("jdbc:h2:file", "user1", "pass");

    }
}
