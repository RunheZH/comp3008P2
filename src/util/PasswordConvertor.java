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

        if (target_scheme.equals("IMAGE")){

            StringBuilder updated_rep = new StringBuilder(curr_rep);

            for (int i = 0; i <= 4; i+=2) {
                char target = curr_rep.charAt(i);

                switch (target){
                    case '0': updated_rep.setCharAt(i,'A');
                    case '1': updated_rep.setCharAt(i,'B');
                    case '2': updated_rep.setCharAt(i,'C');
                    case '3': updated_rep.setCharAt(i,'D');
                    case '4': updated_rep.setCharAt(i,'E');
                    case '5': updated_rep.setCharAt(i,'F');
                    case '6': updated_rep.setCharAt(i,'G');

                }
            }

            original.setPassword_representative(updated_rep.toString());

        }else if(target_scheme.equals("BINARY")){

            String new_rep = "";
            String binary_digits = "1";

            for(int i = 0; i < 7; i++){

                //get value at each index starting from 0;
                char target = curr_rep.charAt(i);
                int value = Character.getNumericValue(target);

                //add digits to the new_representative
                for (int j = 0; j < value; j++) {
                    new_rep += binary_digits;
                }

                //switch digit (1/0)
                if (binary_digits.equals("1")){binary_digits = "0";}
                else {binary_digits = "1";}

            }
            original.setPassword_representative(new_rep);

        }else{ //target_scheme.equals("OCTAL")

            original.setPassword_representative(Integer.toString(original.getPassword()));
        }


        return original;
    }
}
