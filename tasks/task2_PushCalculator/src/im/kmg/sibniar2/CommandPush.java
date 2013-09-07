package im.kmg.sibniar2;

import java.util.List;
import java.util.Stack;

/**
 * Created with IntelliJ IDEA.
 * User: KurbatovM
 * Date: 9/5/13
 * Time: 4:00 PM
 */
public class CommandPush implements ICommand
{
    final private String NAME = "Push";
    private final Stack<String> m_stack;
    private String m_param = null;

    public CommandPush(Stack<String> stack) {
        m_stack = stack;
    }

    @Override
    public String getName() {
        return NAME;
    }

    @Override
    public void execute() {
        if (m_param != null) {
            m_stack.push(m_param);
        }
    }

    /**
     *
     * @param dataCommand  List with cmd and arg
     * @throws BadParamException
     */
    @Override
    public void init(List<String> dataCommand) throws BadParamException {
        if ( dataCommand.size() != 2 ) {
            throw new BadParamException(NAME + " command take 1 param");
        }

        try {
            Double.valueOf(dataCommand.get(1));
        } catch (NumberFormatException e) {
            throw new BadParamException("The second parameter must be a number");
        }

        m_param =  dataCommand.get(1);
    }

    @Override
    public String getHelp() {
        return NAME + " NUMBER\t\t-\tAdd NUMBER to top stack\n";
    }
}
