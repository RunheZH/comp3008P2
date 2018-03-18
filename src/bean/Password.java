package bean;

public class Password {
    private Scheme currentScheme;
    private int password;
    public Password(){

    }
    public Scheme getCurrentScheme() {
        return currentScheme;
    }

    public void setCurrentScheme(Scheme currentScheme) {
        this.currentScheme = currentScheme;
    }

    public int getPassword() {
        return password;
    }

    public void setPassword(int password) {
        this.password = password;
    }
}
