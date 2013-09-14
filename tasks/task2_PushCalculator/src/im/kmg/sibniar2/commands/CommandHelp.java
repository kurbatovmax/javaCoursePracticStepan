package im.kmg.sibniar2.commands;

import im.kmg.sibniar2.BadParamException;
import im.kmg.sibniar2.MainPushCalculator;
import java.util.List;
import static java.lang.System.out;

/**
 * Created with IntelliJ IDEA.
 * User: KurbatovM
 * Date: 9/6/13
 * Time: 9:42 AM
 */
public class CommandHelp extends BaseCommand
{
    public CommandHelp(CommandDataContainer data) {
        super("HELP", data);
    }

    @Override
    public void execute(List<String> commandWithArg) throws BadParamException {
        String str = "\n";
        str += "====== Stack calculator HELP =======\n";
        for(ICommand s : this.getCommands() ) {
            str += s.getHelp();
        }
        out.println(str);
    }

    @Override
    public String getHelp() {
        return getName() + "\t\t\t-\tprint this message\n";
    }
}
