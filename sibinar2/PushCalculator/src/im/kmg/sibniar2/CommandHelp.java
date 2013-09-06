package im.kmg.sibniar2;

import java.util.List;

import static java.lang.System.out;

/**
 * Created with IntelliJ IDEA.
 * User: KurbatovM
 * Date: 9/6/13
 * Time: 9:42 AM
 */
public class CommandHelp implements ICommand
{
    final private String NAME = "Help";

    private final List<ICommand> m_commands;

    /**
     *
     * @param commands - List of commands
     */
    public CommandHelp(List<ICommand> commands) {
        m_commands = commands;
    }

    @Override
    public String getName() {
        return NAME;
    }

    @Override
    public void execute() {
        String str = "";
        str += "====== Stack calculator =======\n";
        str += "version: " + MainPushCalculator.VERSION + "\n";
        str += "--------------------------------\n";
        for(ICommand s : m_commands) {
            str += s.getHelp();
        }
        out.println(str);
    }

    @Override
    public void init(List<String> dataCommand) {}

    @Override
    public String getHelp() {
        return NAME + "\t\t\t-\tprint this message\n";
    }
}