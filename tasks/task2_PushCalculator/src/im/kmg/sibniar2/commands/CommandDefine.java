package im.kmg.sibniar2.commands;

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
    private final Map<String, Double> m_listDefine;
    public ICommand m_orgCommand;
    private boolean m_isInit;

    public CommandDefine() {
        super("DEFINE", null);
        this.m_listDefine = new HashMap<String, Double>();
        m_isInit = false;
    }

    public CommandDefine(CommandDataContainer data) {
        super("DEFINE", data);
        this.m_listDefine = new HashMap<String, Double>();
    }

    /**
     * When exe this method add var to list
     */
    @Override
    public void execute(List<String> commandWithArg) throws ExceptionBadParam {
        if (m_isInit == false) {
            init();
        }

        if (commandWithArg.size() > 0) {

        }


        validate(commandWithArg);
        System.out.println("");
        m_listDefine.put(commandWithArg.get(1), Double.valueOf(commandWithArg.get(2)));
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
                all.set(i, t);
            }
        }
    }

    /**
     * @param commandWithArg - [0] cmd NAME, .... param
     * @param commandWithArg
     * @throws ExceptionBadParam
     */
    private void validate(List<String> commandWithArg) throws ExceptionBadParam {
        final String NAME = this.getName();
        if (commandWithArg.size() != 3) {
            throw new ExceptionBadParam(NAME + " command must 2 param");
        }

        boolean isBadNameVAR = true;

        try {
            Double.valueOf(commandWithArg.get(1));
        } catch (NumberFormatException e) {
            isBadNameVAR = false;
        }
        if (isBadNameVAR) {
            throw new ExceptionBadParam(NAME + " Bad name var");
        }

        try {
            Double.valueOf(commandWithArg.get(2));
        } catch (NumberFormatException e) {
            throw new ExceptionBadParam(NAME + " Command tow param must be number");
        }
    }

    @Override
    public String getHelp() {
        return this.getName() + " NAME_VAR NUMBER\t\t-\tAdded define NAME_VAR\n";
    }

    private boolean isDefineCmd(List<String> commandWithArg) {
        if (commandWithArg != null && commandWithArg.size() == 3 ) {
            return true;
        }
        return false;
    }

    public String getName() {
        if (this.m_orgCommand == null) {
            return super.getName();
        }
        return  m_orgCommand.getName();
    }
}

