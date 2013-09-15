package im.kmg.pc.commands;


import im.kmg.pc.commands.api.BaseCommand;
import im.kmg.pc.commands.api.CommandDataContainer;
import im.kmg.pc.commands.api.ExceptionBadParam;
import im.kmg.pc.commands.api.ExceptionStackEmpty;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: KurbatovM
 * Date: 9/5/13
 * Time: 4:00 PM
 */
public class CommandPush extends BaseCommand
{
    private Double m_param;

    public CommandPush() {
        super("PUSH", null);
    }

    public CommandPush(CommandDataContainer data) {
        super("PUSH", data);
    }

    @Override
    public void execute(List<String> commandWithArg) throws ExceptionBadParam, ExceptionStackEmpty {
        validate(commandWithArg);
        this.getStack().push(m_param.toString());
    }

    @Override
    public String getHelp() {
        return this.getName() + " NUMBER\t\t-\tAdd NUMBER to top stack\n";
    }

    private void validate(List<String> commandWithArg) throws ExceptionBadParam{
        if ( commandWithArg.size() != 2 ) {
            throw new ExceptionBadParam(this.getName() + " command take 1 param");
        }

        String sparam = commandWithArg.get(1);
        try {
            m_param = Double.valueOf(sparam);
        } catch (NumberFormatException e) {
            throw new ExceptionBadParam("The second parameter must be a number");
        }
    }
}
