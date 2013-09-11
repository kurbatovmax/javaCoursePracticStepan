package im.kmg.sibniar2;

import javafx.util.Pair;

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
    private final String NAME = "DEFINE";
    private final Map<String, Double> m_listDefine;
    private Pair<String, Double> m_value;

    public CommandDefine() {
        super("DEFINE");
        this.m_listDefine = new HashMap<String, Double>();
    }

    /**
     * When exe this method add var to list
     */
    @Override
    public void execute() {
        System.out.println("");
        m_listDefine.put(m_value.getKey(), m_value.getValue());
        m_value = null;
    }

    /**
     *
     * @param dataCommand
     * @throws BadParamException
     */
    @Override
    public void init(List<String> dataCommand) throws BadParamException {
        final String NAME = this.getName();
        if (dataCommand.size() != 3) {
            throw new BadParamException(NAME + " command must 2 param");
        }

        boolean isBadNameVAR = true;
        try {
            Double.valueOf(dataCommand.get(1));
        } catch (NumberFormatException e) {
            isBadNameVAR = false;
        }
        if (isBadNameVAR) {
            throw new BadParamException(NAME + " Bad name var");
        }

        try {
            Double.valueOf(dataCommand.get(2));
        } catch (NumberFormatException e) {
            throw new BadParamException(NAME + " Command tow param must be number");
        }

        m_value = new Pair<String, Double>(dataCommand.get(1), Double.valueOf(dataCommand.get(2)) );

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

