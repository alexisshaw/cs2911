package Game;

import Cards.Card;

import java.util.Vector;

/**
 * Created by IntelliJ IDEA.
 * User: ates466
 * Date: 3/12/12
 * Time: 7:40 PM
 * Interface for an individual player to see what he can about the game
 */
public class PlayerView {

    //PlayerViews copy of the game state
    private GameState gameState;

    public int getPlayerId() {
        return playerId;
    }

    //Stores player's ID
    private int playerId;

    //Constructor for player view
    public PlayerView(GameState states, int id){
        //Stores the copy of the game state
        this.gameState = states;

        //Stores the player Id
        this.playerId = id;
    }

    public String getPlayerName(int playerID){
        return gameState.getPlayer(playerID).getName();
    }

    //returns the dice for making a turn
    public Die[] getDice(){
        return gameState.getPlayerState(playerId).getDice().clone();
    }
    //returns a specific dice
    public Die getDice(int index){
        return gameState.getPlayerState(playerId).getDice()[index];
    }
    
    public int getNoPlayers(){
        return gameState.getNumPlayers();
    }

    //returns the cards in the players hand
    public Vector<Card> getHand(){
        return new Vector<Card>(gameState.getPlayerState(playerId).getHand());
    }

    public Card[] getField(int playerId){
        return gameState.getPlayerState(playerId).getField().clone();
    }

    //returns amount of victory points that any player has
    public int getVictoryPoints(int playerNo) {
        return gameState.getPlayerState(playerNo).getVictoryPoints();
    }

    public int getMoney(int playerNo){
        return gameState.getPlayerState(playerNo).getMoney();
    }
}
