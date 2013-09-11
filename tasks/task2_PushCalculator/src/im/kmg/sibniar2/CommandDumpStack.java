package im.kmg.sibniar2;

import java.util.List;
import java.util.Stack;

/**
 * Created with IntelliJ IDEA.
 * User: KurbatovM
 * Date: 9/6/13
 * Time: 10:18 AM
 */
public class CommandDumpStack extends BaseCommand
{
    private final Stack<String> m_stack;

    public CommandDumpStack(Stack<String> stack) {
        super("DUMPS");
        m_stack = stack;
    }

    @Override
    public void execute() {
        System.out.println(m_stack);
    }

    @Override
    public void init(List<String> dataCommand) throws BadParamException {
        if ( dataCommand.size() != 1) {
            throw new BadParamException("Command \"" + this.getName() + "\" Do not have param");
        }
    }

    @Override
    public String getHelp() {
        return getName() + "\t\t-\tShow all stack as String\n";
    }
}
