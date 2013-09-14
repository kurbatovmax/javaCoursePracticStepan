package im.kmg.sibniar2.commands;

import im.kmg.sibniar2.IKMGStack;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: KurbatovM
 * Date: 9/5/13
 * Time: 4:06 PM
 */
public class CommandMultiplication extends  BaseCommand
{
    //private String m_param;
    //private Double m_param1;
    //private Double m_param2;


    public CommandMultiplication() {
        super("MUL", null);
    }

    public CommandMultiplication(CommandDataContainer data) {
        super("MUL", data);
    }

    @Override
    public void execute(List<String> commandWithArg) throws ExceptionBadParam, ExceptionStackEmpty {
        validate(commandWithArg);
        IKMGStack stack = this.getStack();
        List<String> paramFromStack  = new ArrayList<String>();
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
        IKMGStack stack = this.getStack();
        if ( (stack == null) ||  (stack.size() <= 0) ) {
            throw new ExceptionStackEmpty();
        }

        String param="";
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
                    new ExceptionBadParam("Not number");
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
