package util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;

import bean.User;

public class Logger {
    private static FileWriter writer;
    private static BufferedWriter bf_writer;
    private static final String FILEPATH = "/home/largetimmo/Logging.txt";
    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    //format: "time","userid","scheme","password type","action","something else"
    public static synchronized void writeLog(User user, String action, String data1,String data2){
        Date date = new Date();
        System.out.println("\""+sdf.format(date)+"\",\""+ user.getUserid()+"\",\""+action+"\",\""+data1+"\",\""+data2+"\"");
        try {
            bf_writer = new BufferedWriter(new FileWriter(FILEPATH,true));


            bf_writer.write("\""+sdf.format(date)+"\",\""+ user.getUserid()+"\",\""+action+"\",\""+data1+"\",\""+data2+"\"");
            bf_writer.newLine();
            bf_writer.close();

        }catch (IOException e){
            System.err.println(e.getMessage());
        }
    }




}
