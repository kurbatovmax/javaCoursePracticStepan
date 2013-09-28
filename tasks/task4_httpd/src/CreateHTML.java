import org.apache.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.file.Path;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: maxim
 * Date: 9/28/13
 * Time: 12:04 AM
 * To change this template use File | Settings | File Templates.
 */
public class CreateHTML
{
    private final Logger Log = Logger.getLogger(Class.class);

    /**
     *
     * @param file
     * @return
     */
    String getHtmlDirsAndFile(File file) throws UnsupportedEncodingException {
        String rootFolder = MainHttpd.getHttpRootFolder();

        StringBuilder  html = new StringBuilder();
        html.append("<html><body>");
        File []listFile = file.listFiles();
        if ( listFile != null ) {
            for (File f : listFile) {
                String str  = f.getAbsolutePath();
                str = str.replaceAll("\\\\", "/");
                str = removeRootDir(str);
                if (f.isDirectory()) {
                    html.append("<a href=\"")
                            .append(str)
                            .append("\" >")
                            .append(f.getName())
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
        html.append("</body></html>");
        this.Log.debug(html.toString());
        return html.toString();
    }

    /**
     *
     * @param str
     * @return
     */
    private String removeRootDir(String str) throws UnsupportedEncodingException {
        String rd = MainHttpd.getHttpRootFolder();
        String retv = "";
        String rd_decode = URLEncoder.encode(rd, "UTF-8");
        String str_decode = URLEncoder.encode(str, "UTF-8");
        retv = str_decode.replaceAll(rd_decode, "");
        retv = URLDecoder.decode(retv, "UTF-8");
        return  retv;
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

    /**
     *
     * @return
     */
    public String getHtmlWithError() {
        String html="<html><body><h1>Error: Page not found</body></html>";
        return html;
    }
}
