package im.kmg.sibniar2;

import java.util.EmptyStackException;
import java.util.Stack;

/**
 * Created with IDEA.
 * User: KurbatovM
 * Date: 9/5/13
 * Time: 4:37 PM
 * To change this template use File | Settings | File Templates.
 */
public class KmgStack implements IStackString
{
    private final Stack<String> m_stack;

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
     * @return  top value without remove
     */
    public String Peek() {
        return this.m_stack.peek();
    }


    /**
     *
     * @return - all element as string
     */
    @Override
    public String toString() {
        return  this.m_stack.toString();
    }

    @Override
    public int size() {
        return m_stack.size();
    }
}
