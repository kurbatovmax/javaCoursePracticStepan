package im.kmg.pc.commands;

import im.kmg.pc.commands.api.BaseCommand;
import im.kmg.pc.commands.api.CommandDataContainer;
import im.kmg.pc.commands.api.ExceptionBadParam;
import im.kmg.pc.commands.api.ExceptionStackEmpty;
import java.util.List;
import java.util.Stack;

/**
 * Created with IntelliJ IDEA.
 * User: KurbatovM
 * Date: 9/5/13
 * Time: 4:07 PM
 */
public class CommandSqrt extends BaseCommand
{
    public CommandSqrt() {
        super("SQRT", null);
    }

    public CommandSqrt(CommandDataContainer data) {
        super("SQRT", data);
    }

    /**
     * Note: result may be NaN negative number !!!
     */
    @Override
    public void execute(List<String> commandWithArg) throws ExceptionStackEmpty, ExceptionBadParam {
        validate(commandWithArg);
        Stack<String> stack = this.getStack();
        Double value =  Double.valueOf(stack.pop());
        value = Math.sqrt(value);
        stack.push(value.toString());
    }

    /**
     *
     * @param dataCommand cmd and arg
     * @throws ExceptionBadParam
     * @throws ExceptionStackEmpty
     */
    private void validate(List<String> dataCommand) throws ExceptionBadParam, ExceptionStackEmpty {
        Stack<String> stack = this.getStack();
        if ( dataCommand.size() != 1) {
            throw new ExceptionBadParam("Command \"" + this.getName() + "\" do not have param");
        }
        if ( (stack == null) || (stack.size() <= 0) ) {
            throw new ExceptionStackEmpty();
        }

        try {
            Double.valueOf(stack.peek());
        } catch (NumberFormatException e) {
            throw new ExceptionBadParam("The second parameter must be a number");
        }
    }



    @Override
    public String getHelp() {
        return this.getName() + "\t\t-\tTake top value from stack and square store result to top stack\n";
    }
}
