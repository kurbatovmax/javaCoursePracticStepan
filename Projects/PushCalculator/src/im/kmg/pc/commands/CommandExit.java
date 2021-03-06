package im.kmg.pc.commands;

import im.kmg.pc.MainPushCalculator;
import im.kmg.pc.commands.api.BaseCommand;
import im.kmg.pc.commands.api.CommandDataContainer;
import im.kmg.pc.commands.api.ExceptionBadParam;
import im.kmg.pc.commands.api.ExceptionStackEmpty;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: KurbatovM
 * Date: 9/6/13
 * Time: 9:26 AM
 */
public class CommandExit extends BaseCommand
{
    public CommandExit() {
        super("EXIT", null);
    }

    public CommandExit(CommandDataContainer data) {
        super("EXIT", data);
    }

    @Override
    public void execute(List<String> commandWithArg) throws ExceptionBadParam, ExceptionStackEmpty {
        validate(commandWithArg);
        System.out.println("Good bay");
        MainPushCalculator.running = false;
    }

    /**
     * Validate input data
     * @param commandWithArg cmd and arg
     * @throws ExceptionBadParam
     */
    private void validate(List<String> commandWithArg) throws ExceptionBadParam {
        if ( commandWithArg.size() > 1) {
            throw new ExceptionBadParam("Command don't have param");
        }
    }

    @Override
    public String getHelp() {
        return this.getName() + "\t\t\t-\texit application\n";
    }
}
