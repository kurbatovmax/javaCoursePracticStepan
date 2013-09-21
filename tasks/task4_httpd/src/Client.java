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
public class Client implements Runnable
{
    private  Thread m_tThread;
    private Socket m_client;

    /**
     *
     * @param client
     */
    Client(Socket client) {
        synchronized (this) {
            m_tThread = new Thread(this, getClass().getName());
            m_client  = client;
            m_tThread.start();
        }
    }

    /**
     *
     */
    @Override
    public void run() {
        printDbgMsg("Enter: ", new Throwable());
        List <String> headers;
        try {
            headers = this.getHeaders(m_client.getInputStream());
            //headers.add(new Date().toString());
            //writeToClient(headers, m_client.getOutputStream());

            Properties getsParam = getListGetParams(headers);

            //
            String[] arrayDir;

            arrayDir = new File("/").list();

            List<String> listDir = Arrays.asList(arrayDir);
            writeToClient(listDir, m_client.getOutputStream());

            m_client.getOutputStream().close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                m_client.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        printDbgMsg("Leave: ", new Throwable());
    }

    /**
     *
     * @param headers
     * @return
     */
    private Properties getListGetParams(List<String> headers) throws MalformedURLException {
        Properties retv = null;

        for (String s : headers ) {
            if ( s.matches("^GET\\s.*")) {
                retv = parserGetParams(s);
                break;
            }
        }
        return retv;
    }

    /**
     *
     * @param s
     * @return
     */
    private Properties parserGetParams(String s) throws MalformedURLException {
        Properties retv = new Properties();

        URL url = new URL(s);

        /*
        int index = s.indexOf("?");
        if (index == -1) {
            return retv;
        }

        s = s.substring(index);
        s = s.replace("?", "");

        String[] listP = s.split("\\s");

        if (listP.length == 2 ){
            String [] listGet = listP[0].split("&");
            for (String str : listGet ) {
                String tmp[] = str.split("=");
                if (tmp.length == 2) {
                    retv.setProperty(tmp[0], tmp[1]);
                }
            }
        }
        */

        return retv;
    }

    /**
     *
     * @param in stream for reading
     * @return   list of header
     */
    List<String> getHeaders(InputStream in) {
        printDbgMsg("Enter: ", new Throwable());
        List<String> retv = new ArrayList<>();
        Reader reader = new InputStreamReader(in);
        BufferedReader bufferedReader = new BufferedReader(reader);

        try {
            while (bufferedReader.ready()) {
                String tmp =  bufferedReader.readLine();
                if (tmp.equals("\n") || tmp.isEmpty() ) {
                    break;
                }
                retv.add(tmp);
            }
        } catch (IOException e) {
            printDbgMsg("Leave: ", new Throwable());
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        printDbgMsg("Leave: ", new Throwable());
        return retv;
    }

    /**
     *
     * @param list
     * @param out
     * @throws IOException
     */
    void writeToClient(List<String> list, OutputStream out) throws IOException {
        printDbgMsg("Enter: ", new Throwable());
        if (!list.isEmpty()) {
           BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(out));

            for (String item : list) {
                writer.write(item + "\n");
                writer.flush();
            }
        }
        printDbgMsg("Leave: ", new Throwable());
    }

    /**
     *
     * @param e
     * @return
     */
    String getNameMethod(Throwable e) {
        String retv = e.getStackTrace()[0].toString();
        return  retv;
    }

    /**
     *
     * @param msg
     * @param cthrow
     */
    void printDbgMsg(String msg, Throwable cthrow) {
        System.out.println(getNameMethod(cthrow) + " "  + msg);
    }
}
