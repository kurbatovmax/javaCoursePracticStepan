package im.kmg.sibniar2.commands;

import im.kmg.sibniar2.BadParamException;
import im.kmg.sibniar2.IKMGStack;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: KurbatovM
 * Date: 9/5/13
 * Time: 4:06 PM
 */
public class CommandMultiplication extends  BaseCommand
{
    private String m_param;
    private  final ICommandDefine m_define;
    private Double m_param1;
    private Double m_param2;

    /**
     *
     * @param stack
     */
    public CommandMultiplication(IKMGStack stack, ICommandDefine define) {
        super("MUL", stack);
        m_define = define;
    }

    @Override
    public void execute(List<String> commandWithArg) throws BadParamException {
        validate(commandWithArg);
        IKMGStack stack = this.getStack();
        Double param1 = Double.valueOf(stack.pop());
        Double param2 = null;
        if (m_param.isEmpty()) {
            param2 = Double.valueOf(stack.pop());
        } else {
            try {
                param2 =  Double.valueOf(m_param);
            } catch (NumberFormatException e) {
                if (m_define.hasDefineVar(m_param)) {
                    param2 = m_define.getDefineVar(m_param);
                }
            }
        }

        if (param2 == null) {
            throw new BadParamException(this.getName() + " command may have zero or one param");
        }

        m_param = Double.toString( param1 * param2 );
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
                m_param = dataCommand.get(1);
                break;
            }
            default: {
                throw new BadParamException(this.getName() + " command may have zero or one param");
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
