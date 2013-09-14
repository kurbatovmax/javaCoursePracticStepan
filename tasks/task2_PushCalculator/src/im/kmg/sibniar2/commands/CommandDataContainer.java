package im.kmg.sibniar2.commands;

import im.kmg.sibniar2.IKMGStack;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: maxim
 * Date: 9/14/13
 * Time: 6:44 AM
 */
public class CommandDataContainer
{
    private final List<ICommand> m_commands;
    private final IKMGStack m_stack;

    public CommandDataContainer(List<ICommand> commands, IKMGStack stack) {
        this.m_commands = commands;
        this.m_stack = stack;
    }

    public List<ICommand> getCommands()  {
        return m_commands;
    }

    public IKMGStack getStack() {
        return m_stack;
    }
}
