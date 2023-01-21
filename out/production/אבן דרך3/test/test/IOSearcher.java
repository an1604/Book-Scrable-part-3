package test;


import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class IOSearcher {
    public static boolean search(String s , String... fileNames) {
        for (String s1 : fileNames) {
            try {
                File file = new File(s1);
                Scanner myReader = new Scanner(file);
                while (myReader.hasNextLine()) {
                    String data = myReader.nextLine();
                    if (data.contains(s))
                        return true;
                }
                myReader.close();
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
        return false;
    }

//    public static boolean search(String is, String s, String s1) {
//        String[] fileNames= new String[2];
//        fileNames[0]=s;
//        fileNames[1]=s1;
//        for(String s2 :fileNames){
//            try {
//                File file = new File(s2);
//                Scanner myReader=new Scanner(file);
//                while(myReader.hasNextLine()){
//                    String data =myReader.nextLine();
//                    if(data.contains(s))
//                        return true;
//                }
//                myReader.close();
//            } catch (FileNotFoundException e) {
//                throw new RuntimeException(e);
//            }
//        }
//        return  false;
//    }
//}
    }