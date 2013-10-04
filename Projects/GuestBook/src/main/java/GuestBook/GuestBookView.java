package GuestBook;

import GuestBook.DataModel.DataModelGuestBooks;
import GuestBook.DataModel.GuestRecord;

import java.util.List;
import java.util.Observable;
import java.util.Observer;

/**
 * Created with IntelliJ IDEA.
 * User: maxim
 * Date: 9/29/13
 * Time: 7:30 PM
 * To change this template use File | Settings | File Templates.
 */
public class GuestBookView implements Observer
{
    @Override
    public void update(Observable o, Object arg) {
        if (arg != null) {
            DataModelGuestBooks dm = (DataModelGuestBooks)arg;
            List<GuestRecord> list = dm.getRecords();
            for (GuestRecord s : list) {
                System.out.println(s);
            }
        }
    }
}
