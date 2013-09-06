package im.kmg.sibniar2;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: KurbatovM
 * Date: 9/5/13
 * Time: 4:00 PM
 */
public class CommandPush implements ICommand
{
    final private String NAME = "Push";
    private final IStackString m_stack;
    private String m_param = null;

    public CommandPush(IStackString stack) {
        m_stack = stack;
    }

    @Override
    public String getName() {
        return NAME;
    }

    @Override
    public void execute() {
        if (m_param != null) {
            m_stack.Push(m_param);
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
            throw new BadParamException("Push command take 1 param");
        }

        try {
            Integer.valueOf(dataCommand.get(1));
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
