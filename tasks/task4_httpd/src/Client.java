import java.io.*;
import java.lang.reflect.Method;
import java.net.Socket;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

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

    Client(Socket client) {
        synchronized (this) {
            m_tThread = new Thread(this, getClass().getName());
            m_client  = client;
            m_tThread.start();
        }
    }

    @Override
    public void run() {
        printDbgMsg("Enter: ", new Throwable());
        List <String> headers;
        try {
           headers = this.getHeaders(m_client.getInputStream());
            try {
                m_tThread.sleep(10);
            } catch (InterruptedException e) {}
            writeToClient(headers, m_client.getOutputStream());

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
            list.add(new Date().toString());
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
        System.err.println(getNameMethod(cthrow) + " "  + msg);
    }
}
