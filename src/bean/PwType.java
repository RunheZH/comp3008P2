package bean;

public class PwType {
    public static String EMAIL = "Email";
    public static String BANKING = "Banking";
    public static String SHOPPING = "Shopping";
    private String type;

    public PwType() {

    }

    public int setType(String type){
        if(!(type.equals(EMAIL)||
            type.equals(BANKING)||
            type.equals(SHOPPING))){
            return -1;
        }
        this.type = type;
        return 1;
    }

    public String getType() {
        return type;
    }
}
