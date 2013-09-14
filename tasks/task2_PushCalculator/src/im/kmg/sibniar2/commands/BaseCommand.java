package im.kmg.sibniar2.commands;


import im.kmg.sibniar2.IKMGStack;

import java.util.List;

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
     * @param name
     * @param data
     */
    BaseCommand(String name, CommandDataContainer data) {
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
     * @return
     */
    public IKMGStack getStack() {
        return this.m_data.getStack();
    }

    public List<ICommand> getCommands() {
        return m_data.getCommands();
    }
}
