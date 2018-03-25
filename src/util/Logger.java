package util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import bean.User;

public class Logger {
    private static FileWriter writer;
    private static BufferedWriter bf_writer;

    static {
        openFile();


    }
    private static void openFile(){
        File file = new File("./Logging");
        try {
            writer = new FileWriter("./Logging", true);
            bf_writer = new BufferedWriter(writer);
        }catch (IOException e){
            System.err.println(e.getMessage());
        }
    }
    //format: "time","userid","scheme","password type","action","something else"
    public static synchronized void writeLog(User user, String scheme, String pwtype, String action, String data){
        try {

            bf_writer.write("Time: " + System.currentTimeMillis());
            bf_writer.write("UserId: " + user.getUserid());
            bf_writer.write("Scheme: " + scheme);
            bf_writer.write("PasswordType: " + pwtype);
            bf_writer.write("Action: " + action);
            bf_writer.write("Data: " + data + "\n");

        }catch (IOException e){
            System.err.println(e.getMessage());
        }
    }



}
