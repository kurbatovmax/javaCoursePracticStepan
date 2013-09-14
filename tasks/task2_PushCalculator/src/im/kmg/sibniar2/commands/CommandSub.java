package im.kmg.sibniar2.commands;

import im.kmg.sibniar2.IKMGStack;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: KurbatovM
 * Date: 9/5/13
 * Time: 4:04 PM
 */
public class CommandSub extends BaseCommand
{
    private String m_param;

    public CommandSub() {
        super("SUB", null);
    }

    public CommandSub(CommandDataContainer data) {
        super("SUB", data);
    }

    @Override
    public void execute(List<String> commandWithArg) throws ExceptionBadParam, ExceptionStackEmpty {
        validate(commandWithArg);

        IKMGStack stack = this.getStack();
        Double param1 = Double.valueOf(stack.pop());
        Double param2;
        if (m_param.isEmpty()) {
            param2 = Double.valueOf(stack.pop());
        } else {
            param2 =  Double.valueOf(m_param);
        }
        m_param = Double.toString( param1 - param2 );
        stack.push(m_param);

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

        Integer szParam = dataCommand.size();
        switch (szParam) {
            case 1: {
                m_param="";
                if (stack.size() < 2 ) {
                    throw new ExceptionBadParam("Not enough parameters");
                }

                break;
            }
            case 2: {
                String param = dataCommand.get(1);

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
        return this.getName() + " NUMBER\t\t-\t(NUMBER - Top value stack), result store to stack\n";
    }
}
