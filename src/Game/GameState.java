package Game;

import card.Card;
import card.CardAction;
import Game.CLIPlayer.Player;

import java.util.*;

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
    private Collection<Card> discardPile;
    private Set<Disk> blockedDisks;

    private Player[] players;

    public Set<Disk> getBlockedDisks() {
        return blockedDisks;
    }

    public Collection<Card> getDiscardPile() {
        return discardPile;
    }

    //Constructor to create the object
    public GameState(Player[] players){
        //create a new array of player states
        this.playerStates = new PlayerState[players.length];
        discardPile = new LinkedList<Card>();
        for (int i=0; i<players.length;i++){
            playerStates[i] = new PlayerState(discardPile);
        }
        this.players = players;
        
        //creates the deck
        ourDeck = new Deck();
        
        blockedDisks = new HashSet<Disk>();
    }
    public int getNumPlayers(){
        return playerStates.length;
    }
    
    //return the deck
    public Deck getDeck() {
        return ourDeck;
    }

    //Assign cards to the field
    public void setField(List<Map<Disk,Card>> cardAssignments){
        for(int i=0; i < playerStates.length ; i++){
            playerStates[i].setField(cardAssignments.get(i));
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
            for(PlayerState p : playerStates){
                p.getFieldMap().values().removeAll(input.getDestroyCards());
            }
        }
        if(input.getLayCards() != null){
            playerStates[playerId].getHand().removeAll(input.getLayCards().values());
            playerStates[playerId].getFieldMap().putAll(input.getLayCards());
        }
        if(input.getReLayCards() != null){
            playerStates[playerId].getField().values().removeAll(input.getReLayCards().values(), false);
            playerStates[playerId].getField().putAll(input.getReLayCards());
        }
        if(input.getPlaceCards() != null){
            playerStates[playerId].getFieldMap().putAll(input.getPlaceCards());
        }
        if(input.getAddToHand() != null){
            Collection<Card> toAddToHand = input.getAddToHand();
            for(Card c : toAddToHand) {
                for(PlayerState p : playerStates){
                    if(c != null && p.getFieldMap().values().contains(c)){
                        p.getField().values().remove(c, false);
                        p.addToHand(c);
                    }
                }
            }
        }
        if(input.getToAddToHandFromDiscard() != null){
            for(Card c: input.getToAddToHandFromDiscard()){
                if(c!=null && discardPile.contains(c)){
                    discardPile.remove(c);
                    playerStates[playerId].addToHand(c);
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
                        p.addDie(new Die(d.getValue() + d.getKey().getDieValue()));
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
        if(input.getToRemoveFromDeck() != null){
            ourDeck.getDeck().removeAll(input.getToRemoveFromDeck());
        }
        if(input.getDiceUsed()!=null){
            playerStates[playerId].getDice().removeAll(input.getDiceUsed());
        }
        /*if(input.getToBlock() != null){
            gameState.blocked
        }*/

    }
}
