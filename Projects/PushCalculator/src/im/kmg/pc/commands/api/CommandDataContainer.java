package im.kmg.pc.commands.api;

import java.util.List;
import java.util.Stack;

/**
 * Created with IntelliJ IDEA.
 * User: maxim
 * Date: 9/14/13
 * Time: 6:44 AM
 */
public class CommandDataContainer
{
    private final List<ICommand> m_commands;
    private final Stack<String> m_stack;

    public CommandDataContainer(List<ICommand> commands, Stack<String> stack) {
        this.m_commands = commands;
        this.m_stack = stack;
    }

    public List<ICommand> getCommands()  {
        return m_commands;
    }

    public Stack<String> getStack() {
        return m_stack;
    }
}
