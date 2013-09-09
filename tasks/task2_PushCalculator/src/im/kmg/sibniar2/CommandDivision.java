package im.kmg.sibniar2;

import java.util.List;
import java.util.Stack;

/**
 * Created with IntelliJ IDEA.
 * User: KurbatovM
 * Date: 9/5/13
 * Time: 4:06 PM
 */
public class CommandDivision implements ICommand
{
    private final String NAME = "Div";
    private String m_param;
    private final Stack<String> m_stack;
    private final ICommandDefine m_define;

    public CommandDivision(Stack<String> stack, ICommandDefine define) {
        this.m_stack = stack;
        this.m_define = define;
    }

    @Override
    public String getName() {
        return NAME;
    }

    /**
     * Note: Division on zero result infinity !!!!
     */
    @Override
    public void execute() {
        Double TopStackValue = Double.valueOf(m_stack.pop());
        m_param = Double.toString( Double.valueOf(m_param) / TopStackValue );
        m_stack.push(m_param);
    }

    @Override
    public void init(List<String> dataCommand) throws BadParamException {
        if ( (m_stack == null) ||  (m_stack.size() <= 0) ) {
            throw new BadParamException("Stack empty");
        }

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
        return NAME + " NUMBER\t\t-\tNUMBER division on top value from stack result store stack\n";
    }
}
