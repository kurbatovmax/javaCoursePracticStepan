package im.kmg.sibniar2;


import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: KurbatovM
 * Date: 9/5/13
 * Time: 4:02 PM
 */
public class CommandPop implements ICommand
{
    final private String NAME = "Pop";
    private final IStackString m_stack;

    CommandPop(IStackString stack) {
        m_stack = stack;
    }

    @Override
    public String getName() {
        return NAME;
    }

    @Override
    public void execute() {
        System.out.println(m_stack.Pop());
    }

    @Override
    public void init(List<String> dataCommand) throws BadParamException {
        if ( dataCommand.size() != 1) {
            throw new BadParamException("Command \"Pop\" do not have param");
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
