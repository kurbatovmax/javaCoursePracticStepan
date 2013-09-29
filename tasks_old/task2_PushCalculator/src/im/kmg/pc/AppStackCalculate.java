package im.kmg.pc;

import im.kmg.pc.commands.api.CommandDataContainer;
import im.kmg.pc.commands.api.ExceptionBadParam;
import im.kmg.pc.commands.api.ExceptionStackEmpty;
import im.kmg.pc.commands.api.ICommand;


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
    private final CommandDataContainer m_data;

    public AppStackCalculate(CommandDataContainer data) {
        m_data = data;
    }

    /**
     *
     * @param cmd  command line
     * @return  - command obj or null;
     */
    ICommand getCommandByName(String cmd) {
        ICommand rv = null;
        for (ICommand s : this.m_data.getCommands() ) {
            if (s.getName().compareTo(cmd) == 0) {
                rv = s;
                break;
            }
        }
        return rv;
    }

    /**
     * @param commandWithArg - commandWithArg List cmd with param
     * @throws CommandNotFoundException
     * @throws im.kmg.pc.commands.api.ExceptionStackEmpty
     * @throws im.kmg.pc.commands.api.ExceptionBadParam
     */
    public void executeCommand(List<String> commandWithArg) throws CommandNotFoundException, ExceptionStackEmpty, ExceptionBadParam {
        String sNameCmd;
        if ( (commandWithArg == null) || (commandWithArg.size() < 1)) {
            throw new CommandNotFoundException();
        }

        sNameCmd = commandWithArg.get(0);
        ICommand cmd = this.getCommandByName(sNameCmd);
        if ( cmd == null ) {
            throw new CommandNotFoundException();
        }

        cmd.execute(commandWithArg);
    }
 }
