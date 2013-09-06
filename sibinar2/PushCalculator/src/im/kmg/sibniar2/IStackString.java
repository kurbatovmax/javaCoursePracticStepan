package im.kmg.sibniar2;

/**
 * Created with IntelliJ IDEA.
 * User: KurbatovM
 * Date: 9/5/13
 * Time: 4:47 PM
 */
public interface IStackString
{
    public String Push(String sParam);
    public String Pop();
    public String Peek();
    public String toString();
    public int size();
}
