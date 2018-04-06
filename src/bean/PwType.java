package bean;
/**
 * plain old java object
 * store data
 * declare password types
 */
import java.util.HashMap;
import java.util.Map;

public class PwType{
    public static String EMAIL = "Email";
    public static String BANKING = "Banking";
    public static String SHOPPING = "Shopping";
    public static Map<Integer,String> TypeMapping = new HashMap<>();
    static {
        TypeMapping.put(1,EMAIL);
        TypeMapping.put(2,BANKING);
        TypeMapping.put(3,SHOPPING);
    }
}
