package im.kmg.sibniar2;

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
    public void execute();
    public void init(List<String> dataCommand) throws BadParamException;
    public  String getHelp();
}
