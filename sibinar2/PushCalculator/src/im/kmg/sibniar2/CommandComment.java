package im.kmg.sibniar2;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: KurbatovM
 * Date: 9/5/13
 * Time: 4:02 PM
 * To change this template use File | Settings | File Templates.
 */
public class CommandComment implements ICommand
{
    final private String NAME = "Commnet";

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
