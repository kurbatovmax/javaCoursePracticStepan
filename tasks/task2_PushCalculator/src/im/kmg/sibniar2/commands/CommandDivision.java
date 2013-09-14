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
public class CommandDivision extends BaseCommand
{
    private String m_param;

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
         /*
        IKMGStack stack = this.getStack();
        Double param1 = Double.valueOf(stack.pop());
        Double param2;
        if (m_param.isEmpty()) {
            param2 = Double.valueOf(stack.pop());
        } else {
            param2 =  Double.valueOf(m_param);
        }
        m_param = Double.toString( param1 / param2 );
        stack.push(m_param);
         */

        IKMGStack stack = this.getStack();
        List<String> paramFromStack  = new ArrayList<String>();
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
     * @param commandWithArg
     * @throws ExceptionStackEmpty
     * @throws ExceptionBadParam
     */
    private void validate(List<String> commandWithArg) throws ExceptionStackEmpty, ExceptionBadParam {
        IKMGStack stack = this.getStack();
        if ( (stack == null) ||  (stack.size() <= 0) ) {
            throw new ExceptionStackEmpty();
        }

        Integer szParam = commandWithArg.size();
        switch (szParam) {
            case 1: {
                m_param="";
                if (stack.size() < 2 ) {
                    throw new ExceptionBadParam("Not enough parameters");
                }

                break;
            }
            case 2: {
                String param = commandWithArg.get(1);

                try {
                    Double.valueOf( param );
                } catch (NumberFormatException e) {
                    throw new ExceptionBadParam("The second parameter must be a number");
                }
                m_param =  param;
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
