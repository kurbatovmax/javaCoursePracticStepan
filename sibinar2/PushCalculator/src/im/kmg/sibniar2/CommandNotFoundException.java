package im.kmg.sibniar2;

/**
 * Created with IntelliJ IDEA.
 * User: maxim
 * Date: 9/5/13
 * Time: 8:25 PM
 */
class CommandNotFoundException extends Exception
{
    CommandNotFoundException() {
        super("Command not found");
    }
}
