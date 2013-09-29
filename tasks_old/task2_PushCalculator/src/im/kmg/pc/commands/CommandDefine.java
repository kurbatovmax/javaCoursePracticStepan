package im.kmg.pc.commands;

import im.kmg.pc.commands.api.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: maxim
 * Date: 9/7/13
 * Time: 7:15 PM
 */
public class CommandDefine extends BaseCommand
{
    private static final Map<String, Double> m_listDefine;
    static {
        m_listDefine = new HashMap<>();
    }

    private ICommand m_orgCommand;
    private boolean m_isInit;

    /**
     *
     */
    public CommandDefine() {
        super("DEFINE", null);
        m_isInit = false;
    }

    /**
     *
     * @param data shared data
     */
    public CommandDefine(CommandDataContainer data) {
        super("DEFINE", data);
    }

    /**
     * When exe this method add var to list
     */
    @Override
    public void execute(List<String> commandWithArg) throws ExceptionBadParam, ExceptionStackEmpty {

        // test on list command nad replace it
        if (m_isInit == false) {
            init();
            m_isInit = true;
        }

        //
        // define
        //
        if (commandWithArg.size() > 0) {
            String cmd = commandWithArg.get(0);
            if (cmd.equals(super.getName())) {
                validate_def(commandWithArg);
                System.out.println("");
                m_listDefine.put(commandWithArg.get(1), Double.valueOf(commandWithArg.get(2)));
                return;
            }

            //
            String orgCmdName = m_orgCommand.getName();
            if(cmd.equals(orgCmdName)) {
                try {
                    m_orgCommand.execute(commandWithArg);
                } catch (ExceptionBadParam e) {
                    String key = commandWithArg.get(1);
                    if ( m_listDefine.containsKey(key)) {
                        commandWithArg.set(1, m_listDefine.get(key).toString());
                        m_orgCommand.execute(commandWithArg);
                    } else {
                        throw e;
                    }
                }
            }
        }
    }

    /**
     *
     */
    private void init() {
        List<ICommand> all = this.getCommands();
        for (int i=0; i < all.size() ; i++) {
            ICommand item = all.get(i);
            if ( item != this) {
                CommandDefine t = new CommandDefine();
                t.m_orgCommand = item;
                t.m_isInit = true;
                all.set(i, t);
            }
        }
    }

    /**
     * @param commandWithArg - [0] cmd NAME, .... param
     * @throws ExceptionBadParam  - if have problem
     */
    private void validate_def(List<String> commandWithArg) throws ExceptionBadParam {
        final String NAME = this.getName();
        if (commandWithArg.size() != 3) {
            throw new ExceptionBadParam(NAME + " command must 2 param");
        }

        if ( isNumber(commandWithArg.get(1)) ) {
            throw new ExceptionBadParam(NAME + " Bad name var cannot be number");
        }

        if (!isNumber(commandWithArg.get(2) ) ) {
            throw new ExceptionBadParam(NAME + " Command tow param must be number");
        }
    }

    /**
     * @return help about cmd
     */
    @Override
    public String getHelp() {
        if (this.m_orgCommand == null)  {
            return this.getName() + " NAME_VAR NUMBER\t\t-\tAdded define NAME_VAR\n";
        }
        return  m_orgCommand.getHelp();
    }

    /**
     *
     * @return  help about cmd
     */
    public String getName() {
        if (this.m_orgCommand == null) {
            return super.getName();
        }
        return  m_orgCommand.getName();
    }

    /**
     *
     * @param value   string value
     * @return  - true if string value can convert to Double
     */
    private boolean isNumber(String value) {
        boolean retv = true;
        try {
            Double.valueOf(value);
        }catch (NumberFormatException e) {
            retv = false;
        }
        return retv;
    }
}

