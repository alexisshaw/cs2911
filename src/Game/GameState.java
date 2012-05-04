package Game;

import Cards.Card;
import Cards.CardAction;

import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: ates466
 * Date: 3/12/12
 * Time: 8:56 PM
 * Class to store the game state
 */
public class GameState {
    
    //set the number of action dice each player gets each turn
    public final static int numActionDice = 3;
    
    //declare the player states
    private PlayerState[] playerStates;
    

    //declare the deck
    private Deck ourDeck;

    private Player[] players;
    
    //Constructor to create the object
    public GameState(Player[] players){
        //create a new array of player states
        this.playerStates = new PlayerState[players.length];
        for (int i=0; i<players.length;i++){
            playerStates[i] = new PlayerState();
        }
        this.players = players;
        
        //creates the deck
        ourDeck = new Deck();

    }
    public int getNumPlayers(){
        return playerStates.length;
    }
    
    //return the deck
    public Deck getDeck() {
        return ourDeck;
    }

    //Assign cards to the field
    public void setField(Cards.Card[][] cardAssignments){
        for(int i=0; i < playerStates.length ; i++){
            playerStates[i].setField(cardAssignments[i]);
        }
    }

    public PlayerState getPlayerState(int playerId){
        return this.playerStates[playerId];
    }
    public Player getPlayer(int playerID){
        return players[playerID];
    }
    // applyAction will always destroy cards before placing other cards.
    public void applyAction(CardAction input, int playerId){
        if(input.getDestroyCards() != null){
            Card[] toDestroy = input.getDestroyCards();
            for(int i= 0; i<toDestroy.length; i++) {
                for(PlayerState p : playerStates){
                    if(toDestroy[i] != null && p.getFieldVector().contains(toDestroy[i])){
                        int index = p.getFieldVector().indexOf(toDestroy[i]);
                        p.getFieldVector().set(index, null);
                    }
                }
            }
        }
        if(input.getLayCards() != null){
            Card[] toLay = input.getLayCards();
            for(int i= 0; i<toLay.length; i++) {
                if(toLay[i] != null && playerStates[playerId].getHand().contains(toLay[i])){
                    playerStates[playerId].getHand().removeElement(toLay[i]);
                    playerStates[playerId].getFieldVector().set(i,toLay[i]);
                }
            }
        }
        if(input.getPlaceCards() != null){
            Card[] toPlace = input.getPlaceCards();
            for(int i= 0; i<toPlace.length; i++) {
                if(toPlace[i] != null){
                    playerStates[playerId].getFieldVector().set(i,toPlace[i]);
                }
            }
        }
        if(input.getAddToHand() != null){
            Card[] toAddToHand = input.getPlaceCards();
            for(int i= 0; i<toAddToHand.length; i++) {
                for(PlayerState p : playerStates){
                    if(toAddToHand[i] != null && p.getFieldVector().contains(toAddToHand[i])){
                        int index = p.getFieldVector().indexOf(toAddToHand[i]);
                        p.getFieldVector().set(index, null);
                        p.addToHand(toAddToHand[i]);
                    }
                }
            }
        }
        if(input.getVictoryPointsChangeArray() != null){
            int[] victoryPointsToChange = input.getVictoryPointsChangeArray();
            for(int i=0; i< victoryPointsToChange.length; i++){
                playerStates[i].setVictoryPoints(playerStates[i].getVictoryPoints() + victoryPointsToChange[i]);
            }
        }
        if(input.getDiceModifications() != null){
            for(Map.Entry<Die, Integer> d : input.getDiceModifications().entrySet()){
                for(PlayerState p : playerStates){
                    if(p.hasDie(d.getKey())){
                        p.removeDie(d.getKey());
                        p.addDie(new Die(d.getValue().intValue() + d.getKey().getDieValue()));
                    }
                }
            }
        }
        if(input.getMoneyToPay() != 0){
            playerStates[playerId].addMoney(-input.getMoneyToPay());
        }
        if(input.getVictoryPointsToAdd() != 0){
            playerStates[playerId].setVictoryPoints(playerStates[playerId].getVictoryPoints() + input.getVictoryPointsToAdd());
        }

    }
}
