package im.kmg.pc.commands;

import im.kmg.pc.commands.api.BaseCommand;
import im.kmg.pc.commands.api.ExceptionBadParam;
import im.kmg.pc.commands.api.ExceptionStackEmpty;

import java.util.List;
import java.util.Stack;

/**
 * Created with IntelliJ IDEA.
 * User: KurbatovM
 * Date: 9/5/13
 * Time: 4:08 PM
 */
public class CommandPrint extends BaseCommand
{
    public CommandPrint( ) {
        super("PRINT", null);
    }

    @Override
    public void execute(List<String> commandWithArg) throws ExceptionBadParam, ExceptionStackEmpty {
        Stack<String> stack = this.getStack();
        if ( commandWithArg.size() != 1) {
            throw new ExceptionBadParam("Command \"" + this.getName() + "\" do not have param");
        }
        if ( (stack == null) || (stack.size() <= 0) ) {
            throw new ExceptionStackEmpty();
        }
        System.out.println(this.getStack().peek());
    }

    @Override
    public String getHelp() {
        return this.getName() + "\t\t\t-\tPrint Top value without remove, not implements yet\n";
    }
}
