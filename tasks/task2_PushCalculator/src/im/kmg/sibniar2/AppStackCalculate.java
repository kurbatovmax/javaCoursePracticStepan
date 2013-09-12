package im.kmg.sibniar2;

import im.kmg.sibniar2.commands.*;


import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * This invoker commands
 * Created with IntelliJ IDEA.
 * User: maxim
 * Date: 9/5/13
 * Time: 7:27 PM
 * To change this template use File | Settings | File Templates.
 */
class AppStackCalculate
{
    private final List<ICommand> m_commands;
    private final Stack<String> m_stack;

    /**
     *
     */
    AppStackCalculate() {
        this.m_stack = new Stack<String>();
        this.m_commands = new ArrayList<ICommand>();
        ICommandDefine m_define = new CommandDefine();

        //add commands
        this.m_commands.add(new CommandHelp(m_commands));
        this.m_commands.add(new CommandExit());
        this.m_commands.add(new CommandVersion(MainPushCalculator.VERSION));
        this.m_commands.add(new CommandDumpStack(m_stack));
        this.m_commands.add(new CommandAdd(m_stack, m_define));
        this.m_commands.add(new CommandPop(m_stack));
        this.m_commands.add(new CommandPush(m_stack, m_define));
        this.m_commands.add(new CommandSub(m_stack, m_define));
        this.m_commands.add(new CommandDivision(m_stack, m_define));
        this.m_commands.add(new CommandMultiplication(m_stack, m_define));
        this.m_commands.add(new CommandPrint(m_stack));
        this.m_commands.add(new CommandSqrt(m_stack));
        this.m_commands.add((ICommand) m_define);
    }

    /**
     *
     * @param cmd  command line
     * @return  - command obj or null;
     */
    ICommand getCommandByName(String cmd) throws CommandNotFoundException {
        ICommand rv = null;
        for (ICommand s : this.m_commands) {
            if (s.getName().compareTo(cmd) == 0) {
                rv = s;
                break;
            }
        }
        if (rv == null) {
            throw new CommandNotFoundException();
        }
        return rv;
    }

    /**
     *
     * @param CmdAndParam List cmd with param
     */
    public void executeCommand(List<String> CmdAndParam) throws
            CommandNotFoundException,
            BadParamException
    {
        String sNameCmd;
        if ( (CmdAndParam == null) || (CmdAndParam.size() < 1)) {
            throw new CommandNotFoundException();
        }

        sNameCmd = CmdAndParam.get(0);
        ICommand cmd = this.getCommandByName(sNameCmd);
        cmd.init(CmdAndParam);
        cmd.execute();
    }

    /**
     *
     * @return - stack.
     */
    public Stack<String> getStack() {
          return m_stack;
    }
}
