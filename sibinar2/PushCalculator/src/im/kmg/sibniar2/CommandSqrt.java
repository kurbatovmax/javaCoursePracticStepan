package im.kmg.sibniar2;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: KurbatovM
 * Date: 9/5/13
 * Time: 4:07 PM
 */
public class CommandSqrt implements ICommand
{
    final private String NAME = "Sqrt";

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
