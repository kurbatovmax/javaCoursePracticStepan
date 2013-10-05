package GuestBook.DataModel;

import java.util.Observer;

/**
 * Created with IntelliJ IDEA.
 * User: maxim
 * Date: 9/29/13
 * Time: 7:26 PM
 */
interface  IObservable
{
    public void addObserver(Observer o);
    public void deleteObserver(Observer o);
    public void deleteObservers();
}
