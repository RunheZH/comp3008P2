package util;
import java.util.Random;

public class RandomNumberGenerator {
    /**
     * Generate random 7 digit number, each digit range is 1-8
     * @return
     */
    public static int GenerateRandomNumber(){
        //TODO:
        Random rand = new Random();
        int result = 0;
        int p = 1;
        for (int i = 0; i < 7; i++){
            int n = rand.nextInt(7);
            result += p*(n+1);
            p *= 10;
        }

        return result;
        //return -1;
    }

}
