package im.kmg.sibniar2;

import java.util.ArrayList;
import java.util.List;
/**
 * Split String
 * Created with IntelliJ IDEA.
 * User: maxim
 * Date: 9/5/13
 * Time: 9:29 PM
 * To change this template use File | Settings | File Templates.
 */
class CliParser
{
    private final List<String> m_ListString;

     /**
     *
     * @param cmdLine   String contains command with arguments
     */
    public CliParser(String cmdLine) {
        m_ListString = new ArrayList<String>();
        String param[] = cmdLine.split(" ");
        for(String s : param) {
            this.m_ListString.add(s);
        }
    }

    /**
     *
     * @return array string  cmd with param
     * [0] - command name
     * [1] - param for command
     */
    public List<String> getCmdWithParam() {
        return m_ListString;
    }
}
