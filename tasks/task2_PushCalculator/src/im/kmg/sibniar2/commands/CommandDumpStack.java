package im.kmg.sibniar2.commands;

import im.kmg.sibniar2.BadParamException;
import im.kmg.sibniar2.IKMGStack;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: KurbatovM
 * Date: 9/6/13
 * Time: 10:18 AM
 */
public class CommandDumpStack extends BaseCommand
{
    public CommandDumpStack(CommandDataContainer data) {
        super("DUMPS", data);
    }

    @Override
    public void execute(List<String> commandWithArg) throws BadParamException {
        validata(commandWithArg);
        System.out.println(this.getStack());
    }

    /**
     *
     * @param commandWithArg - [0] cmd NAME, .... param
     * @throws im.kmg.sibniar2.BadParamException
     */
    private void validata(List<String> commandWithArg) throws BadParamException {
        if ( commandWithArg.size() != 1) {
            throw new BadParamException("Command \"" + this.getName() + "\" Do not have param");
        }
    }

    @Override
    public String getHelp() {
        return getName() + "\t\t-\tShow all stack as String\n";
    }
}
