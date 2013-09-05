package im.kmg.sibniar2;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: maxim
 * Date: 9/5/13
 * Time: 7:27 PM
 * To change this template use File | Settings | File Templates.
 */
public class AppStackCalculate
{
    private List<ICommand> m_commands;
    private IStackString m_stack;

    /**
     *
     *
     * @param dataComand
     * @return
     */
    public ICommand  getCommand(List<String> dataComand) throws CommandNotFoundException {
        ICommand cmd = getCommandByName(dataComand.get(0));
        cmd.init(dataComand );
        return cmd;
    }

    /**
     *
     */
    enum indexCommnad {
        Add, Commnet, Pop, Push, Sub, Define, Div, Mul, Print, Sqrt
    }

    AppStackCalculate() {
        this.m_stack = new KmgStack();

        this.m_commands = new ArrayList<ICommand>();
        //add commnad
        this.m_commands.add(new CommandAdd());
        this.m_commands.add(new CommandComment());
        this.m_commands.add(new CommandPop());
        this.m_commands.add(new CommandPush(m_stack));
        this.m_commands.add(new CommandSub());
        this.m_commands.add(new CommnadDefine());
        this.m_commands.add(new CommnadDivision());
        this.m_commands.add(new CommnadMultiplication());
        this.m_commands.add(new CommnadPrint());
        this.m_commands.add(new CommnadSqrt());

    }


    /**
     *
     * @param cmd
     * @return  - command obj or null;
     */
    public ICommand getCommandByName(String cmd) throws CommandNotFoundException {
        ICommand retv = null;
        for (ICommand s : this.m_commands) {
            if (s.getName().compareTo(cmd) == 0) {
                retv = s;
                break;
            }
        }
        if (retv == null) {
            throw new CommandNotFoundException();
        }
        return retv;
    }

    /**
     *
     * @param cmdLine
     * @return
     */
    public ICommand parserCommand(String cmdLine) {
        ICommand retv = null;
        return retv;
    }
}
