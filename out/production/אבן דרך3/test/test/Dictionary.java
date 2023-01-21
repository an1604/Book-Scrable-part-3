package test;


import java.io.File;
import java.io.FileNotFoundException;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

public class Dictionary {
    private CacheManager lruCache;
    private CacheManager lfuCache;
    private BloomFilter bf;

    private String[] files;
    public Dictionary(String...fileNames){
        this.files= new String[fileNames.length];
        this.lfuCache=new CacheManager(400, new LFU());
        this.lruCache= new CacheManager(100,new LRU());
        this.bf= new BloomFilter(256,"MD5","SHA1");
        int i=0;
        for(String s:fileNames){
            this.bf.add(s);
            this.files[i]=s;
            i++;
        }
    }

    public boolean query(String is) {
        if(this.lruCache.query(is))
            return true;
        else if (this.lfuCache.query(is))
            return false;
        else if(bf.contains(is)) {
            this.lfuCache.add(is);
            return true;}
        else if(containInArr(is)) {
            return true;
             }
        else{
            this.lruCache.add(is);
            return false;
        }
    }

    public boolean challenge(String lazy) {
        boolean b= IOSearcher.search(lazy,files);
        if(b==true){
            this.lruCache.add(lazy);
            return true;
        }
        else {
            this.lfuCache.add(lazy);
            return false;
        }
    }
    boolean containInArr(String is) {
        for (String s : this.files) {
            File file= new File(s);
            Scanner scanner = null;
            try {
                scanner = new Scanner(file);
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
            while (scanner.hasNextLine()) {
                String data = scanner.nextLine();
                if (data.contains((is)))
                    return true;
            }
        }
        return  false;
    }
}
