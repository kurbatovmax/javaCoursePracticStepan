package im.kmg.sibniar2;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: KurbatovM
 * Date: 9/6/13
 * Time: 9:26 AM
 */
public class CommandExit extends BaseCommand
{
    protected CommandExit() {
        super("EXIT");
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
        return this.getName() + "\t\t\t-\texit application\n";
    }
}
