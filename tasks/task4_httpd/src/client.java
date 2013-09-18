import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
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
    private Socket m_client;

    Client(Socket client) {
        m_tThread = new Thread(this, getClass().getName());
        m_client  = client;
        m_tThread.start();
    }

    @Override
    public void run() {
        byte []inputBuffer = new byte[1024];
        InputStream inputStream;
        OutputStream outputStream;
        try {
            inputStream = m_client.getInputStream();

            if ( inputStream.available() > 0) {
                inputStream.read(inputBuffer);
            }

            outputStream = m_client.getOutputStream();
            outputStream.write(inputBuffer);
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
