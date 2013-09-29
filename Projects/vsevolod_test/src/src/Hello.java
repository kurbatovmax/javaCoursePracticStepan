package src;

import java.util.Properties;

/**
 * Created with IntelliJ IDEA.
 * User: vsev
 * Date: 25.06.13
 * Time: 21:08
 * To change this template use File | Settings | File Templates.
 */
public class Hello {
    public static  void main(String args[]) {

        System.out.println("Hello world!");
        System.out.println("My args:");
        for (int i = 0; i<args.length; i++) {
            System.out.println("args["+i+"] = "+args[i]);
        }
        for (String s : args) {
            System.out.println(s);
        }
        Properties props = System.getProperties();
        //props.setProperty("file.encoding", "cp866");
        for (Object o : props.keySet()) {
            System.out.println("Property: "+o+" : " + props.get(o));
        }
        Runtime r = Runtime.getRuntime();
        System.out.println("free mem: " + r.freeMemory());
        byte[] array = new byte[10000000];
        
        System.out.println("free mem: " + r.freeMemory());
        array = null;
        System.gc();
        System.out.println("free mem: " + r.freeMemory());     
    }
}
