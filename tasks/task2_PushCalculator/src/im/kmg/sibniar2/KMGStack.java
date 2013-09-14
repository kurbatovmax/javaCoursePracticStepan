package im.kmg.sibniar2;

import java.util.List;
import java.util.Stack;

/**
 * Created with IntelliJ IDEA.
 * User: maxim
 * Date: 9/12/13
 * Time: 9:34 PM
 */
public class KMGStack implements IKMGStack
{
    private  final Stack<String> m_stack;

    public KMGStack() {
        this.m_stack = new Stack<String>();
    }

    @Override
    public void push(String var) {
        m_stack.push(var);
    }

    @Override
    public String pop() {
        return this.m_stack.pop();
    }

    @Override
    public String peek() {
        return this.m_stack.peek();
    }

    @Override
    public Integer size() {
        return (this.m_stack == null) ? 0 : this.m_stack.size();
    }

    @Override
    public List<String> subList(int fromIndex, int toIndex) {
        return this.m_stack.subList(fromIndex, toIndex);
    }

    public String toString() {
        return m_stack.toString();
    }
}
