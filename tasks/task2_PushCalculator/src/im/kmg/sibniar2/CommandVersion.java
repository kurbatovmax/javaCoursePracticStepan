package im.kmg.sibniar2;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: KurbatovM
 * Date: 9/6/13
 * Time: 11:04 AM
 */
public class CommandVersion implements ICommand
{
    final private String NAME = "VERSION";
    private final String m_version;

    public CommandVersion(String version) {
        m_version = version;
    }

    @Override
    public String getName() {
        return NAME;
    }

    @Override
    public void execute() {
        System.out.println("Version: " + m_version);
    }

    @Override
    public void init(List<String> dataCommand) throws BadParamException {}

    @Override
    public String getHelp() {
        return NAME + "\t\t\t-\tprint version\n";
    }
}
