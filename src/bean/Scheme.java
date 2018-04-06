package bean;

//Scheme Type
/**
 * plain old java object
 * store data
 * declare password scheme types
 */
public class Scheme {
    public final static String OCTAL = "Octal";
    public final static String IMAGE = "Image";
    public final static String BINARY = "Binary";

    /**
     * to verify if the scheme name is valid
     * @param scheme
     * @return
     */
    public static boolean verifyScheme(String scheme) {
        if (!(scheme.equals(OCTAL) ||
                scheme.equals(IMAGE) ||
                scheme.equals(BINARY))) {
            return false;
        }
        return true;
    }

}
