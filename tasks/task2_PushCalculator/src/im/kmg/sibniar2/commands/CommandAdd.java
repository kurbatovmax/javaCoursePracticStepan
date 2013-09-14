package im.kmg.sibniar2.commands;

import im.kmg.sibniar2.IKMGStack;

import java.util.List;


/**
 * Created with IntelliJ IDEA.
 * User: KurbatovM
 * Date: 9/5/13
 * Time: 4:03 PM
 */
public class CommandAdd extends BaseCommand
{
    private String m_param;

    public CommandAdd() {
        super("ADD", null);
    }

    public CommandAdd(CommandDataContainer data) {
        super("ADD", data);
    }

    @Override
    public void execute(List<String> commandWithArg) throws ExceptionBadParam, ExceptionStackEmpty {
        this.validate(commandWithArg);

        IKMGStack stack = this.getStack();
        Double param1 = Double.valueOf(stack.pop());
        Double param2;
        if (m_param.isEmpty()) {
            param2 = Double.valueOf(stack.pop());
        } else {
            param2 =  Double.valueOf(m_param);
        }
        m_param = Double.toString( param1 + param2 );
        stack.push(m_param);
    }

    /**
     *
     * @param commandWithArg - [0] cmd NAME, .... param
     * @throws ExceptionBadParam
     * @throws ExceptionStackEmpty
     */
    private void validate(List<String> commandWithArg) throws ExceptionBadParam, ExceptionStackEmpty {
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
        return this.getName() + " NUMBER\t\t-\tAdded NUMBER to Top Value from stack, top value replace new value\n";
    }
}
