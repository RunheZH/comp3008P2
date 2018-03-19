package util;

import bean.Password;
import bean.Scheme;

public class PasswordConvertor {
    public static Password convertPasswordTo(Password original,Scheme target_scheme){
        //TODO#2
        Scheme curr_scheme = original.getCurrentScheme();
        if (curr_scheme != target_scheme){
            if(curr_scheme == "OCTAL" && target_scheme == "IMAGE"){
                int p_number = original.getPassword();
                int digit = 0;
                int factor = 1000;
                while (p_number > 0){
                    digit = p_number % factor;
                    factor *= 100;

                }

            }else if(curr_scheme == "OCTAL" && target_scheme == "BINARY"){


            }else if(curr_scheme == "IMAGE" && target_scheme == "OCTAL"){

            }else if(curr_scheme == "IMAGE" && target_scheme == "BINARY"){

            }else if(curr_scheme == "BINARY" && target_scheme == "OCTAL"){


            }else { // curr_scheme == "BINARY" && target_scheme == "IMAGE"

            }

            original.setCurrentScheme(target_scheme);


        }
        return null;
    }
}
