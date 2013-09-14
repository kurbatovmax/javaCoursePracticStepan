package im.kmg.sibniar2.commands;


import im.kmg.sibniar2.BadParamException;
import im.kmg.sibniar2.IKMGStack;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: KurbatovM
 * Date: 9/5/13
 * Time: 4:02 PM
 */
public class CommandPop extends BaseCommand
{
    public CommandPop(IKMGStack stack) {
        super("POP", stack);
    }

    @Override
    public void execute(List<String> commandWithArg) throws BadParamException {
        IKMGStack stack = this.getStack();
        if ( commandWithArg.size() != 1) {
            throw new BadParamException("Command \"" + this.getName() + "\" do not have param");
        }
        if ( (stack == null) || (stack.size() <= 0) ) {
            throw new BadParamException("Stack empty");
        }

        System.out.println( this.getStack().pop() );
    }

    @Override
    public String getHelp() {
        return this.getName() + "\t\t\t-\tTake Top Value from stack\n";
    }
}
