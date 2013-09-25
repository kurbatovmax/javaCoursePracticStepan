import org.apache.log4j.Logger;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Properties;

/**
 * Created with IntelliJ IDEA.
 * User: maxim
 * Date: 9/15/13
 * Time: 6:54 PM
 */
public class MainHttpd
{
    private static final Logger Log = Logger.getLogger(MainHttpd.class);
    private static final Properties Configs = new Properties();

    static {
        try {
            String nameConfigFile = "httpd.conf";
            InputStream in = MainHttpd.class.getResourceAsStream(nameConfigFile);
            Configs.load(in);
        } catch (IOException e) {
            Log.fatal("", e);
        }
    }

    /**
     *
     * @param argv - arguments
     */
    public static  void main (String argv[]) throws IOException {
        Log.info("Start httpd");
        ServerSocket serverSocket = null;
        Integer port = Integer.valueOf(Configs.getProperty("server_port"));
        try {
            serverSocket =  new ServerSocket(port);
            while (true) {
                Socket sClient;
                try {
                    sClient = serverSocket.accept();
                } catch (IOException e) {
                    Log.fatal("", e);
                    break;
                }
                new Client(sClient);
            }
        } catch (Exception e) {
            Log.fatal("", e);
        } finally {
            if (serverSocket!=null) {
                serverSocket.close();
            }
        }
    }

    /**
     *
     * @return
     */
    public static String getHttpRootFolder() {
        String rootFolder = Configs.getProperty("server_root_folder");
        if ( rootFolder == null) {
            rootFolder = "/";
        } else {
            rootFolder = rootFolder.replaceAll("\"", "");
        }
        return rootFolder;
    }
}
