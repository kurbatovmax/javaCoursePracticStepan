package im.kmg.sibniar2.commands;

import java.util.List;


/**
 * Created with IntelliJ IDEA.
 * User: KurbatovM
 * Date: 9/5/13
 * Time: 3:59 PM
 */
public interface ICommand
{
    public String getName();
    public void execute(List<String> commandWithArg) throws ExceptionBadParam, ExceptionStackEmpty;
    public  String getHelp();
}
