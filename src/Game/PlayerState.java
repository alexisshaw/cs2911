package Game;

import Cards.Card;

import java.util.*;

/**
 * Created by IntelliJ IDEA.
 * User: ates466
 * Date: 3/12/12
 * Time: 7:18 PM
 * Stores the game state specific for an individual player
 */
public class PlayerState {

    //Declarations for die, victory points, card variables
    private ArrayList<Die> dice;
    private int victoryPoints;
    private Vector<Card> hand;
    private Vector<Card> field;
    private Random randomness;
    private int money;

    //Constructor for creating player state
    public PlayerState(){
        //create empty array of dice
        dice = new ArrayList<Die>();

        //Set victory points to 10
        victoryPoints = 10;

        //Set initial money to 0
        money = 0;

        //Create hand and field
        hand = new Vector<Card>();
        randomness = new Random();
        field = new Vector<Card>();
        field.setSize(Die.getMaxDiceValue());
    }



    public int getMoney(){
        return money;
    }
    public void addMoney(int amount){
         money += amount;
    }
    public void removeVictoryPointsForEmpty(){
        this.victoryPoints -= getNumEmptyFieldLocations();
    }

    //return victory points
    public int getVictoryPoints() {
        return victoryPoints;
    }
    public void setVictoryPoints(int victoryPoints) {
        this.victoryPoints = victoryPoints;
    }



    //return player's field
    public Card[] getField() {
        Card[] returnValue = new Card[Die.getMaxDiceValue()];
        for(int i=0; i< returnValue.length;i++){
            try{
                returnValue[i] = field.get(i);
            } catch (IndexOutOfBoundsException e){
                returnValue[i] = null;
            }
        }
        return returnValue;
    }
    public Vector<Card> getFieldVector(){
        return field;
    }

    //set player's field
    public void setField(Card[] field) {
        this.field = new Vector<Card>(Arrays.asList(field));
    }
    public void placeOnField(Card cardToPlace, int index){
        field.set(index - 1,cardToPlace);
        this.removeFromHand(cardToPlace);
    }
    private int getNumEmptyFieldLocations() {
        int emptySpots = 0;
        for (int i = 0; i < Die.getMaxDiceValue(); i++) {
            if (this.getField()[i] == null){
                emptySpots++;
            }
        }
        return emptySpots;
    }

    //add new card to players hand
    public void addToHand(Cards.Card newCard) {
        hand.add(newCard);
    }
    //return player's hand
    public void removeFromHand(Cards.Card[] myCards){
        //for each card to be removed, remove card
        for (Card myCard : myCards) {
            hand.removeElement(myCard);
        }
    }
    public void removeFromHand(Cards.Card myCard){
        hand.removeElement(myCard);
    }

    public Vector<Card> getHand() {
        return hand;
    }

    public void rollDice(){
        ArrayList<Die> newDice = new ArrayList<Die>();
        for(int i=0; i< GameState.numActionDice; i++){
            newDice.add(new Die(randomness));
        }
        dice = newDice;
    }
    public void addDie(Die myDie){
        dice.add(myDie);
    }
    public void removeDie(Die myDie){
        dice.remove(myDie);
    }
    public boolean hasDie(Die myDie){
        return dice.contains(myDie);
    }
    //return dice array
    public Die[] getDice(){
        return dice.toArray(new Die[dice.size()]);
    }
}
