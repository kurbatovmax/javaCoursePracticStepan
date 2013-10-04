package GuestBook.DataModel.H2DB_Layer;


import GuestBook.DataModel.GuestRecord;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class GuestBookDB
{
    private Connection connection = null;
    private List<GuestRecord> records;

    /**
     *
     */
    public GuestBookDB() throws ClassNotFoundException, SQLException {
        Class.forName("org.h2.Driver");
        try {
            Connecting();
            createTable();
        } catch (SQLException e) {
            e.printStackTrace();
            throw  e;
        }
    }

    /**
     *
     * @return
     * @throws SQLException
     */
    void Connecting() throws SQLException {
        this.connection = DriverManager.getConnection("jdbc:h2:file:guestbook", "admin", "admin");
    }

    /**
     *
     * @throws SQLException
     */
    void createTable() throws SQLException {
        if ( this.connection != null ) {
            this.connection.createStatement().execute(
                "CREATE TABLE IF NOT EXISTS GUESTBOOK(ID INT PRIMARY KEY AUTO_INCREMENT NOT NULL," +
                        " DATE TIMESTAMP , MSG VARCHAR(1024))"
            );
        }
    }

    /**
     *
     * @param msg
     */
    public void addPostMessage(String msg) throws SQLException {
        PreparedStatement ps = connection.prepareStatement("INSERT INTO GUESTBOOK(DATE, MSG) VALUES (?,?)");
        ps.setTimestamp(1, new Timestamp(new Date().getTime()));
        ps.setString(2, msg);
        ps.execute();
    }

    /**
     *
     * @return
     */
    public List<GuestRecord> getRecords() throws SQLException {
        PreparedStatement ps = connection.prepareStatement("SELECT ID as ID, DATE AS DATE, MSG AS MSG FROM GUESTBOOK");
        List<GuestRecord> listRetv;
        if (ps.execute() == false) {
            listRetv = new ArrayList<GuestRecord>();
        } else {
            listRetv = new ArrayList<GuestRecord>();
            ResultSet resultSet = ps.getResultSet();
            while (resultSet.next()) {
                GuestRecord guestRecord = new GuestRecord();
                guestRecord.setIdKey(resultSet.getInt("ID"));
                guestRecord.setPostDate(resultSet.getDate("DATE"));
                guestRecord.setPostMsg(resultSet.getString("MSG"));
                listRetv.add(guestRecord);
            }
        }
        return listRetv;
    }
}
