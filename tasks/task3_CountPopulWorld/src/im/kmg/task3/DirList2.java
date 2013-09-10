package im.kmg.task3;

import java.io.File;
import java.io.FilenameFilter;
import java.util.Arrays;


/**
 * Created with IntelliJ IDEA.
 * User: maxim
 * Date: 9/10/13
 * Time: 11:53 PM
 */
public class DirList2
{
    public static FilenameFilter filter(final String afn)
    {
        // Создание анонимного внутреннего класса:
        return new FilenameFilter() {
            String fn = afn;
            public boolean accept(File dir, String n) {
                // Получаем информацию о пути:
                String f = new File(n).getName();
                return f.indexOf(fn) != -1;
            }
        }; // Конец анонимного внутреннего класса
    }

    public static void main(String[] args) {
        File path = new File(".");
        String[] list;
        if(args.length == 0) {
            list = path.list();
        } else {
            list = path.list(filter(args[0]));
        }

        //Arrays.sort(list, new AlphabeticComparator());
        for(int i = 0; i < list.length; i++) {
            System.out.println(list[i]);
        }
    }
}
