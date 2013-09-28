import org.apache.log4j.Logger;

import java.io.File;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: maxim
 * Date: 9/28/13
 * Time: 12:04 AM
 * To change this template use File | Settings | File Templates.
 */
public class CreateHTMLForFolders
{
    private final Logger Log = Logger.getLogger(Class.class);

    /**
     *
     * @param file
     * @return
     */
    String getHtmlDirsAndFile(File file) {
        String rootFolder = MainHttpd.getHttpRootFolder();

        StringBuilder  html = new StringBuilder();
        html.append("<html><body>");
        File []listFile = file.listFiles();
        if ( listFile != null ) {
            for (File f : listFile) {
                String str  = f.getAbsolutePath();
                str = str.replaceAll(rootFolder, "");

                if (f.isDirectory()) {
                    html.append("<a href=\"")
                            .append(str)
                            .append("\" >")
                            .append(f.getName())
                            .append(File.separator)
                            .append("</a></br>");
                } else {
                    html.append("<a href=\"")
                            .append(str)
                            .append("\" >")
                            .append(f.getName())
                            .append("</a></br>");
                }
            }
            html.append(getFooter());
        }
        html.append("</body><html>                    ");
        this.Log.debug(html.toString());
        return html.toString();
    }

    /**
     *
     * @return  String footer
     */
    private String getFooter() {
        String data = new Date().toString();
        String retv = "<br><br><hr><p>*** ATM-Turbo 512k, OS: CP/M, " +
                data + " ***</p><hr>";
        return retv;
    }
}
