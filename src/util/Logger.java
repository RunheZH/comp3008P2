package util;

import java.io.File;
import java.io.FileWriter;

public class Logger {
    static {
        openFile();

    }
    private static void openFile(){
        File file = new File("./Logging");
        FileWriter writer = new FileWriter("./Logging", true);



    }
    //format: "time","userid","scheme","password type","action","something else"
    public static synchronized void writeLog(User user, String scheme, String pwtype, String action, String data){


        System.currentTimeMillis();

    }



}
