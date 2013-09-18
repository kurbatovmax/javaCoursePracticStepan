import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/*
                     /*
                // client connect
                InputStream in   = clientSocket.getInputStream();
                OutputStream out = clientSocket.getOutputStream();
                Reader reader = new InputStreamReader(in);
                StringBuffer buffer = new StringBuffer();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {}

                while ( true ) {
                    if ( reader.ready() == false ) { break; }
                    int res = reader.read();
                    if (res == -1) { break;}
                    buffer.append((char) res);
                }

                if ( buffer.length() > 0 ) {
                    System.out.print(buffer);
                    System.out.flush();
                }

                Writer sender = new OutputStreamWriter(out);
                Date date = new Date();
                sender.write("Server connect\nHello you first server\ndate: " + date.toString() + "\n\n\n");
                sender.flush();
                clientSocket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            serverSocket.close();
        }
        try {
            Thread.currentThread().join();
        } catch (InterruptedException e) {
            System.out.println("App done");
        }
    }
 */

/**
 * Created with IntelliJ IDEA.
 * User: maxim
 * Date: 9/15/13
 * Time: 6:54 PM
 */
public class MainHttpd
{
    /**
     *
     * @param argv - arguments
     */
    public static  void main (String argv[]) throws IOException {
        System.out.println("Start httpd");
        ServerSocket serverSocket = null;
        try {
            System.out.println("Create socket server");
            serverSocket =  new ServerSocket(30000);
            while (true) {
                new Client(serverSocket.accept());
                System.out.println("accept done");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            serverSocket.close();
        }
    }
}
