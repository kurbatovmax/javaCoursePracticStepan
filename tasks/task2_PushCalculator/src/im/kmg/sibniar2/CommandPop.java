package im.kmg.sibniar2;


import java.util.List;
import java.util.Stack;

/**
 * Created with IntelliJ IDEA.
 * User: KurbatovM
 * Date: 9/5/13
 * Time: 4:02 PM
 */
public class CommandPop implements ICommand
{
    final private String NAME = "Pop";
    private final Stack<String> m_stack;

    CommandPop(Stack<String> stack) {
        m_stack = stack;
    }

    @Override
    public String getName() {
        return NAME;
    }

    @Override
    public void execute() {
        System.out.println(m_stack.pop());
    }

    @Override
    public void init(List<String> dataCommand) throws BadParamException {
        if ( dataCommand.size() != 1) {
            throw new BadParamException("Command \"" + NAME + "\" do not have param");
        }
        if ( (m_stack == null) || (m_stack.size() <= 0) ) {
            throw new BadParamException("Stack empty");
        }
    }

    @Override
    public String getHelp() {
        return NAME + "\t\t\t-\tTake Top Value from stack\n";
    }
}
