package bean;

import org.apache.commons.lang3.tuple.Pair;

import java.util.*;

public class User {
    public static int uid = 1;
    private int userid;
    private Hashtable<String,Password> password_list;
    private StringBuffer log;
    private ArrayList<Pair<String,Password>> random_password_list;

    public User() {
        random_password_list = new ArrayList<>();
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
    public void  addToRandomList(Pair<String,Password> data){
        random_password_list.add(data);
    }

    public ArrayList<Pair<String, Password>> getRandom_password_list() {
        return random_password_list;
    }

    public boolean pwdListIsEmpty(){
       return password_list.isEmpty();
    }

    @Override
    public String toString() {
        return "User{" +
                "password_list=" + password_list +
                '}';
    }
}
