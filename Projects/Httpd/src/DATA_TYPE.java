/**
 * Created with IntelliJ IDEA.
 * User: maxim
 * Date: 9/27/13
 * Time: 10:54 PM
 * To change this template use File | Settings | File Templates.
 */
public enum DATA_TYPE
{
    HTML("text/html") ,
    PNG ("image/png"),
    JPG ("image/jpeg"),
    TEXT("text/plain"),
    UNKNOWN("text/html");

    private String str_type;
    DATA_TYPE(String s) {str_type = s;}
    String getType() {return str_type;}
}
