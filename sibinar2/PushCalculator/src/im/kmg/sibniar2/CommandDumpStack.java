package im.kmg.sibniar2;

import java.util.List;
import java.util.Stack;

/**
 * Created with IntelliJ IDEA.
 * User: KurbatovM
 * Date: 9/6/13
 * Time: 10:18 AM
 */
public class CommandDumpStack implements ICommand
{
    private final Stack<String> m_stack;

    public CommandDumpStack(Stack<String> stack) {
        m_stack = stack;
    }

    final private String NAME = "DumpStack";

    @Override
    public String getName() {
        return NAME;
    }

    @Override
    public void execute() {
        System.out.println(m_stack);
    }

    @Override
    public void init(List<String> dataCommand) throws BadParamException {
        if ( dataCommand.size() != 1) {
            throw new BadParamException("Command \"" + NAME + "\" Do not have param");
        }
    }

    @Override
    public String getHelp() {
        return NAME + "\t\t-\tShow all stack as String\n";
    }
}
