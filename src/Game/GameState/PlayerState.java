package Game.GameState;

import Game.Die;
import Game.Disk;
import Game.GameOverException;
import card.Card;
import Game.field.Field;
import card.DiscardActor;
import Game.Game;

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
    private Field field;
    private Random randomness;
    private int money;
    Set<Disk> blockedDisks;

    //Constructor for creating player state
    public PlayerState(List<Card> discard,
                       Collection<DiscardActor> discardActors,
                       Field.DiscardActivator discardActivator,
                       Set<Disk> blockedDisks
    ){
        //create empty array of dice
        dice = new ArrayList<Die>();

        //Set victory points to 10
        victoryPoints = 10;

        //Set initial money to 0
        money = 0;
        this.blockedDisks = blockedDisks;

        //Create hand and field
        hand = new Vector<Card>();
        randomness = new Random();
        field = new Field(discard, discardActors, discardActivator);
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public void setHand(Vector<Card> hand) {
        this.hand = hand;
    }

    public boolean canActivateDisk(Disk disk, Die bribeDie){
        if(disk == Disk.BRIBE_DISK){
            return dice.contains(bribeDie) &&
                    (money >= bribeDie.getDieValue()) &&
                    field.containsKey(disk) &&
                    !blockedDisks.contains(disk);
        } else {
            boolean haveCorrectDie = false;
            for (Die die:dice){
                if(die.getDieValue() == disk.intValue()) haveCorrectDie = true;
            }
            return field.containsKey(disk) &&
                    haveCorrectDie &&
                    field.containsKey(disk) &&
                    !blockedDisks.contains(disk);
        }
    }


    public int getMoney(){
        return money;
    }
    public void addMoney(int amount){
         money += amount;
    }
    public void removeVictoryPointsForEmpty() throws GameOverException{
        this.victoryPoints -= getNumEmptyFieldLocations();
        if (victoryPoints <= 0){
            throw new GameOverException();
        }
    }

    //return victory points
    public int getVictoryPoints() {
        return victoryPoints;
    }
    public void setVictoryPoints(int victoryPoints, int currentVPPoolSize) throws GameOverException{
        this.victoryPoints = victoryPoints;
        if (victoryPoints + currentVPPoolSize > Game.VPPool ){
            this.victoryPoints = Game.VPPool  - currentVPPoolSize;
            throw new GameOverException();
        }
    }



    //return player's field
    public Field getField() {
        return field;
    }
    public Map<Disk,Card> getFieldMap(){
        return field;
    }

    public void setField(Map<Disk,Card> field) {
        this.field.clear();
        this.field.putAll(field);
    }
    public void placeOnField(Card cardToPlace, Disk key){
        field.put(key, cardToPlace);
        this.removeFromHand(cardToPlace);
    }
    private int getNumEmptyFieldLocations() {
        int emptySpots = 0;
        for (Disk d: Disk.diskSet()) {
            if (!field.containsKey(d)){
                emptySpots++;
            }
        }
        return emptySpots;
    }

    //add new card to players hand
    public void addToHand(card.Card newCard) {
        hand.add(newCard);
    }
    //return player's hand
    public void removeFromHand(Collection<Card> myCards){
        //for each card to be removed, remove card
        for (Card myCard : myCards) {
            hand.removeElement(myCard);
        }
    }
    public void removeFromHand(card.Card myCard){
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
    public List<Die> getDice(){
        return dice;
    }
}
