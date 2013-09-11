package im.kmg.sibniar2;

import java.util.List;
import java.util.Stack;

/**
 * Created with IntelliJ IDEA.
 * User: KurbatovM
 * Date: 9/5/13
 * Time: 4:08 PM
 */
public class CommandPrint extends BaseCommand
{
    private final Stack<String> m_stack;

    public CommandPrint(Stack<String> stack) {
        super("PRINT");
        this.m_stack = stack;
    }

    @Override
    public void execute() {
        System.out.println(m_stack.peek());
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
        return this.getName() + "\t\t\t-\tPrint Top value without remove, not implements yet\n";
    }
}
