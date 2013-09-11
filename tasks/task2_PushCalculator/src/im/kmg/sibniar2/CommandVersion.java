package im.kmg.sibniar2;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: KurbatovM
 * Date: 9/6/13
 * Time: 11:04 AM
 */
public class CommandVersion extends BaseCommand
{
    private final String m_version;

    public CommandVersion(String version) {
        super("VERSION");
        m_version = version;
    }

    @Override
    public void execute() {
        System.out.println("Version: " + m_version);
    }

    @Override
    public void init(List<String> dataCommand) throws BadParamException {}

    @Override
    public String getHelp() {
        return this.getName() + "\t\t\t-\tprint version\n";
    }
}
