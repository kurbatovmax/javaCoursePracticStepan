package im.kmg.sibniar2;

import java.util.List;
import java.util.Stack;

/**
 * Created with IntelliJ IDEA.
 * User: KurbatovM
 * Date: 9/5/13
 * Time: 4:03 PM
 */
public class CommandAdd implements ICommand
{
    private final String NAME = "Add";
    private final Stack<String> m_stack;
    private final ICommandDefine m_define;
    private String m_param;


    /**
     *
     * @param stack object stack
     * @param define  object define
     */
    CommandAdd(Stack<String> stack, ICommandDefine define) {
        m_stack = stack;
        m_define = define;
    }

    @Override
    public String getName() {
        return NAME;
    }

    @Override
    public void execute() {
        if ( m_stack.size() > 0 ) {
            Double TopStackValue = Double.valueOf(m_stack.pop());
            m_param = Double.toString( Double.valueOf(m_param) + TopStackValue );
            m_stack.push(m_param);
        }
    }

    /**
     *
     * @param dataCommand  list cmd with arg
     * @throws BadParamException
     */
    @Override
    public void init( List<String> dataCommand ) throws BadParamException {

        if ( (m_stack == null) ||  (m_stack.size() <= 0) ) {
            throw new BadParamException("Stack empty");
        }

        if ( dataCommand.size() != 2 ) {
            throw new BadParamException(NAME + " command take 1 param");
        }

        try {
            Double.valueOf( dataCommand.get(1) );
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
