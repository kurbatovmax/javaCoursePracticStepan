package im.kmg.pc.commands;


import im.kmg.pc.commands.api.BaseCommand;
import im.kmg.pc.commands.api.CommandDataContainer;
import im.kmg.pc.commands.api.ExceptionBadParam;
import im.kmg.pc.commands.api.ExceptionStackEmpty;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: KurbatovM
 * Date: 9/6/13
 * Time: 10:18 AM
 */
public class CommandDumpStack extends BaseCommand
{

    public CommandDumpStack() {
        super("DUMPS", null);
    }

    @SuppressWarnings("UnusedDeclaration")
    public CommandDumpStack(CommandDataContainer data) {
        super("DUMPS", data);
    }

    @Override
    public void execute(List<String> commandWithArg) throws ExceptionBadParam, ExceptionStackEmpty {
        validata(commandWithArg);
        System.out.println(this.getStack());
    }

    /**
     *
     * @param commandWithArg - [0] cmd NAME, .... param
     * @throws im.kmg.pc.commands.api.ExceptionBadParam
     */
    private void validata(List<String> commandWithArg) throws ExceptionBadParam {
        if ( commandWithArg.size() != 1) {
            throw new ExceptionBadParam("Command \"" + this.getName() + "\" Do not have param");
        }
    }

    @Override
    public String getHelp() {
        return getName() + "\t\t-\tShow all stack as String\n";
    }
}
