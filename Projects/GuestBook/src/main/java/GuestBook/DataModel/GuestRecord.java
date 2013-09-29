package GuestBook.DataModel;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: Kurbatov
 * Date: 9/29/13
 * Time: 1:04 PM
 */
public class GuestRecord
{
    private Integer id_key;
    private Date postDate;
    private String postMsg;

    /**
     *
     * @return data add message
     */
    public Date getPostDate() {
        return postDate;
    }

    /**
     *
     * @return message
     */
    public String getPostMsg() {
        return postMsg;
    }

    /**
     *
     * @param postMsg message
     */
    public void setPostMsg(String postMsg) {
        this.postDate = new Date();
        this.postMsg = postMsg;
    }

    /**
     *
     * @param postMsg message
     * @param date  date
     */
    public void setPostMsg(String postMsg, Date date) {
        this.postDate = date;
        this.postMsg = postMsg;
    }

    @Override
    public String toString() {
        return this.id_key.toString() + ", " + this.postDate.toString() + ", " + this.postMsg;
    }
}