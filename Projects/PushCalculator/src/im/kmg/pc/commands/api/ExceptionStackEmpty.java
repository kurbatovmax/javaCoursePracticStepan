package im.kmg.pc.commands.api;

/**
 * Created with IntelliJ IDEA.
 * User: maxim
 * Date: 9/14/13
 * Time: 6:20 PM
 */
public class ExceptionStackEmpty extends Exception
{
    public ExceptionStackEmpty() {
        super("Stack empty");
    }
}
