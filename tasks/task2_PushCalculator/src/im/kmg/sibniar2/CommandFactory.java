package im.kmg.sibniar2;

import im.kmg.sibniar2.commands.CommandHelp;
import im.kmg.sibniar2.commands.ICommand;

/**
 * Created with IntelliJ IDEA.
 * User: maxim
 * Date: 9/13/13
 * Time: 11:36 PM
 */
public class CommandFactory implements ICommandFactory
{
    @Override
    public ICommand Instance(String nameCommand) {
        switch (nameCommand) {
            case "HELP": {
                return createHelp();
            }
            case "POP": {
                return createPop();
            }
        }
        return null;
    }

    private ICommand createHelp() {
        return null;// new CommandHelp();
    }

    /**
     *
     * @return
     */
    private ICommand createPop() {
        return null;
    }
}
