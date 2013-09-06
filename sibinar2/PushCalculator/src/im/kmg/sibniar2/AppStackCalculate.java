package im.kmg.sibniar2;

import java.util.ArrayList;
import java.util.List;

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

    /**
     *
     */
    AppStackCalculate() {
        IStackString m_stack = new KmgStack();
        this.m_commands = new ArrayList<ICommand>();

        //add commands
        this.m_commands.add(new CommandHelp(m_commands));
        this.m_commands.add(new CommandExit());
        this.m_commands.add(new CommandVersion(MainPushCalculator.VERSION));
        this.m_commands.add(new CommandDumpStack(m_stack));
        this.m_commands.add(new CommandComment());
        this.m_commands.add(new CommandAdd(m_stack));
        this.m_commands.add(new CommandPop(m_stack));
        this.m_commands.add(new CommandPush(m_stack));
        this.m_commands.add(new CommandSub());
        this.m_commands.add(new CommandDefine());
        this.m_commands.add(new CommandDivision());
        this.m_commands.add(new CommandMultiplication());
        this.m_commands.add(new CommandPrint(m_stack));
        this.m_commands.add(new CommandSqrt());
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
}