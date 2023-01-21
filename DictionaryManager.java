package test;


import java.util.ArrayList;
import java.util.HashMap;

public class DictionaryManager {
    private HashMap<String , test.Dictionary> dictionaryMap;

    private static DictionaryManager dm;
    public DictionaryManager(){
        this.dictionaryMap=new HashMap<>();
    }

    public static DictionaryManager get() {
        if(dm==null){
            dm=new DictionaryManager();
        }
        return dm;
    }



    public int getSize() {
        return dictionaryMap.size();
    }



    public boolean challenge( String... args) {
    if(args.length<1){
        return false;
    }
    String s = args[args.length-1];
    boolean result=false;
    for(int i=0;i<args.length-1;i++){
        if(!dictionaryMap.containsKey(args[i])){
            dictionaryMap.put(args[i],new Dictionary(args[i]));
        }
        if(dictionaryMap.get(args[i]).challenge(s)){
            result=true;
        }
    }
    return result;
    }

    public boolean query( String... args) {
        if(args.length<1){
            return false;
        }
        String s= args[args.length-1];
        boolean result = false;
        for(int i=0;i<args.length-1;i++){
            if(!dictionaryMap.containsKey(args[i])){
                dictionaryMap.put(args[i],new Dictionary(args[i]));
            }
            if(dictionaryMap.get(args[i]).query(s)){
                result=true;
            }
        }
        return result;
    }
}
