package GuestBook;

import GuestBook.DataModel.DataModelGuestBooks;
import GuestBook.DataModel.GuestRecord;
import java.sql.SQLException;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: maxim
 * Date: 9/29/13
 * Time: 2:11 PM
 */
public class GuestBookController implements IGuestBookController
{
    private DataModelGuestBooks dataModelGuestBook;

    GuestBookController(DataModelGuestBooks o) {
        this.dataModelGuestBook = o;
    }

    @Override
    public void addRecord(String message) {
        this.dataModelGuestBook.addRecord(message);
    }

    @Override
    public List<GuestRecord> getRecords() {
        return this.dataModelGuestBook.getRecords();
    }
}
