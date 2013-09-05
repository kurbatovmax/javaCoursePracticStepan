package im.kmg.sibniar2;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: KurbatovM
 * Date: 9/5/13
 * Time: 4:00 PM
 * To change this template use File | Settings | File Templates.
 */
public class CommandPush implements ICommand
{
    final private String NAME = "Push";
    private IStackString m_stack;
    public String m_param = "";

    public CommandPush(IStackString stack) {
        m_stack = stack;
    }


    @Override
    public String getName() {
        return NAME;
    }

    @Override
    public void execute() {
        //System.out.println("this " + this.getName());
        m_stack.Push(m_param);
    }

    @Override
    public void init(List<String> dataCommand) {
        m_param = dataCommand.get(1);
    }
}
