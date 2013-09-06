package im.kmg.sibniar2;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: KurbatovM
 * Date: 9/5/13
 * Time: 4:04 PM
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
    public void init(List<String> dataCommand) {}

    @Override
    public String getHelp() {
        return NAME + " NUMBER\t\t-\tNot implements yet\n";
    }
}
