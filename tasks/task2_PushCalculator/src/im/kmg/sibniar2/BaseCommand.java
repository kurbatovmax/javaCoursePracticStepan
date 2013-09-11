package im.kmg.sibniar2;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: user
 * Date: 11.09.13
 * Time: 18:19
 * To change this template use File | Settings | File Templates.
 */
public abstract class BaseCommand implements ICommand
{
    private final String NAME;

    protected BaseCommand(String name) {
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
