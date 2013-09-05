package im.kmg.sibniar2;

import javafx.util.Pair;
import org.omg.CORBA.BAD_PARAM;

import java.util.ArrayList;
import java.util.List;

import static java.lang.System.out;

/**
 * Created with IntelliJ IDEA.
 * User: maxim
 * Date: 9/5/13
 * Time: 9:29 PM
 * To change this template use File | Settings | File Templates.
 */

public class CommandsParser
{
    private List<String> m_ListString;
    private CommandsType m_CurrentCommand;
    private boolean exit;

    /**
     *
     */
    public CommandsParser() {
        m_ListString = new ArrayList<String>();
        m_CurrentCommand = CommandsType.NONE;
    }

    /**
     *
     * @param cmdLine
     */
    public CommandsParser(String cmdLine) {
        m_ListString = new ArrayList<String>();
        String param[] = cmdLine.split(" ");
        for(String s : param) {
            this.m_ListString.add(s);
        }
    }


    /**
     *
     * @return true if command correct , false if not
     */
    public boolean isValid() throws BadParamException {
        if (this.m_ListString.size() >= 1) {
            switch ( m_ListString.get(0) ) {
                case "--help": {
                    m_CurrentCommand = CommandsType.HELP;
                    return true;
                }
                case "--exit": {
                    m_CurrentCommand = CommandsType.EXIT;
                    return true;
                }
                case  "Add": {
                    if (m_ListString.size() == 2) {
                        m_CurrentCommand = CommandsType.ADD;
                        return true;
                    }
                    break;
                }
                case  "Comment": {
                    m_CurrentCommand = CommandsType.COMMENT;
                    return true;
                }
                case "Pop": {
                    if (m_ListString.size() == 1) {
                        m_CurrentCommand = CommandsType.POP;
                        return true;
                    }
                    break;
                }
                case "Push": {
                    if (m_ListString.size() == 2) {
                        Integer param2 = null;
                        try {
                            param2 = Integer.valueOf(m_ListString.get(1));
                        }  catch(NumberFormatException e) {
                            throw new BadParamException();
                        }
                        m_CurrentCommand = CommandsType.PUSH;
                        return true;
                    }
                    break;
                }
                case "Sub": {
                    if (m_ListString.size() == 2) {
                        m_CurrentCommand = CommandsType.SUB;
                        return true;
                    }
                    break;
                }
                case "Define": {
                    m_CurrentCommand = CommandsType.DEFINE;
                    return true;
                }
                case "Div": {
                    if (m_ListString.size() == 2) {
                        m_CurrentCommand = CommandsType.DIV;
                        return true;
                    }
                    break;
                }
                case "Mul": {
                    if (m_ListString.size() == 2) {
                        m_CurrentCommand = CommandsType.MUL;
                        return true;
                    }
                    break;
                }
                case "Print": {
                    m_CurrentCommand = CommandsType.PRINT;
                    return true;
                }
                case "Sqrt": {
                    if (m_ListString.size() == 2) {
                        m_CurrentCommand = CommandsType.SQRT;
                        return true;
                    }
                    break;
                }

            }
        }
        return false;
    }

    /**
     *
     */
    public void printHelp() {
        String str = "";
        str += "====== Stack calculator =======\n";
        str += "version: " + MainPushCalculator.VERSION + "\n";
        str += "--help\t-\tprint this message\n";
        str += "--exit\t-\texit application\n";
        out.println(str);
    }

    /**
     *
     * @return
     */
    public boolean isHelp() {
        if (m_CurrentCommand == CommandsType.HELP) {
            return  true;
        }
        return false;
    }


    /**
     *
     * @return
     */
    public boolean isExit() {
        if (m_CurrentCommand == CommandsType.EXIT) {
            return true;
        }
        return false;
    }

    /**
     *
     * @return array string
     * [0] - command name
     * [1] - param for command
     */
    public List<String> getParamCmd() throws BadParamException {
        if( isValid() == true ) {
            return m_ListString;
        }
       return null;
    }
}
