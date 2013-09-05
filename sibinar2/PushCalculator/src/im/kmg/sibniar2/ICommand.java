package im.kmg.sibniar2;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: KurbatovM
 * Date: 9/5/13
 * Time: 3:59 PM
 * To change this template use File | Settings | File Templates.
 */
public interface ICommand
{
    public String getName();
    public void execute();
    public void init(List<String> dataComand);
}
