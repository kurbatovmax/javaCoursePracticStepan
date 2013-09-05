package im.kmg.sibniar2;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: KurbatovM
 * Date: 9/5/13
 * Time: 4:04 PM
 * To change this template use File | Settings | File Templates.
 */
public class CommandSub implements ICommand
{
    final private String NAME = "Sub";

    @Override
    public String getName() {
        return NAME;
    }

    @Override
    public void execute() {
        System.out.println("this " + this.getName());
    }

    @Override
    public void init(List<String> dataComand) {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
