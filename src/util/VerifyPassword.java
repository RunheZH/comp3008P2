package util;

import bean.Password;
import bean.Scheme;


public class VerifyPassword {
    /**
     * To verify user entered password is correct ot not
     *
     * @param password password object, contains original password and password representative
     * @param user_input user entered password
     * @return boolean
     */


    public static boolean verify(Password password, String user_input){

        Password image_pwd = password;
        Password binary_pwd = password;

        String octal_rep = Integer.toString(password.getPassword());
        String image_rep = PasswordConvertor.convertPasswordTo(image_pwd, Scheme.IMAGE).getPassword_representative();
        String binary_rep = PasswordConvertor.convertPasswordTo(binary_pwd, Scheme.BINARY).getPassword_representative();

        if (user_input.equals(octal_rep) || user_input.equals(image_rep) || user_input.equals(binary_rep) ){ return true;}


        return false;
    }
}
