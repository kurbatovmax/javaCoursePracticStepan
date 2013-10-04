package GuestBook.DataModel;

import GuestBook.DataModel.H2DB_Layer.GuestBookDB;

import java.sql.SQLException;
import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: maxim
 * Date: 9/29/13
 * Time: 6:34 PM
 */
public class DataModelGuestBooks extends  Observable implements  IObservable
{
    private GuestBookDB guestBookDB;
    private List<GuestRecord> listGuestRecord;

    /**
     *
     */
    public DataModelGuestBooks() throws ClassNotFoundException, SQLException {
        //this.listGuestRecord = new ArrayList<GuestRecord>();
        this.guestBookDB = new GuestBookDB();
    }

    /**
     *
     * @param o
     */
    @Override
    public synchronized void addObserver(Observer o) {
        super.addObserver(o);
    }

    /**
     *
     * @param o
     */
    @Override
    public synchronized void deleteObserver(Observer o) {
        super.deleteObserver(o);
    }

    /**
     *
     */
    @Override
    public synchronized void deleteObservers() {
        super.deleteObservers();
    }

    /**
     *
     * @param message
     * @throws SQLException
     */
    public void addRecord(String message) throws SQLException {
        this.guestBookDB.addPostMessage(message);
        this.listGuestRecord = this.guestBookDB.getRecords();
        this.setChanged();
        this.notifyObservers(this);
    }

    /**
     *
     * @return
     * @throws SQLException
     */
    public List<GuestRecord> getRecords() {
        return Collections.unmodifiableList(this.listGuestRecord);
    }
}
