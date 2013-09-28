import java.io.File;

/**
 * Created with IntelliJ IDEA.
 * User: maxim
 * Date: 9/27/13
 * Time: 10:57 PM
 * To change this template use File | Settings | File Templates.
 */
public class ResourceType
{
    /**
     *
     * @param f
     * @return
     */
    static DATA_TYPE getFileType(File f) {
        String s = f.getName();
        String []exes = s.split("\\.");
        DATA_TYPE type_retv = DATA_TYPE.UNKNOWN;

        if ( exes.length > 0 ) {
            String exe = exes[exes.length-1];

            if ( !exe.isEmpty() ) {
                if ( exe.equals("html") ) {
                    type_retv = DATA_TYPE.HTML;
                } else if (exe.equals("png")) {
                    type_retv = DATA_TYPE.PNG;
                } else if (exe.equals("txt")) {
                    type_retv = DATA_TYPE.TEXT;
                } else if (exe.equals("jpg")) {
                    type_retv = DATA_TYPE.JPG;
                }
            }
        }
        return type_retv;
    }
}
