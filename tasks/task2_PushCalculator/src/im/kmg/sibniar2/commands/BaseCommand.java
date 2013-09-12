package im.kmg.sibniar2.commands;

import im.kmg.sibniar2.BadParamException;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: kurbatov
 * Date: 11.09.13
 * Time: 18:19
 */
public abstract class BaseCommand implements ICommand
{
    private final String NAME;

    BaseCommand(String name) {
        NAME = name;
    }

    @Override
    public String getName() {
        return NAME;
    }

    @Override
    public abstract void execute();

    @Override
    public abstract void init(List<String> dataCommand) throws BadParamException;

    @Override
    public abstract String getHelp();
}
