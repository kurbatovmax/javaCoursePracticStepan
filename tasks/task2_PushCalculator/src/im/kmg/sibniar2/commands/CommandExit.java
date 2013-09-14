package im.kmg.sibniar2.commands;

import im.kmg.sibniar2.BadParamException;
import im.kmg.sibniar2.IKMGStack;
import im.kmg.sibniar2.MainPushCalculator;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: KurbatovM
 * Date: 9/6/13
 * Time: 9:26 AM
 */
public class CommandExit extends BaseCommand
{
    /**
     *
     * @param data
     */
    public CommandExit(CommandDataContainer data) {
        super("EXIT", data);
    }

    @Override
    public void execute(List<String> commandWithArg) throws BadParamException {
        System.out.println("Good bay");
        MainPushCalculator.running = false;
    }

    @Override
    public String getHelp() {
        return this.getName() + "\t\t\t-\texit application\n";
    }
}
