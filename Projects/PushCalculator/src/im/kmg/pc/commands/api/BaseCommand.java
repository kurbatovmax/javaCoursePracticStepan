package im.kmg.pc.commands.api;
import java.util.List;
import java.util.Stack;

/**
 * Created with IntelliJ IDEA.
 * User: kurbatov
 * Date: 11.09.13
 * Time: 18:19
 */
public abstract class BaseCommand implements ICommand
{
    private final String NAME;

    /**
     * This data need all object!!!
     */
    @KMGResource(type="CommandDataContainer")
    private final CommandDataContainer m_data;


    /**
     *
     * @param name - name command
     * @param data - shared data
     */
    public BaseCommand(String name, CommandDataContainer data) {
        NAME = name;
        m_data = data;
    }

    @Override
    public String getName() {
        return NAME;
    }

    @Override
    public abstract void execute(List<String> commandWithArg) throws ExceptionBadParam, ExceptionStackEmpty;

    @Override
    public abstract String getHelp();

    /**
     *
     * @return - shared stack
     */
    protected Stack<String> getStack() {
        return this.m_data.getStack();
    }

    protected List<ICommand> getCommands() {
        return m_data.getCommands();
    }
}
