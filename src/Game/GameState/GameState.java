package Game.GameState;

import Game.*;
import Game.field.Field;
import card.*;
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
    private Map<Disk, Set<Card>> blockedDisks;
    private Collection<PlayerTurnChangeActor> playerTurnChangeActors;
    private Collection<DiscardActor> discardActors;
    private Collection<DefenseModificationActor> defenseModificationActors;
    private int turnNumber;

    public int getCurrentPlayerID() {
        return currentPlayerID;
    }

    public void setCurrentPlayerID(int currentPlayerID) {
        this.currentPlayerID = currentPlayerID;
    }

    private int currentPlayerID;


    private Player[] players;

    public Set<Disk> getBlockedDisks() {
        return blockedDisks.keySet();
    }

    public Collection<Card> getDiscardPile() {
        return discardPile;
    }

    public int getTurnNumber() {
        return turnNumber;
    }

    public void setTurnNumber(int turnNumber) {
        this.turnNumber = turnNumber;
    }
    public void incrementTurnNumber(){
        this.turnNumber++;
    }
    public void activateTurnChangeActors(){
        for(PlayerTurnChangeActor a: playerTurnChangeActors){
            applyAction(a.getAction(),a.getOwnerPlayerID(),a.getCard());
        }
    }

    //Constructor to create the object
    public GameState(Player[] players){
        //create a new array of player states
        this.playerStates = new PlayerState[players.length];
        discardPile = new LinkedList<Card>();
        discardActors = new HashSet<DiscardActor>();
        playerTurnChangeActors = new HashSet<PlayerTurnChangeActor>();
        defenseModificationActors = new HashSet<DefenseModificationActor>();
        blockedDisks = new HashMap<Disk, Set<Card>>();
        for (int i=0; i<players.length;i++){
            playerStates[i] = new PlayerState(discardPile, discardActors, new gameDiscardActivator(i,this), blockedDisks.keySet()) ;
        }
        this.players = players;

        //creates the deck
        ourDeck = new Deck();

    }
    public int getNumPlayers(){
        return playerStates.length;
    }
    class gameDiscardActivator implements Field.DiscardActivator {
        private int i;
        private GameState g;
        gameDiscardActivator (int i, GameState g){
            this.i = i;
            this.g = g;
        }
        @Override
        public void applyAction(CardAction a, Card c){
            g.applyAction(a, i, c);
        }

        @Override
        public DiscardView getDiscardView(Card responsible, Card toDiscard, DiscardView.DiscardManor manor, Disk location) {
            return new DiscardView(toDiscard,manor, location);
        }
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
    public void applyAction(CardAction input, int playerId, Card card){
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
                playerStates[i].setVictoryPoints(playerStates[i].getVictoryPoints() + victoryPointsToChange[i], getCurrentVPPool());
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
            playerStates[playerId].setVictoryPoints(playerStates[playerId].getVictoryPoints() + input.getVictoryPointsToAdd(), getCurrentVPPool());
        }
        if(input.getToRemoveFromDeck() != null){
            ourDeck.getDeck().removeAll(input.getToRemoveFromDeck());
        }
        if(input.getDiceUsed()!=null){
            playerStates[playerId].getDice().removeAll(input.getDiceUsed());
        }
        if(input.getToBlock() != null){
            addBlocks(input.getToBlock(), card);
        }
        if(input.getToUnblock() != null){
            removeBlocks(input.getToUnblock(), card);
        }
        if(input.getDiscardActorToAdd() != null){
            discardActors.add(input.getDiscardActorToAdd());
        }
        if(input.getDiscardActorToRemove() != null){
            discardActors.remove(input.getDiscardActorToRemove());
        }
        if(input.getPlayerTurnChangeActorToAdd()!= null){
            playerTurnChangeActors.add(input.getPlayerTurnChangeActorToAdd());
        }
        if(input.getPlayerTurnChangeActorToRemove() != null){
            playerTurnChangeActors.remove(input.getPlayerTurnChangeActorToRemove());
        }
        if(input.getDefenseModificationActorToAdd() != null){
            defenseModificationActors.add(input.getDefenseModificationActorToAdd());
        }
        if(input.getDefenseModificationActorToRemove() != null){
            defenseModificationActors.remove(input.getDefenseModificationActorToRemove());
        }
    }
    public void addBlocks(Collection<Disk> disks, Card c){
        for(Disk d:disks){
            if(blockedDisks.containsKey(d)){
                blockedDisks.get(d).add(c);
            } else {
                Set<Card> s = new HashSet<Card>();
                s.add(c);
                blockedDisks.put(d,s);
            }
        }
    }
    public void removeBlocks(Collection<Disk> disks, Card c){
        for(Disk d:disks) if(blockedDisks.containsKey(d) && blockedDisks.get(d).contains(c)){
            blockedDisks.get(d).remove(c);
            if(blockedDisks.get(d).isEmpty()){
                blockedDisks.remove(d);
            }

        }
    }
    public int getCurrentVPPool(){
        int currentVPsInPlay = 0;
        for(PlayerState s:playerStates){
            currentVPsInPlay += s.getVictoryPoints();
        }
        return Game.VPPool - currentVPsInPlay;
    }

    public Collection<DefenseModificationActor> getDefenseModificationActors() {
        return defenseModificationActors;
    }
}
