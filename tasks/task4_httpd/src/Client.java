import com.sun.xml.internal.messaging.saaj.util.Base64;
import org.apache.log4j.Logger;

import java.io.*;
import java.net.*;
import java.util.*;

/**
 *
 */
enum HTML_STATUS {
    OK("HTTP/1.1 200 OK");
    private String status;
    HTML_STATUS(String status) {this.status = status;}
    String getStatus() {return status;}
}

/**
 *
 */
enum TYPE_DATA{
    HTML("text/html") ,
    PNG ("image/png"),
    JPG ("image/jpeg"),
    TEXT("text/plain"),
    UNKNOWN("text/html");

    private String str_type;
    TYPE_DATA(String s) {str_type = s;}
    String getType() {return str_type;}
}

/**
 * Created with IntelliJ IDEA.
 * User: maxim
 * Date: 9/15/13
 * Time: 6:56 PM
 */
class Client implements Runnable
{
    private final Logger Log = Logger.getLogger(Client.class);
    private final Socket m_client;

    /**
     *
     * @param client
     */
    Client(Socket client) {
        synchronized (this) {
            Thread l_Thread = new Thread(this, getClass().getName());
            m_client  = client;
            l_Thread.start();
        }
    }

    /**
     *
     * @param sizeBody
     * @param type
     * @return
     */
    public void sendDataToClient(InputStream in,
                                 OutputStream out,
                                 long sizeBody,
                                 TYPE_DATA type,
                                 HTML_STATUS htmlStatus)
    {
        List<String> headers_send = new ArrayList<>();
        headers_send.add( htmlStatus.getStatus() + "\r\n");
        headers_send.add("Date: " + new Date().toString() + "\r\n");
        headers_send.add("Last-Modified: " + new Date().toString() + "\r\n");
        headers_send.add("Server: Super http server ver: 0.1\r\n");
        headers_send.add("Content-Language: ru\r\n");
        headers_send.add("Content-Type: " + type.getType() + "; charset=utf-8\r\n");
        //headers_send.add("Content-Type: " + type.getType() + "\r\n");
        headers_send.add("Content-Length: " + sizeBody + "\r\n");
        headers_send.add("Connection: close\r\n");
        headers_send.add("\r\n\r\n");
        try {
            for (String sss : headers_send) {
                out.write( sss.getBytes() );
                out.flush();
            }
        } catch (IOException e) {
            this.Log.fatal("", e);
        }

        int data = 0;
        try {
            while ( (data = in.read()) > 0 ) {
                out.write(data);
                out.flush();
            }
            out.write("\n".getBytes());
            out.flush();
        }catch (IOException e) {
            this.Log.fatal("", e);
        }
    }

    /**
     *
     */
    @Override
    public void run() {
        this.Log.debug("Enter: ");
        List<String> headers = null;
        List<String> headers_send = null;
        Socket cSocket = m_client;
        OutputStream clientOutStream = null;
        InputStream clientInStream = null;
        try {

            clientInStream = cSocket.getInputStream();
            clientOutStream = cSocket.getOutputStream();

            headers = this.getHeaders(clientInStream);

            String pathToFile = getPathToFile(headers);
            pathToFile = URLDecoder.decode(pathToFile);
            File f = new File(pathToFile);
            if ( f.exists() )  {
                if ( f.isDirectory() ) {
                    String html = getHtmlDirsAndFile(f);
                    ByteArrayInputStream bodyInStream = new ByteArrayInputStream(html.getBytes());
                    sendDataToClient(bodyInStream, clientOutStream, html.length(), TYPE_DATA.HTML, HTML_STATUS.OK);
                } else {
                    TYPE_DATA type = getFileType(f);
                    long size = f.length();
                    InputStream bodyInStream = new FileInputStream(f);
                    sendDataToClient(bodyInStream, clientOutStream, size, type, HTML_STATUS.OK);
                }

            } else {
                String html = getHtmlWithError();
            }
        } catch (IOException e) {
            this.Log.fatal("", e);
        } finally {
            try {
                cSocket.close();
            } catch (IOException e) {
                this.Log.fatal("", e);
            }
        }
        this.Log.debug("Leave");
    }

