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
    final private String NAME = "PUSH";
    private final Stack<String> m_stack;
    private final ICommandDefine m_define;
    private String m_param = null;

    public CommandPush(Stack<String> stack, ICommandDefine define) {
        m_stack = stack;
        m_define = define;
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
     * Command
     * @param dataCommand  List with cmd and arg
     * @throws BadParamException
     */
    @Override
    public void init(List<String> dataCommand) throws BadParamException {
        if ( dataCommand.size() != 2 ) {
            throw new BadParamException(NAME + " command take 1 param");
        }

        String param = dataCommand.get(1);

        try {
            Double.valueOf(param);
        } catch (NumberFormatException e) {
            if (!m_define.hasDefineVar(dataCommand.get(1))) {
                throw new BadParamException("The second parameter must be a number");
            } else {
                param = m_define.getDefineVar(dataCommand.get(1)).toString();
            }

        }

        m_param =  param;
    }

    @Override
    public String getHelp() {
        return NAME + " NUMBER\t\t-\tAdd NUMBER to top stack\n";
    }
}
