import java.net.Socket;

/**
 * Created with IntelliJ IDEA.
 * User: maxim
 * Date: 9/15/13
 * Time: 6:56 PM
 */
public class Client implements Runnable
{
    private  Thread m_tThread;
    private long m_i;
    private Socket m_client;

    Client() {
        m_tThread = new Thread(this, getClass().getName());
        m_i = 0;
    }

    Client(String name) {
        m_tThread = new Thread(this, name);
        m_i = 0;
    }

    @Override
    public void run() {
        while(true) {
            m_i++;
            System.out.println(Thread.currentThread().getName()  +  " i=:" + m_i);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }
        }
    }

    /**
     *
     */
    public void start(Socket client) {
        m_client = client;
        m_tThread.start();
    }
}
