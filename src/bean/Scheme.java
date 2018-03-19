package bean;

//Scheme Type
public class Scheme {
    public static String OCTAL = "Octal";
    public static String IMAGE = "Image";
    public static String BINARY = "Binary";
    private String currentScheme;

    public Scheme() {
    }
    public static Scheme CreateOctalScheme(){
        Scheme scheme = new Scheme();
        scheme.setScheme(OCTAL);
        return scheme;
    }
    public static Scheme CreateImageScheme(){
        Scheme scheme = new Scheme();
        scheme.setScheme(IMAGE);
        return scheme;
    }
    public static Scheme CreateBinaryScheme(){
        Scheme scheme = new Scheme();
        scheme.setScheme(BINARY);
        return scheme;
    }
    public int setScheme(String scheme) {
        if (!(scheme.equals(OCTAL) ||
                scheme.equals(IMAGE) ||
                scheme.equals(BINARY))) {
            return -1;
        }
        currentScheme = scheme;
        return 1;
    }

    public String getCurrentScheme() {
        return currentScheme;
    }
}
