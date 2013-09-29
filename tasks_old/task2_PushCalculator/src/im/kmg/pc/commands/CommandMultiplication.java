package im.kmg.pc.commands;

import im.kmg.pc.commands.api.BaseCommand;
import im.kmg.pc.commands.api.CommandDataContainer;
import im.kmg.pc.commands.api.ExceptionBadParam;
import im.kmg.pc.commands.api.ExceptionStackEmpty;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

/**
 * Created with IntelliJ IDEA.
 * User: KurbatovM
 * Date: 9/5/13
 * Time: 4:06 PM
 */
public class CommandMultiplication extends BaseCommand
{
    public CommandMultiplication() {
        super("MUL", null);
    }

    public CommandMultiplication(CommandDataContainer data) {
        super("MUL", data);
    }

    @Override
    public void execute(List<String> commandWithArg) throws ExceptionBadParam, ExceptionStackEmpty {
        validate(commandWithArg);
        Stack<String> stack = this.getStack();
        List<String> paramFromStack  = new ArrayList<>();
        Double p1 = null ;
        Double p2 = null;
        Double res;

        try {
            int szParam = commandWithArg.size() - 1;
            switch (szParam) {
                case 0: { // tow parm take from stack
                    paramFromStack.add(stack.pop()); // top
                    paramFromStack.add(stack.pop()); // under
                    p1 = Double.valueOf(paramFromStack.get(0));
                    p2 = Double.valueOf(paramFromStack.get(1));
                    break;
                }
                case 1: { // one param take from stack
                    paramFromStack.add(stack.pop()); // top
                    p1 = Double.valueOf(paramFromStack.get(0));
                    p2 = Double.valueOf(commandWithArg.get(1));
                    break;
                }
            }
            //noinspection ConstantConditions
            res = p1 * p2;
            stack.push(res.toString());
        } catch (NumberFormatException e) {
            // restore stack when exception
            Collections.reverse(paramFromStack);
            for (String s : paramFromStack) {
                stack.push(s);
            }
            throw  new ExceptionBadParam("Not number");
        }
    }

    /**
     * Command may have zero, one.
     * If not have param try take two value from  stack top.
     * If have one param, try take one value from stack top.
     * Result save in stack top.
     */
     private void validate(List<String> dataCommand) throws ExceptionStackEmpty, ExceptionBadParam {
         Stack<String> stack = this.getStack();
        if ( (stack == null) ||  (stack.size() <= 0) ) {
            throw new ExceptionStackEmpty();
        }

        Integer szParam = dataCommand.size();
        switch (szParam) {
            case 1: {
                if (stack.size() < 2 ) {
                    throw new ExceptionBadParam("Not enough parameters");
                }
                break;
            }
            case 2: {
                try {
                    Double.valueOf(dataCommand.get(1));
                } catch (NumberFormatException e) {
                    throw  new ExceptionBadParam("Not number");
                }
                break;
            }
            default: {
                throw new ExceptionBadParam(this.getName() + " command may have zero or one param");
            }
        }
    }

    /**
     *
     * @return Help as string for automation generation help.
     */
    @Override
    public String getHelp() {
        return this.getName() + " [NUMBER]\t\t-\tif not param Take two top value from stack and multiplication or NUMBER * top stack value,  result store to stack\n";
    }
}