    /**
     *
     * @param f
     * @return
     */
    private TYPE_DATA getFileType(File f) {
        String s = f.getName();
        String []exes = s.split("\\.");
        TYPE_DATA type_retv = TYPE_DATA.UNKNOWN;

        if ( exes.length > 0 ) {
            String exe = exes[exes.length-1];

            if ( !exe.isEmpty() ) {
                if ( exe.equals("html") ) {
                    type_retv = TYPE_DATA.HTML;
                } else if (exe.equals("png")) {
                    type_retv = TYPE_DATA.PNG;
                } else if (exe.equals("txt")) {
                    type_retv = TYPE_DATA.TEXT;
                } else if (exe.equals("jpg")) {
                    type_retv = TYPE_DATA.JPG;
                }
            }
        }
        return type_retv;
    }

    /**
     *
     * @param headers
     * @return
     */
    private String getPathToFile(List<String> headers) {
        String rf = MainHttpd.getHttpRootFolder(); // root dir
        String [] list;
        String retv = "/";
        for(String s : headers) {
            if (s.matches("^GET\\s.*")) {
                list = s.split("\\s");
                if (list.length == 3) {
                    retv = list[1];
                }
                break;
            }
        }
        retv  = rf + retv;
        return retv;
    }

    /**
     *
     * @param in stream for reading
     * @return   list of header
     */
    List<String> getHeaders(InputStream in) {
        Log.info("Enter: ");
        List<String> retv = new ArrayList<>();
        Reader reader = new InputStreamReader(in);
        BufferedReader bufferedReader = new BufferedReader(reader);

        try {
            //while (bufferedReader.ready()) {
            while (true) {
                String tmp =  bufferedReader.readLine();
                if (tmp == null) {
                    break;
                }

                if (tmp.equals("\n") || tmp.isEmpty() ) {
                    break;
                }
                retv.add(tmp);
            }
        } catch (IOException e) {
            Log.fatal("Leave: ", e);
        }
        Log.info("Leave: ");
        return retv;
    }

    /**
     *
     * @param list
     * @param out
     * @throws IOException
     */
    void writeToClient(List<String> list, OutputStream out) throws IOException {
        Log.warn("Enter: ");
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(out));
        if (!list.isEmpty()) {
            for (String item : list) {
                writer.write(item + "\n");
                writer.flush();
            }
        }
        writer.flush();
        Log.warn("Leave: ");
    }

    /**
     *
     * @param file
     * @return
     */
     String getHtmlDirsAndFile(File file) {
        StringBuilder  html = new StringBuilder();
        html.append("<html><body>");
        File []listFile = file.listFiles();
        if ( listFile != null ) {

            for (File f : listFile) {
                String str  = f.getAbsolutePath();

                if (f.isDirectory()) {
                    html.append("<a href=\"").append(f.getName()).append("\" >").append(f.getName()).append(File.separator).append("</a></br>");
                } else {
                    html.append("<a href=\"").append(f.getName()).append("\" >").append(f.getName()).append("</a></br>");
                }
            }
            html.append(getFooter());
        }
        html.append("</body><html>");
        return html.toString();
    }

    /**
     *
     * @return
     */
    String getHtmlWithError() {
        String html="<html><body><h1>Error: Page not found</body></html>";
        return html;
    }

    /**
     *
     * @return  String footer
     */
    String getFooter() {
        StringBuilder str = new StringBuilder();

        str.append("<br><br><hr><p>ATM-Turbo 512k, OS: CP/M, ")
                .append(new Date().toString())
                .append(" </br><hr></p>");
        return str.toString();
    }
}
