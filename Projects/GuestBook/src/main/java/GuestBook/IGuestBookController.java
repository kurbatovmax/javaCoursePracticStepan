package GuestBook;

import GuestBook.DataModel.GuestRecord;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: maxim
 * Date: 9/29/13
 * Time: 2:06 PM
 */
public interface IGuestBookController
{
    void addRecord(String message);

    /**
     *
     * @return GuestRecords list
     * {id, data, message}
     */
    List<GuestRecord> getRecords();
}
