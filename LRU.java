package test;


import java.util.LinkedHashSet;

public class LRU implements CacheReplacementPolicy {
    private LinkedHashSet cache;

    public LRU() {
        this.cache= new LinkedHashSet();
    }

    @Override
    public void add(String word) {
        this.cache.remove(word);
        this.cache.add(word);
    }

    @Override
    public String remove() {
        String s = (String) this.cache.iterator().next();
        this.cache.remove(s);
        return s;
    }


}
