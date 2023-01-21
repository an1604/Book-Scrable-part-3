//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package test;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class BookScrabbleHandler implements ClientHandler {
    DictionaryManager dictionaryManager = DictionaryManager.get();
    Scanner in;
    PrintWriter out;

    public BookScrabbleHandler() {
    }

    public void handleClient(InputStream inFromclient, OutputStream outToClient) {
        try {
            this.in = new Scanner(inFromclient);
            this.out = new PrintWriter(outToClient);
            String[] arguments = this.in.next().split(",");
            ArrayList<String> argsList = new ArrayList(Arrays.asList(arguments));
            argsList.remove(0);
            boolean result = false;
            if (!arguments[0].equals("Q") && !arguments[0].equals("C")) {
                this.out.println("Query Faild");
            } else {
                String[] challenge;
                if (arguments[0].equals("Q")) {
                    challenge = (String[])argsList.toArray(new String[argsList.size()]);
                    result = this.dictionaryManager.query(challenge);
                } else if (arguments[0].equals("C")) {
                    challenge = (String[])argsList.toArray(new String[argsList.size()]);
                    result = this.dictionaryManager.challenge(challenge);
                }
            }

            if (result) {
                this.out.println("true");
            } else {
                this.out.println("false");
            }

            this.close();
        } catch (Exception var7) {
            throw new RuntimeException(var7);
        }
    }

    public void close() {
        try {
            this.out.flush();
            this.in.close();
            this.out.close();
        } catch (Exception var2) {
            var2.printStackTrace();
            throw new RuntimeException(var2);
        }
    }
}
