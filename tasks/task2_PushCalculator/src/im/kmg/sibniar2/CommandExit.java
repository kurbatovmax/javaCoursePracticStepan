package im.kmg.sibniar2;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: KurbatovM
 * Date: 9/6/13
 * Time: 9:26 AM
 */
public class CommandExit implements ICommand
{
    final private String NAME = "Exit";

    @Override
    public String getName() {
        return NAME;
    }

    @Override
    public void execute() {
        System.out.println("Good bay");
        MainPushCalculator.running = false;
    }

    @Override
    public void init(List<String> dataCommand) {}

    @Override
    public String getHelp() {
        return NAME + "\t\t\t-\texit application\n";
    }
}
