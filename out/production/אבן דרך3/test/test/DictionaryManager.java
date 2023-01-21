//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package test;

import java.util.HashMap;

public class DictionaryManager {
    private HashMap<String, Dictionary> dictionaryMap = new HashMap();
    private static DictionaryManager dm;

    public DictionaryManager() {
    }

    public static DictionaryManager get() {
        if (dm == null) {
            dm = new DictionaryManager();
        }

        return dm;
    }

    public int getSize() {
        return this.dictionaryMap.size();
    }

    public boolean challenge(String... args) {
        if (args.length < 1) {
            return false;
        } else {
            String s = args[args.length - 1];
            boolean result = false;

            for(int i = 0; i < args.length - 1; ++i) {
                if (!this.dictionaryMap.containsKey(args[i])) {
                    this.dictionaryMap.put(args[i], new Dictionary(new String[]{args[i]}));
                }

                if (((Dictionary)this.dictionaryMap.get(args[i])).challenge(s)) {
                    result = true;
                }
            }

            return result;
        }
    }

    public boolean query(String... args) {
        if (args.length < 1) {
            return false;
        } else {
            String s = args[args.length - 1];
            boolean result = false;

            for(int i = 0; i < args.length - 1; ++i) {
                if (!this.dictionaryMap.containsKey(args[i])) {
                    this.dictionaryMap.put(args[i], new Dictionary(new String[]{args[i]}));
                }

                if (((Dictionary)this.dictionaryMap.get(args[i])).query(s)) {
                    result = true;
                }
            }

            return result;
        }
    }
}
