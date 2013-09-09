package im.kmg.sibniar2;

import java.io.*;

/**
 * Created with IntelliJ IDEA.
 * User: KurbatovM
 * Date: 9/9/13
 * Time: 6:05 PM
 */
public class GetCommand
{
    private final String m_nameFile;

    GetCommand(String argv[]) {
        if (argv.length > 0) {
            m_nameFile = argv[0];
        }
    }

    /**
     *
     * @return
     */
    public String getLine() throws FileNotFoundException {
        String cmdLineRet = null;
        FileReader readerFile = null;
        BufferedReader bufferedReader = null;

        readerFile = new FileReader( new File(m_nameFile));
        bufferedReader = new BufferedReader(readerFile);

        try {
            cmdLineRet = in.readLine();
        } catch (IOException e) {
            System.err.println(e.getLocalizedMessage());
        }

        //printStartMsg();
        //String s1 = new Scanner(System.in).nextLine();
        //CliParser cmdParser = new CliParser(s1);
         return  cmdLineRet;
    }
}
