package GuestBook.DataModel;

import java.util.Observable;
import java.util.Observer;

/**
 * Created with IntelliJ IDEA.
 * User: maxim
 * Date: 9/29/13
 * Time: 6:34 PM
 */
public class DataModelGuestBook implements  IObservable
{
    private Observable observable;

    /**
     *
     */
    public DataModelGuestBook() {
        this.observable = new Observable();

    }

    /**
     *
     * @param o
     */
    @Override
    public synchronized void addObserver(Observer o) {
        this.observable.addObserver(o);
    }

    /**
     *
     * @param o
     */
    @Override
    public synchronized void deleteObserver(Observer o) {
        this.observable.deleteObserver(o);
    }

    /**
     *
     */
    @Override
    public synchronized void deleteObservers() {
        this.observable.deleteObservers();
    }
}
