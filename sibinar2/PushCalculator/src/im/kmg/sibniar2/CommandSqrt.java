package im.kmg.sibniar2;

import java.util.List;
import java.util.Stack;

/**
 * Created with IntelliJ IDEA.
 * User: KurbatovM
 * Date: 9/5/13
 * Time: 4:07 PM
 */
public class CommandSqrt implements ICommand
{
    final private String NAME = "Sqrt";
    private final Stack<String> m_stack;

    public CommandSqrt(Stack<String> stack) {
        m_stack = stack;
    }

    @Override
    public String getName() {
        return NAME;
    }

    /**
     * Note: result may be NaN negative number !!!
     */
    @Override
    public void execute() {
        Double value =  Double.valueOf(m_stack.pop());
        value = Math.sqrt(value);
        m_stack.push(value.toString());
    }

    @Override
    public void init(List<String> dataCommand) throws BadParamException {
        if ( dataCommand.size() != 1) {
            throw new BadParamException("Command \"" + NAME + "\" do not have param");
        }
        if ( (m_stack == null) || (m_stack.size() <= 0) ) {
            throw new BadParamException("Stack empty");
        }

        try {
            Double.valueOf(m_stack.peek());
        } catch (NumberFormatException e) {
            throw new BadParamException("The second parameter must be a number");
        }
    }

    @Override
    public String getHelp() {
        return NAME + "\t\t-\tTake top value from stack and square store result to top stack\n";
    }
}
