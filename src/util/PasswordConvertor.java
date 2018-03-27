package util;

import bean.Password;
import bean.Scheme;

public class PasswordConvertor {

    public static Password convertPasswordTo(Password original,String target_scheme){
        //TODO#2
        //String curr_scheme = original.getCurrentScheme();
        //String curr_rep = original.getPassword_representative();

        int curr_password = original.getPassword();
        String curr_rep = Integer.toString(curr_password);

        if (target_scheme.equals(Scheme.IMAGE)){

            StringBuilder updated_rep = new StringBuilder(curr_rep);

            for (int i = 0; i <= 4; i+=2) {
                char target = curr_rep.charAt(i);

                switch (target){
                    case '1': updated_rep.setCharAt(i,'A');
                    case '2': updated_rep.setCharAt(i,'B');
                    case '3': updated_rep.setCharAt(i,'C');
                    case '4': updated_rep.setCharAt(i,'D');
                    case '5': updated_rep.setCharAt(i,'E');
                    case '6': updated_rep.setCharAt(i,'F');
                    case '7': updated_rep.setCharAt(i,'G');
                    case '8': updated_rep.setCharAt(i,'H');

                }
            }

            original.setPassword_representative(updated_rep.toString());

        }else if(target_scheme.equals(Scheme.BINARY)){
            String new_rep = Integer.toBinaryString(curr_password);
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
