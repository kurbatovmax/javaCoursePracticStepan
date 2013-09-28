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
     * @param sizeBody
     * @param type
     * @return
     */
    public void sendDataToClient(InputStream in,
                                 OutputStream out,
                                 long sizeBody,
                                 DATA_TYPE type,
                                 STATUS_HTTP htmlStatus)
            throws IOException
    {
        String headers = Headers.getResponseheader(sizeBody, type, htmlStatus);
        out.write(headers.getBytes());
        int data = 0;
        byte [] buffer = new byte[4096];
        while ( (data = in.read(buffer)) > 0 ) {
            out.write(buffer);
        }
        out.flush();
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

            if ( f.exists() )  {
                if ( f.isDirectory() ) {
                    // create body stream
                    String htmlBody = new CreateHTML().getHtmlDirsAndFile(f);
                    ByteArrayInputStream bodyInStream = new ByteArrayInputStream(htmlBody.getBytes());

                    //create header as stream
                    String header = Headers.getResponseheader(htmlBody.length(), DATA_TYPE.HTML, STATUS_HTTP.OK);
                    ByteArrayInputStream headerInStream = new ByteArrayInputStream(header.getBytes());
                    SequenceInputStream sequenceInputStream = new SequenceInputStream(headerInStream, bodyInStream);
                    sendDataToClient(sequenceInputStream, clientOutStream);
                } else {
                    DATA_TYPE type = ResourceType.getFileType(f);
                    long size = f.length();
                    InputStream bodyInStream = new FileInputStream(f);
                    sendDataToClient(bodyInStream, clientOutStream, size, type, STATUS_HTTP.OK);
                }
            } else {
                String html = new CreateHTML().getHtmlWithError();
            }
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
    private void sendDataToClient(SequenceInputStream sequenceInputStream,
                                  OutputStream clientOutStream)
            throws IOException
    {
        int data = 0;
        while ( (data = sequenceInputStream.read()) > 0 ) {
            clientOutStream.write(data);
        }
        clientOutStream.flush();
    }
}
