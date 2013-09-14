package im.kmg.sibniar2.commands;

import im.kmg.sibniar2.BadParamException;
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

    public CommandSub(IKMGStack stack) {
        super("SUB", stack);
    }

    @Override
    public void execute(List<String> commandWithArg) throws BadParamException {
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
    private void validate(List<String> dataCommand) throws BadParamException {
        IKMGStack stack = this.getStack();
        if ( (stack == null) ||  (stack.size() <= 0) ) {
            throw new BadParamException("Stack empty");
        }

        Integer szParam = dataCommand.size();
        switch (szParam) {
            case 1: {
                m_param="";
                if (stack.size() < 2 ) {
                    throw new BadParamException("Not enough parameters");
                }

                break;
            }
            case 2: {
                String param = dataCommand.get(1);

                try {
                    Double.valueOf( param );
                } catch (NumberFormatException e) {
//                    if (!m_define.hasDefineVar(param)) {
                        throw new BadParamException("The second parameter must be a number");
//                    } else {
//                        param = m_define.getDefineVar(dataCommand.get(1)).toString();
//                    }
                }
                m_param =  param;
                break;
            }
            default: {
                throw new BadParamException(this.getName() + " command may have zero or one param");
            }
        }
    }

    @Override
    public String getHelp() {
        return this.getName() + " NUMBER\t\t-\t(NUMBER - Top value stack), result store to stack\n";
    }
}
