package util;
import java.util.Random;

public class RandomNumberGenerator {
    /**
     * Generate random 7 digit number, each digit range is 0-7
     * @return
     */
    public static int GenerateRandomNumber(){
        //TODO:
        Random rand = new Random();
        int n = rand.nextInt(500)+1;
        return n;
        //return -1;
    }

}
