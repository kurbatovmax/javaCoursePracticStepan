package im.kmg.PopulWorld;


import java.util.*;

public class MyPair implements Comparable
{
    private String key;
    private Integer value;

    /**
     *
     * @param key
     * @param value
     */
    MyPair(String key, Integer value){
        this.key = key;
        this.value = value;
    }

    /**
     *
     *
     * @param list
     * @return
     */
    public static List<MyPair> convertMap(Map<String, Integer> list) {
        List<MyPair> m = new ArrayList<MyPair>();
        Set<String> keys = list.keySet();

        Integer v;
        for (String s: keys) {
            v = list.get(s);
            MyPair tmp = new MyPair(s, v );
            m.add(tmp);
        }

        return m;
    }


    @Override
    public int compareTo(Object o) {
        MyPair t = (MyPair) o;
        int p1 = value;
        int p2 = t.getValue();
        return p2-p1;
    }

    public String getKey() {
        return key;
    }

    public Integer getValue() {
        return value;
    }
}
