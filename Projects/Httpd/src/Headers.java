import org.apache.log4j.Logger;

import java.io.*;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.channels.Channel;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: maxim
 * Date: 9/27/13
 * Time: 6:38 PM
 * To change this template use File | Settings | File Templates.
 */
public class Headers
{
    private Logger Log = Logger.getLogger(Class.class);
    private List<String> m_headers;
    private InputStream m_in;
    private SocketChannel m_sChannel;

    /**
     *
     * @param in - Input stream
     */
    Headers(InputStream in) throws IOException {
        m_headers = new ArrayList<String>();
        m_in = in;
        init();
    }

    /**
     *
     */
    private void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(m_in));
        while(true) {
            String s = br.readLine();
            if(s == null || s.trim().length() == 0) {
                break;
            }
            m_headers.add(s);
        }
    }

    /**
     *
     * @return
     */
    public String getPathResourceDecode() throws UnsupportedEncodingException {
        String []list;
        String pathResource = "/";
        for (String s : this.m_headers) {
            if ( s.matches("^GET\\s.*") ) {
                list = s.split("\\s");
                if ( list.length == 3) {
                    pathResource = list[1];
                }
                break;
            }
        }

        pathResource = URLDecoder.decode(pathResource, "UTF-8");
        return pathResource;
    }

    public static String getResponseheader(long sizeBody, DATA_TYPE type, STATUS_HTTP statusHttp) {
        StringBuilder header = new StringBuilder();
        header.append(statusHttp.getStatus() + "\r\n");
        header.append("Date: " + new Date().toString() + "\r\n");
        header.append("Last-Modified: " + new Date().toString() + "\r\n");
        header.append("Server: Super http server ver: 0.1\r\n");
        header.append("Content-Language: ru\r\n");
        header.append("Content-Type: " + type.getType() + "; charset=UTF-8\r\n");
        header.append("Content-Length: " + sizeBody + "\r\n");
        header.append("Connection: close\r\n");
        header.append("\r\n");
        return  header.toString();
    }
}
