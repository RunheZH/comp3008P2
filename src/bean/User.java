package bean;

import java.util.*;

public class User {
    public static int uid = 1;
    private int userid;
    private Hashtable<String,Password> password_list;
    private StringBuffer log;

    public User() {
        password_list = new Hashtable<>();
        log = new StringBuffer();
    }
    public Password getPassword(String type){
        return password_list.get(type);
    }
    public void addPassword(String type,Password pw){
        password_list.put(type,pw);
    }
    public void addLog(String l){
        log.append(l);
        log.append("\n");
    }
    public String getLog(){
        return log.toString();
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }
    public void removePassword(String pwtype){
        assert password_list.get(pwtype)!=null;
        password_list.remove(pwtype);
    }
    @Override
    public String toString() {
        return "User{" +
                "password_list=" + password_list +
                '}';
    }
}
