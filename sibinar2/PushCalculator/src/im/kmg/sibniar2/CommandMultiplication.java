package im.kmg.sibniar2;

import java.util.List;
import java.util.Stack;

/**
 * Created with IntelliJ IDEA.
 * User: KurbatovM
 * Date: 9/5/13
 * Time: 4:06 PM
 */
public class CommandMultiplication implements ICommand
{
    final private String NAME = "Mul";
    private String m_param;
    private Stack<String> m_stack;

    public CommandMultiplication(Stack<String> stack) {
        m_stack = stack;
    }

    @Override
    public String getName() {
        return NAME;
    }

    @Override
    public void execute() {
        Double TopStackValue = Double.valueOf(m_stack.pop());
        m_param = Double.toString( Double.valueOf(m_param) * TopStackValue );
        m_stack.push(m_param);
    }

    @Override
    public void init(List<String> dataCommand) throws BadParamException {
        if ( (m_stack == null) ||  (m_stack.size() <= 0) ) {
            throw new BadParamException("Stack empty");
        }

        if ( dataCommand.size() != 2 ) {
            throw new BadParamException("Mul command take 1 param");
        }

        try {
            Double.valueOf(dataCommand.get(1));
        } catch (NumberFormatException e) {
            throw new BadParamException("The second parameter must be a number");
        }

        m_param =  dataCommand.get(1);
    }

    /**
     *
     * @return Help as string for automation generation help.
     */
    @Override
    public String getHelp() {
        return NAME + " NUMBER\t\t-\tTake top value from stack and multiplication  NUMBER, result store to stack\n";
    }
}
