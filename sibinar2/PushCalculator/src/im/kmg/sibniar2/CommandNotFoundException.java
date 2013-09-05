package im.kmg.sibniar2;

/**
 * Created with IntelliJ IDEA.
 * User: maxim
 * Date: 9/5/13
 * Time: 8:25 PM
 * To change this template use File | Settings | File Templates.
 */
public class CommandNotFoundException extends Exception
{
    CommandNotFoundException() {
        super("Command not found");
    }
}
