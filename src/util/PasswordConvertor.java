package util;

import bean.Password;
import bean.Scheme;

public class PasswordConvertor {
    public static Password convertPasswordTo(Password original,String target_scheme){
        //TODO#2
        String curr_scheme = original.getCurrentScheme();
        String curr_rep = original.getPassword_representative();

        if (!curr_scheme.equals(target_scheme)){
            if(curr_scheme.equals("OCTAL")  && target_scheme.equals("IMAGE")){

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



            }else if(curr_scheme.equals("OCTAL") && target_scheme.equals("BINARY"){

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

            }else if(curr_scheme.equals("IMAGE") && target_scheme.equals("OCTAL")){

                StringBuilder new_rep = new StringBuilder(curr_rep);

                for (int i = 0; i <= 4; i+=2) {
                    char target = curr_rep.charAt(i);

                    switch (target){
                        case 'A': new_rep.setCharAt(i, '1');
                        case 'B': new_rep.setCharAt(i, '2');
                        case 'C': new_rep.setCharAt(i, '3');
                        case 'D': new_rep.setCharAt(i, '4');
                        case 'E': new_rep.setCharAt(i, '5');
                        case 'F': new_rep.setCharAt(i, '6');
                        case 'G': new_rep.setCharAt(i, '7');

                    }
                }

                original.setPassword_representative(new_rep.toString());

            }else if(curr_scheme.equals("IMAGE") && target_scheme.equals("BINARY")){
                String new_rep = "";
                String binary_digits = "1";

                for(int i = 0; i < 7; i++){

                    //get value at each index starting from 0;
                    char target = curr_rep.charAt(i);
                    if (Character.isLetter(target)) {

                        switch (target) {
                            case 'A':
                                for (int i = 0; i < 1; i++) {
                                    new_rep += binary_digits;
                                }
                            case 'B':
                                for (int i = 0; i < 2; i++) {
                                    new_rep += binary_digits;
                                }
                            case 'C':
                                for (int i = 0; i < 3; i++) {
                                    new_rep += binary_digits;
                                }
                            case 'D':
                                for (int i = 0; i < 4; i++) {
                                    new_rep += binary_digits;
                                }
                            case 'E':
                                for (int i = 0; i < 5; i++) {
                                    new_rep += binary_digits;
                                }
                            case 'F':
                                for (int i = 0; i < 6; i++) {
                                    new_rep += binary_digits;
                                }
                            case 'G':
                                for (int i = 0; i < 7; i++) {
                                    new_rep += binary_digits;
                                }

                        }
                    }else {
                        int value = Character.getNumericValue(target);

                        //add digits to the new_representative
                        for (int j = 0; j < value; j++) {
                            new_rep += binary_digits;
                        }

                    }
                    //switch digit (1/0)
                    if (binary_digits.equals("1")){binary_digits = "0";}
                    else {binary_digits = "1";}

                }
                original.setPassword_representative(new_rep);

            }else if(curr_scheme.equals("BINARY") && target_scheme.equals("OCTAL")){
                int len = curr_rep.length();
                String new_rep = "";
                boolean next_digit = false;
                int counter = 0;

                for (int i = 0; i < len; i++){
                    char target = curr_rep.charAt(i);
                    char next = curr_rep.charAt(i+1);


                    if (next_digit){
                        new_rep += Integer.toString(counter);
                        next_digit = false;
                        counter = 0;
                    }

                    if (target != next){
                        next_digit = true;

                    }
                    counter++;

                }
                if (counter!=0){
                    new_rep += Integer.toString(counter);
                }

                original.setPassword_representative(new_rep);


            }else { // curr_scheme == "BINARY" && target_scheme == "IMAGE"int len = curr_rep.length();
                int len = curr_rep.length();
                String new_rep = "";
                boolean next_digit = false;
                int counter = 0;

                for (int i = 0; i < len; i++){
                    char target = curr_rep.charAt(i);
                    char next = curr_rep.charAt(i+1);


                    if (next_digit){
                        new_rep += Integer.toString(counter);
                        next_digit = false;
                        counter = 0;
                    }

                    if (target != next){
                        next_digit = true;

                    }
                    counter++;

                }
                if (counter!=0){
                    new_rep += Integer.toString(counter);
                }

                StringBuilder updated_rep = new StringBuilder(new_rep);

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



            }

            original.setCurrentScheme(target_scheme);


        }
        return null;
    }
}
