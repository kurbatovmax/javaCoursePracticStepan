package im.kmg.sibniar2;

import im.kmg.sibniar2.commands.ICommandDefine;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: maxim
 * Date: 9/12/13
 * Time: 8:41 PM
 */
public interface IKMGStack
{
    public void push(String var);
    public String pop();
    public String peek();
    public Integer size();
    public String toString();

    // Returns a view of the portion of this list between the specified fromIndex, inclusive, and toIndex, exclusive.
    public List<String> subList(int FromIndex, int toIndex);
}
