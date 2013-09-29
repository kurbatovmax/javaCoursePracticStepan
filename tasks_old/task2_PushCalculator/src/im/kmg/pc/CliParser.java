package im.kmg.pc;

import java.util.ArrayList;
import java.util.Collections;
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
        m_ListString = new ArrayList<>();
        cmdLine = this.removeComment(cmdLine);
        int sz = cmdLine.length();
        if (sz > 0) {
            String param[] = cmdLine.split(" ");
            Collections.addAll(this.m_ListString, param);
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

    private String removeComment(String str) {
        return  str.replaceAll("(#.*$)", "").trim();
    }

    @SuppressWarnings("BooleanMethodIsAlwaysInverted")
    public boolean hasCommand() {
        return m_ListString.size() > 0;
    }
}
