package im.kmg.sibniar2.commands;

import im.kmg.sibniar2.BadParamException;

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

    public CommandVersion() {
        super("VERSION", null);
        m_version = im.kmg.sibniar2.MainPushCalculator.VERSION;
    }

    @Override
    public void execute(List<String> commandWithArg) throws BadParamException {
        System.out.println("Version: " + m_version);
    }

    @Override
    public String getHelp() {
        return this.getName() + "\t\t\t-\tprint version\n";
    }
}
