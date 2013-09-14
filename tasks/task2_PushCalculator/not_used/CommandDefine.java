package im.kmg.sibniar2.commands;

// my
import im.kmg.sibniar2.BadParamException;

// std
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: maxim
 * Date: 9/7/13
 * Time: 7:15 PM
 */
public class CommandDefine extends BaseCommand implements ICommandDefine
{
    private final Map<String, Double> m_listDefine;

    public CommandDefine() {
        super("DEFINE", null);
        this.m_listDefine = new HashMap<String, Double>();
    }

    /**
     * When exe this method add var to list
     */
    @Override
    public void execute(List<String> commandWithArg) throws BadParamException{
        validate(commandWithArg);
        System.out.println("");
        m_listDefine.put(commandWithArg.get(1), Double.valueOf(commandWithArg.get(2)));
    }

    /**
     *
     * @param commandWithArg - [0] cmd NAME, .... param
     * @throws im.kmg.sibniar2.BadParamException
     */
    private void validate(List<String> commandWithArg) throws BadParamException {
        final String NAME = this.getName();
        if (commandWithArg.size() != 3) {
            throw new BadParamException(NAME + " command must 2 param");
        }

        boolean isBadNameVAR = true;

        try {
            Double.valueOf(commandWithArg.get(1));
        } catch (NumberFormatException e) {
            isBadNameVAR = false;
        }
        if (isBadNameVAR) {
            throw new BadParamException(NAME + " Bad name var");
        }

        try {
            Double.valueOf(commandWithArg.get(2));
        } catch (NumberFormatException e) {
            throw new BadParamException(NAME + " Command tow param must be number");
        }
    }

    @Override
    public String getHelp() {
        return this.getName() + " NAME_VAR NUMBER\t\t-\tAdded define NAME_VAR\n";
    }

    @Override
    public boolean hasDefineVar(String nameVar) {
        return m_listDefine.containsKey(nameVar);
    }

    @Override
    public Double getDefineVar(String nameVar) {
        return m_listDefine.get(nameVar);
    }
}

