package util;
/**
 * Convert password from octal to binary or image
 */

import bean.Password;
import bean.Scheme;

public class PasswordConvertor {

    public static Password convertPasswordTo(Password original,String target_scheme){
        //String curr_scheme = original.getCurrentScheme();
        //String curr_rep = original.getPassword_representative();

        int curr_password = original.getPassword();
        String curr_rep = Integer.toString(curr_password);

        if (target_scheme.equals(Scheme.IMAGE)){

            StringBuilder updated_rep = new StringBuilder(curr_rep);

            for (int i = 0; i <= 4; i+=2) {
                char target = curr_rep.charAt(i);

                switch (target){
                    case 49: updated_rep.setCharAt(i,'A');break;
                    case 50: updated_rep.setCharAt(i,'B');break;
                    case 51: updated_rep.setCharAt(i,'C');break;
                    case 52: updated_rep.setCharAt(i,'D');break;
                    case 53: updated_rep.setCharAt(i,'E');break;
                    case 54: updated_rep.setCharAt(i,'F');break;
                    case 55: updated_rep.setCharAt(i,'G');break;
                    case 56: updated_rep.setCharAt(i,'H');break;

                }
            }

            original.setPassword_representative(updated_rep.toString());

        }else if(target_scheme.equals(Scheme.BINARY)){

            String new_rep;
            int binary_pwd = Integer.parseInt(Integer.toString(curr_password), 8);
            new_rep = Integer.toBinaryString(binary_pwd);

            while (new_rep.length()<21){
                new_rep = "0"+new_rep;
            }
            original.setPassword_representative(new_rep);

        }else{ //target_scheme.equals("OCTAL")
            original.setPassword_representative(curr_rep);
        }
        return original;
    }
}
