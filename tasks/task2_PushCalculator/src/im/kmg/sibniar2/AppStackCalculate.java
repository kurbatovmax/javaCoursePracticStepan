package im.kmg.sibniar2;

import im.kmg.sibniar2.commands.*;


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
    private CommandDataContainer m_data;

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
     * commandWithArg List cmd with param
     * @param commandWithArg
     * @throws CommandNotFoundException
     * @throws ExceptionStackEmpty
     * @throws ExceptionBadParam
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
