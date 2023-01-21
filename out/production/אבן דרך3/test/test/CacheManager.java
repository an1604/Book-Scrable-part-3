package test;


import java.util.HashSet;

public class CacheManager {
    private  int size;
    private HashSet<String> cache;
    private CacheReplacementPolicy crp;

    public CacheManager(int size, CacheReplacementPolicy crp) {
        this.size = size;
        this.cache=new HashSet<>();
        this.crp = crp;
    }
    public boolean query(String word){
        if(this.cache.contains(word))
            return true;
        return false;
    }

    public void add(String a) {
        this.crp.add(a);
        this.cache.add(a);
        if(cache.size()>size){
            this.cache.remove(this.crp.remove());
        }
//        if(this.cache.size()> this.size){
//            String s = this.crp.remove();
//            this.cache.remove(s);
//            this.crp.add(a);
//            this.cache.add(a);
//        }
//        else {
//            this.size++;
//            this.cache.add(a);
//            this.crp.add(a);
//        }
    }
	

}
