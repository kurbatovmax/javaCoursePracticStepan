package im.kmg.sibniar2.commands;

import im.kmg.sibniar2.BadParamException;
import im.kmg.sibniar2.IKMGStack;

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
    private final CommandDataContainer m_data;

    BaseCommand(String name, CommandDataContainer data) {
        NAME = name;
        m_data = data;
    }

    @Override
    public String getName() {
        return NAME;
    }

    @Override
    public abstract void execute(List<String> commandWithArg) throws BadParamException;

    @Override
    public abstract String getHelp();

    /**
     *
     * @return
     */
    public IKMGStack getStack() {
        return this.m_data.getStack();
    }

    public List<ICommand> getCommands() {
        return m_data.getCommands();
    }
}
