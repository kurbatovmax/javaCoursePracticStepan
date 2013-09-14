package im.kmg.sibniar2.commands;

import im.kmg.sibniar2.BadParamException;
import im.kmg.sibniar2.IKMGStack;

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

    public CommandPush(CommandDataContainer data) {
        super("PUSH", data);
    }

    @Override
    public void execute(List<String> commandWithArg) throws BadParamException {
        validate(commandWithArg);
        this.getStack().push(m_param.toString());
    }

    @Override
    public String getHelp() {
        return this.getName() + " NUMBER\t\t-\tAdd NUMBER to top stack\n";
    }

    private void validate(List<String> commandWithArg) throws BadParamException{
        if ( commandWithArg.size() != 2 ) {
            throw new BadParamException(this.getName() + " command take 1 param");
        }

        String sparam = commandWithArg.get(1);
        try {
            m_param = Double.valueOf(sparam);
        } catch (NumberFormatException e) {
            //if ( m_define.hasDefineVar(sparam) == true) {
            //    m_param = m_define.getDefineVar(sparam);
            //} else {
                throw new BadParamException("The second parameter must be a number");
            //}
        }
    }
}
