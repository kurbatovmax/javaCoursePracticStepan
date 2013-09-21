import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created with IntelliJ IDEA.
 * User: maxim
 * Date: 9/15/13
 * Time: 6:54 PM
 */
public class MainHttpd
{
    static Thread m_Thread;
    static {
        m_Thread = Thread.currentThread();
    }

    /**
     *
     * @param argv - arguments
     */
    public static  void main (String argv[]) throws IOException {
        System.out.println("Start httpd");
        ServerSocket serverSocket = null;
        try {
            serverSocket =  new ServerSocket(30000);
            while (true) {
                Socket sClient = serverSocket.accept();
                new Client(sClient);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            serverSocket.close();
        }
    }
}
