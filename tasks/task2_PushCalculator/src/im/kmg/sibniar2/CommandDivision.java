package im.kmg.sibniar2;

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
    private String m_param;
    private final Stack<String> m_stack;
    private final ICommandDefine m_define;

    public CommandDivision(Stack<String> stack, ICommandDefine define) {
        super("DIV");
        this.m_stack = stack;
        this.m_define = define;
    }

    /**
     * Note: Division on zero result infinity !!!!
     */
    @Override
    public void execute() {
        Double param1 = Double.valueOf(m_stack.pop());
        Double param2;
        if (m_param.isEmpty()) {
            param2 = Double.valueOf(m_stack.pop());
        } else {
            param2 =  Double.valueOf(m_param);
        }
        m_param = Double.toString( param1 / param2 );
        m_stack.push(m_param);
    }

    /**
     * Command may have zero, one.
     * If not have param try take two value from  stack top.
     * If have one param, try take one value from stack top.
     * Result save in stack top.
     */
    @Override
    public void init(List<String> dataCommand) throws BadParamException {
        if ( (m_stack == null) ||  (m_stack.size() <= 0) ) {
            throw new BadParamException("Stack empty");
        }

        Integer szParam = dataCommand.size();
        switch (szParam) {
            case 1: {
                m_param="";
                if (m_stack.size() < 2 ) {
                    throw new BadParamException("Not enough parameters");
                }

                break;
            }
            case 2: {
                String param = dataCommand.get(1);

                try {
                    Double.valueOf( param );
                } catch (NumberFormatException e) {
                    if (!m_define.hasDefineVar(param)) {
                        throw new BadParamException("The second parameter must be a number");
                    } else {
                        param = m_define.getDefineVar(dataCommand.get(1)).toString();
                    }
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
        return this.getName() + " NUMBER\t\t-\tNUMBER division on top value from stack result store stack\n";
    }
}
