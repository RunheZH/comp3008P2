package bean;

//Scheme Type
public class Scheme {
    public final static String OCTAL = "Octal";
    public final static String IMAGE = "Image";
    public final static String BINARY = "Binary";

    public static boolean verifyScheme(String scheme) {
        if (!(scheme.equals(OCTAL) ||
                scheme.equals(IMAGE) ||
                scheme.equals(BINARY))) {
            return false;
        }
        return true;
    }

}
