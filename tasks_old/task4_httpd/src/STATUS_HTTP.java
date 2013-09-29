/**
 * Created with IntelliJ IDEA.
 * User: maxim
 * Date: 9/27/13
 * Time: 10:55 PM
 * To change this template use File | Settings | File Templates.
 */
public enum STATUS_HTTP {
    OK("HTTP/1.1 200 OK");
    private String status;
    STATUS_HTTP(String status) {this.status = status;}
    String getStatus() {return status;}
}
