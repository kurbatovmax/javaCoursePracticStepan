package im.kmg.sibniar2;

import java.util.EmptyStackException;
import java.util.Stack;

/**
 * Created with IntelliJ IDEA.
 * User: KurbatovM
 * Date: 9/5/13
 * Time: 4:37 PM
 * To change this template use File | Settings | File Templates.
 */
public class KmgStack implements IStackString
{
    private Stack<String> m_stack;

    KmgStack() {
        m_stack = new Stack<String>();
    }

    /**
     *
     * @param sParam - value then add to stack
     * @return  - sParam
     */
    @Override
    public String Push(String sParam) {
        this.m_stack.push(sParam);
        return sParam;
    }

    /**
     *
     * @return value from top stack or null and remove from stack
     */
    @Override
    public String Pop() throws EmptyStackException {
        return this.m_stack.pop();
    }

    /**
     *  Return value without remove item from stack
     * @return
     */
    public String Peek() {
       String retv = null;
        return retv;
    }


    /**
     *
     * @return - all element as string
     */
    @Override
    public String toString() {
        return  this.m_stack.toString();
    }
}
