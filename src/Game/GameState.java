package Game;

import Cards.Card;
import Cards.CardAction;

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

    /*//Exchange victory points between players
    public void exchangeVictoryPoints(int playerIdFrom, int playerIdTo, int qty){
        int victoryPointsFrom = playerStates[playerIdFrom].getVictoryPoints();
        int victoryPointsTo   = playerStates[playerIdTo].getVictoryPoints();
        playerStates[playerIdFrom].setVictoryPoints(victoryPointsFrom - qty);
        playerStates[playerIdTo].setVictoryPoints(victoryPointsTo + qty);
    }
    //test function
    public static void testExchangeVictoryPoints(){
        System.out.println("Testing Game.GameState.exchangeVictoryPoints()");
        Player[] testPlayer = new Player[2];
        testPlayer[0] = new Player();
        testPlayer[1] = new Player();
        GameState testState = new GameState(testPlayer);
        testState.exchangeVictoryPoints(0,1,2);
        assert (testState.getPlayerState(0).getVictoryPoints() == 8);
        assert (testState.getPlayerState(1).getVictoryPoints() == 12);
    } */
    
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
    public void applyAction(CardAction input, int playerId){
        if(input.getLayCards() != null){
            Card[] toLay = input.getLayCards();
            for(int i= 0; i<toLay.length; i++) {
                if(toLay[i] != null && playerStates[playerId].getHand().contains(toLay[i])){
                    playerStates[playerId].getHand().removeElement(toLay[i]);
                    playerStates[playerId].getFieldVector().set(i,toLay[i]);
                }
            }
        }
        if(input.getDestroyCards() != null){
            Card[] toDestroy = input.getDestroyCards();
            for(int i= 0; i<toDestroy.length; i++) {
                if(toDestroy[i] != null && playerStates[playerId].getHand().contains(toDestroy[i])){
                    playerStates[playerId].getFieldVector().removeElement(toDestroy[i]);
                }
            }
        }

    }
}
