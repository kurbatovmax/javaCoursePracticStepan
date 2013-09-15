/**
 * Created with IntelliJ IDEA.
 * User: maxim
 * Date: 9/15/13
 * Time: 6:56 PM
 */
public class httpd implements Runnable
{
    private  Thread m_tThread;
    private long m_i;

    httpd() {
        m_tThread = new Thread(this, getClass().getName());
        m_i = 0;
    }

    httpd(String name) {
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
    public void start() {
        m_tThread.start();
    }
}
