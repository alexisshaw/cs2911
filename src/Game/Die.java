package Game;

import java.util.Random;

/**
 * Created by IntelliJ IDEA.
 * User: ates466
 * Date: 3/12/12
 * Time: 7:12 PM
 * Class for generic die objects in the game.
 */
public class Die {
    //Maximum value for the dice in the game
    private static final int maxDiceValue = 6;

    //Declarations for die
    private int value;

    public Die(Random myRandom){
        //Generate value between 0 and maxDiceValue - 1 inclusive
        value = myRandom.nextInt(maxDiceValue);

        //Shift dice value up by 1
        value++; //to get between 1 and 6
    }
    public Die(int value){
        this.value = value;
    }
    /*public Die(int value){
        this.value = value;
    }*/
    //returns the dice value
    public int getDieValue(){
        return value;
    }

    //returns the maximum dice value
    public static int getMaxDiceValue(){
        return maxDiceValue;
    }


    /*//Test function for die class
    public static void test(){
        System.out.println("testing Die");
        Die testObject;
        int[] results = new int[maxDiceValue + 1];
        for(int i=0; i< 10000; i++){
            Random randomness = new Random();
            testObject = new Die(randomness);
            assert (testObject.getDieValue() > 0);
            assert (testObject.getDieValue() < maxDiceValue);
            results[testObject.getDieValue()] ++;
        }
        for (int i=1; i< maxDiceValue; i++){
            assert (results[i] < 5000);
        }
    }*/
}
