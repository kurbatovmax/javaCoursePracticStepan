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
public class CommandDefine implements
        ICommand,
        ICommandDefine
{
    private final String NAME = "Define";
    private final Map<String, Double> m_listDefine;
    private Pair<String, Double> m_value;

    public CommandDefine() {
        this.m_listDefine = new HashMap<String, Double>();
    }

    /**
     *
     * @return - name command as string
     */
    @Override
    public String getName() {
        return NAME;
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
        if (dataCommand.size() != 3) {
            throw new BadParamException(NAME + " command must 2 param");
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
        return NAME + " NAME_VAR NUMBER\t\t-\tAdded define NAME_VAR\n";
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

