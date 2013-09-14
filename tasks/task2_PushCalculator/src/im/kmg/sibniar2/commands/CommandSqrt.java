package im.kmg.sibniar2.commands;

import im.kmg.sibniar2.IKMGStack;

import java.util.List;

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
        IKMGStack stack = this.getStack();
        Double value =  Double.valueOf(stack.pop());
        value = Math.sqrt(value);
        stack.push(value.toString());
    }

    /**
     *
     * @param dataCommand
     * @throws ExceptionBadParam
     * @throws ExceptionStackEmpty
     */
    private void validate(List<String> dataCommand) throws ExceptionBadParam, ExceptionStackEmpty {
        IKMGStack stack = this.getStack();
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
