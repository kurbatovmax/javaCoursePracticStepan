package im.kmg.sibniar2;

import im.kmg.sibniar2.commands.ICommand;

/**
 * Created with IntelliJ IDEA.
 * User: maxim
 * Date: 9/13/13
 * Time: 11:34 PM
 */
public interface ICommandFactory
{
    public ICommand Instance(String nameCommand);
}
