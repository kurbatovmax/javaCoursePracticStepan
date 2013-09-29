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
public class CommandDivision extends BaseCommand
{

    public CommandDivision() {
        super("DIV", null);
    }

    public CommandDivision(CommandDataContainer data) {
        super("DIV", data);
    }

    /**
     * Note: Division on zero result infinity !!!!
     */
    @Override
    public void execute(List<String> commandWithArg) throws ExceptionBadParam, ExceptionStackEmpty {
        validate(commandWithArg);
        Stack<String> stack = this.getStack();
        List<String> paramFromStack  = new ArrayList<>();
        Double p1 = 1.0;
        Double p2 = 1.0;
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
            res = p1 / p2;
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
     * @param commandWithArg - [0] cmd NAME, .... param
     * @throws ExceptionStackEmpty
     * @throws ExceptionBadParam
     */
    private void validate(List<String> commandWithArg) throws ExceptionStackEmpty, ExceptionBadParam {
        Stack<String> stack = this.getStack();
        if ( (stack == null) ||  (stack.size() <= 0) ) {
            throw new ExceptionStackEmpty();
        }

        Integer szParam = commandWithArg.size();
        switch (szParam) {
            case 1:
            {
                if (stack.size() < 2 ) {
                    throw new ExceptionBadParam("Not enough parameters");
                }
                break;
            }
            case 2: {
                try {
                    Double.valueOf( commandWithArg.get(1) );
                } catch (NumberFormatException e) {
                    throw new ExceptionBadParam("The second parameter must be a number");
                }
                break;
            }
            default: {
                throw new ExceptionBadParam(this.getName() + " command may have zero or one param");
            }
        }
    }

    @Override
    public String getHelp() {
        return this.getName() + " NUMBER\t\t-\tNUMBER division on top value from stack result store stack\n";
    }
}
