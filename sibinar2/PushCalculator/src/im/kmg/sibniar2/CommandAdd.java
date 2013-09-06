package im.kmg.sibniar2;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: KurbatovM
 * Date: 9/5/13
 * Time: 4:03 PM
 */
public class CommandAdd implements ICommand
{
    final private String NAME = "Add";
    private final IStackString m_stack;
    private String m_param;

    CommandAdd(IStackString stack) {
        m_stack = stack;
    }

    @Override
    public String getName() {
        return NAME;
    }

    @Override
    public void execute() {
        if ( m_stack.size() > 0 ) {
            Integer TopStackValue = Integer.valueOf(m_stack.Pop());

            m_param = Integer.toString( Integer.valueOf(m_param) + TopStackValue );
            m_stack.Push(m_param);
        }
    }

    /**
     *
     * @param dataCommand  list cmd with arg
     * @throws BadParamException
     */
    @Override
    public void init( List<String> dataCommand ) throws BadParamException {
        if ( dataCommand.size() != 2 ) {
            throw new BadParamException("Add (+) command take 1 param");
        }

        try {
            Integer.valueOf( dataCommand.get(1) );
        } catch (NumberFormatException e) {
            throw new BadParamException("The second parameter must be a number");
        }

        m_param =  dataCommand.get(1);
    }

    @Override
    public String getHelp() {
        return NAME + " NUMBER\t\t-\tAdded NUMBER to Top Value from stack, top value replace new value\n";
    }
}
