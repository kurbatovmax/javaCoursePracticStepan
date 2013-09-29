import org.apache.log4j.Logger;

import java.io.*;
import java.net.*;
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
    private  Headers m_headers;

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
     */
    @Override
    public void run() {
        this.Log.debug("Enter: ");
        OutputStream clientOutStream = null;
        InputStream clientInStream = null;
        try {

            clientInStream = m_client.getInputStream();
            clientOutStream = m_client.getOutputStream();
            this.m_headers = new Headers(clientInStream);
            String pathToFile = this.m_headers.getPathResourceDecode();
            if (pathToFile == null) {
                return;
            }
            String fileName = MainHttpd.getHttpRootFolder() + File.separator + pathToFile;
            File f = new File(fileName);


            // common data
            DATA_TYPE type                  = DATA_TYPE.HTML;
            STATUS_HTTP http_status         = STATUS_HTTP.OK;
            InputStream headerInStream      = null;
            InputStream bodyInStream        = null;
            String htmlBody                 = "";
            String header                   = "";
            SequenceInputStream sequenceInputStream = null;

            long sizeBody = 0;

            if ( f.exists() == false )  {
                // body
                type = DATA_TYPE.HTML;
                htmlBody = new CreateHTML().getHtmlWithError();
                sizeBody = htmlBody.getBytes().length;
                bodyInStream = new ByteArrayInputStream(htmlBody.getBytes());
                // header
                header = Headers.getResponseheader(sizeBody, type, http_status);
            } else if ( f.isDirectory() ) {
                // body
                htmlBody = new CreateHTML().getHtmlDirsAndFile(f);
                sizeBody = htmlBody.getBytes().length;
                bodyInStream = new ByteArrayInputStream(htmlBody.getBytes());
                // header
                header = Headers.getResponseheader(sizeBody, type, http_status);
            } else {
                type = ResourceType.getFileType(f);

                // body
                sizeBody = f.length();
                bodyInStream = new FileInputStream(f);
                // header
                header = Headers.getResponseheader(sizeBody, type, http_status);
            }

            headerInStream = new ByteArrayInputStream(header.getBytes());
            sequenceInputStream = new SequenceInputStream(headerInStream, bodyInStream);
            sendDataToClient(sequenceInputStream, clientOutStream);

        } catch (IOException e) {
            this.Log.fatal("", e);
        } finally {
            try {
                m_client.close();
            } catch (IOException e) {
                this.Log.fatal("", e);
            }
        }
    }

    /**
     *
     * @param sequenceInputStream
     * @param clientOutStream
     */
    private void sendDataToClient(
            SequenceInputStream sequenceInputStream,
            OutputStream clientOutStream)
            throws IOException
    {
        int data = 0;
        byte [] buffer = new byte[4096];
         while ( (data = sequenceInputStream.read(buffer)) != -1 ) {
             clientOutStream.write(buffer, 0, data);
        }
        clientOutStream.flush();
    }
}
