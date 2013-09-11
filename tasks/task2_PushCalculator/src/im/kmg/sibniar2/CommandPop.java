package im.kmg.sibniar2;


import java.util.List;
import java.util.Stack;

/**
 * Created with IntelliJ IDEA.
 * User: KurbatovM
 * Date: 9/5/13
 * Time: 4:02 PM
 */
public class CommandPop extends BaseCommand
{
    private final Stack<String> m_stack;

    CommandPop(Stack<String> stack) {
        super("POP");
        m_stack = stack;
    }

    @Override
    public void execute() {
        System.out.println(m_stack.pop());
    }

    @Override
    public void init(List<String> dataCommand) throws BadParamException {
        if ( dataCommand.size() != 1) {
            throw new BadParamException("Command \"" + this.getName() + "\" do not have param");
        }
        if ( (m_stack == null) || (m_stack.size() <= 0) ) {
            throw new BadParamException("Stack empty");
        }
    }

    @Override
    public String getHelp() {
        return this.getName() + "\t\t\t-\tTake Top Value from stack\n";
    }
}
