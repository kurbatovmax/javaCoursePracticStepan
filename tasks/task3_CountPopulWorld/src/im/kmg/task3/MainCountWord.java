package im.kmg.task3;

import java.io.*;
import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: user
 * Date: 07.09.13
 * Time: 15:52
 */
public class MainCountWord
{

    public static String tesString = "Во саду ли во городе";
    public static Map<String, Integer> s_table;
    private static String dstFileName = "pscience_count.cvs";


    static {
        s_table = new HashMap<String, Integer>();
    }

    /**
     *
     * @param argv
     */
    public static void main(String argv[]) throws IOException {

        if (argv.length == 0) {
            printHelp();
            return;
        }

        File file = new File(argv[0]);
        FileInputStream inputStream = null;
        try {
            inputStream = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            System.err.println("error: file " + file.getAbsolutePath() + " not found");
            return;
        }

        BufferedInputStream bufferedReader = new BufferedInputStream(inputStream);
        Reader reader = new InputStreamReader(bufferedReader);

        StringBuffer des = new StringBuffer();

        int oneChar;
        try {
            while (true) {
                oneChar = reader.read();
                if (oneChar == -1) {
                    if (des.length() > 0) {
                        storeWorld(des.toString());
                    }
                    break;
                }

                if ( !Character.isLetter(oneChar) ) {
                    if (des.length() > 0) {
                        storeWorld(des.toString());
                        des = new StringBuffer();
                    }
                    continue;
                }
                des.append((char)oneChar);
            }
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }   finally {
            reader.close();
        }

        List<MyPair> list = MyPair.convertMap(s_table);
        Collections.sort(list);

        for (MyPair item : list) {
            System.out.println( item.getKey() + " : "  + item.getValue());
        }
        createCSV(dstFileName, list);
        System.out.println("Add done");
    }

    /**
     *
     * @param str
     */
    private static void storeWorld(String str) {
        str = str.toLowerCase();
        if (str.length() <= 1) {
            return;
        }

        if ( s_table.containsKey(str) ) {
            Integer value = s_table.get(str);
            value++;
            s_table.put(str, value);
        }else {
            s_table.put(str, 1);
        }
    }

    /**
     *
     */
    private static void printHelp() {
        System.out.println("how to use");
        System.out.println("appname [name file]");
    }

    /**
     *
     * @param f
     * @param list
     */
    public static void createCSV(String f, List<MyPair> list) throws IOException {
        File hfile = new File(f);
        FileOutputStream outputStream = null;

        try {
            try {
                outputStream = new FileOutputStream(hfile);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

            Writer writer = new OutputStreamWriter(outputStream);

            int i = 0;
            for (MyPair item : list) {
                i++;
                String s = item.getKey() + ", " + item.getValue() + "\n";
                if (i == 18140) {
                   System.out.println(s);
                }

                try {
                    writer.write(s);
                    writer.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } finally {
            assert outputStream != null;

            outputStream.close();
        }
    }
}


