package test;


import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.LinkedList;

public class LFU implements CacheReplacementPolicy{
    private LinkedHashMap<String,Integer> cache;
    private LinkedList<String> list;

    private static String minimum;

    public LFU() {
        this.cache= new LinkedHashMap();
        this.list= new LinkedList();
    }

    @Override
    public void add(String word) {
        this.list.add(word);
        if(!this.cache.containsKey(word)) {
            this.cache.put(word, 1);
        }
        else {
            this.cache.put(word, this.cache.get(word) + 1);
        }
        list.sort(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return cache.get(o1)-cache.get(o2);
            }
        });
        minimum=this.list.get(0);
    }

    @Override
    public String remove() {
        this.cache.remove(minimum);
        this.list.remove(0);
        String m= minimum;
        minimum= this.list.get(0);
        return m;
    }
//    public void sort() {
//        LinkedList<String> tmp = this.list;
//        for (int i = 0; i < this.list.size(); i++) {
//            for (int j = i + 1; j < this.list.size(); i++) {
//                if (this.cache.get(tmp.get(i)) > this.cache.get(tmp.get(j))) {
//                    tmp.remove(i);
//                    tmp.add(i, tmp.get(j));
//                    tmp.remove(j);
//                    tmp.add(j, this.list.get(j));
//                }
//            }
//            this.list = tmp;
//        }
//    }

}
