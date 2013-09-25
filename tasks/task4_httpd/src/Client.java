import org.apache.log4j.Logger;

import java.io.*;
import java.net.MalformedURLException;
import java.net.Socket;
import java.net.URL;
import java.util.*;

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
    private String byteData;

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

    enum TYPE_DATA{
        HTML("text/html") ,
        PNG ("text/png"),
        JPG ("text/jpg"),
        TEXT("text/plain"),
        UNKNOWN("text/html");

        private String str_type;
        TYPE_DATA(String s) {str_type = s;}
        String getStr_type() {return str_type;}
    }

    public List<String> getBodyData(String body, TYPE_DATA type) {
        List<String> headers_send;
        headers_send = new ArrayList<>();
        headers_send.add("HTTP/1.1 200 OK");
        headers_send.add(new Date().toString());
        headers_send.add("Server: Super http server ver: 0.1");
        headers_send.add("Content-Language: ru");
        headers_send.add("Content-Type: text/html; charset=utf-8");
        headers_send.add("Content-Length: " + body.length());
        headers_send.add("Connection: close");
        headers_send.add("");
        headers_send.add(body);
        return headers_send;
    }

    public List<String> getTemplateHeader(long sizeBody, TYPE_DATA type) {
        List<String> headers_send;
        headers_send = new ArrayList<>();
        headers_send.add("HTTP/1.1 200 OK");
        headers_send.add(new Date().toString());
        headers_send.add("Server: Super http server ver: 0.1");
        headers_send.add("Content-Language: ru");
        headers_send.add("Content-Type: " + type.getStr_type() + "; charset=utf-8");
        headers_send.add("Content-Length: " + sizeBody);
        headers_send.add("Connection: close");
        headers_send.add("");
        return headers_send;
    }

    /**
     *
     */
    @Override
    public void run() {
        Log.debug("Enter: ");


        List<String> headers;
        try {
            headers = this.getHeaders(m_client.getInputStream());
            String pathToFile = getPathToFile(headers);
            List<String> headers_send = null;

            File f = new File(pathToFile);
            if (!f.exists()) {
                String html = getHtmlWithError();
            } else if (f.isDirectory()) {
                String html = getHtmlDirsAndFile(f);
                headers_send = getBodyData(html, TYPE_DATA.HTML);
            } else {
                CopyFileToOutStream(f, m_client.getOutputStream());
            }

            writeToClient(headers_send, m_client.getOutputStream());
            m_client.getOutputStream().close();

        } catch (IOException e) {
            try {
                m_client.getOutputStream().write(e.toString().getBytes());
            } catch (IOException e1) {
                Log.error("problem", e);
            }
            Log.error("problem", e);
        } finally {
            try {
                m_client.close();
            } catch (IOException e) {
                Log.error("problem", e);
            }
        }
        Log.debug("Leave");
    }

    /**
     *
     * @param f
     * @param outputStream
     */
    private void CopyFileToOutStream(File f, OutputStream outputStream) throws FileNotFoundException {
        List<String> headers_body = getTemplateHeader(f.length(), getFileType(f));

        FileInputStream in = new FileInputStream(f);

        int oneData;
        try {
            if ( in.available() > 0) {
                oneData = in.read();
                outputStream.write(oneData);
            }
        } catch (IOException e) { }
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

        if ( exes.length == 2 ) {
            String exe = exes[1];
            if ( exe.equals("html") ) {
                type_retv = TYPE_DATA.HTML;
            } else if (exe.equals("png")) {
                type_retv = TYPE_DATA.PNG;
            } else if (exe.equals("txt")) {
                type_retv = TYPE_DATA.TEXT;
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
