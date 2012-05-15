package Game;

import card.Card;
import card.CardAction;
import Game.CLIPlayer.Player;

import java.util.*;

/**
 * Created by IntelliJ IDEA.
 * User: ates466
 * Date: 3/12/12
 * Time: 6:38 PM
 * Class for main running of a game.
 */
public class Game {
    
    //Declarations for Game.CLIPlayer.Player, Game.GameState classes
    private Player[] players;
    private GameState gameState;
    
    //bool for controlling when to end game
    private boolean gameOver;

    //Constructor for game class    
    public Game(Player[] playerArg){
        
        //Store players withing the Game.Game class
        this.players = playerArg;
        
        //Creates the new game board
        this.gameState = new GameState(this.players);
        
        //Create Game.PlayerView class for each player
        for (int i=0; i< players.length ; i++){
            players[i].setPlayerView(this.getView(i));
        }
        
        //Initialise gameOver to false
        gameOver = false;
    }

    //function for pregame setup
    public void prepare() throws Deck.DeckEmptyException{
        givePlayersInitialCards();
        playersSwapCards();
        playersPlaceCardsOnField();
    }

    private void playersPlaceCardsOnField() {
        boolean assignmentsValid = false;
        Vector<Map<Disk, Card>> cardAssignments = new Vector<Map<Disk, Card>>();
        cardAssignments.setSize(players.length);
        while(!assignmentsValid){
            //Have each player choose where to put their cards
            for(int i=0; i< players.length; i++){
                cardAssignments.set(i, players[i].initialCardPlacementInteraction());
            }
            assignmentsValid = this.verifyCardAssignments(cardAssignments);
        }

        //put cards on field.
        gameState.setField(cardAssignments);
        for(int i=0; i < cardAssignments.size(); i++){
            gameState.getPlayerState(i).removeFromHand(cardAssignments.get(i).values());
        }
    }

    private void playersSwapCards() {
        //Have each player choose 2 cards to give to opponent
        Card[][] chosenCards = new Card[players.length][2];
        for(int i=0; i< players.length; i++){
            chosenCards[i] = players[i].initialSwapCardsInteraction().toArray(new Card[1]);
        }
        for(int i=0; i< players.length; i++){
            //Give 2 cards to opponent.
            //Remove cards from hand
            gameState.getPlayerState(i).removeFromHand(Arrays.asList(chosenCards[i]));
            for (int j = 0; j < 2; j++) {
                gameState.getPlayerState(i).addToHand(chosenCards[(i + 1) % 2][j]);
            }
        }
    }

    private void givePlayersInitialCards() throws Deck.DeckEmptyException{
        //Give each player 4 cards
        for(int i=0; i< players.length; i++){
            for (int j = 0; j < 4; j++) {
               gameState.getPlayerState(i).addToHand(gameState.getDeck().drawCard());
            }
        }
    }

    public void runGame(){
        //Runs the main stage of the game;
        while(!gameOver){
            getNextRound();
        }
    }

    private void getNextRound() {
        int CurrentPlayer;

        //While the game is not over, each player has its turn
        for(CurrentPlayer=0; (CurrentPlayer< players.length) && !gameOver; CurrentPlayer++){
            getPlayersNextTurn(CurrentPlayer);
        }
    }

    private void getPlayersNextTurn(int playerID) {
        //player rolls their dice
        gameState.getPlayerState(playerID).rollDice();
        gameState.getPlayerState(playerID).removeVictoryPointsForEmpty();

        //bool for tracking whether the player has finished their turn
        boolean finished = false;

        //Finishes the game if a player runs out of victory points
        if(gameState.getPlayerState(playerID).getVictoryPoints() <=0){
            finished = true;
            gameOver=true;
            System.out.printf("%s wins\n", players[(playerID +1)%players.length].getName());
        }

        //Run players turn until pass
        while(!finished){
            finished = getPlayersNextAction(playerID, finished);
        }
    }

    private boolean getPlayersNextAction(int playerID, boolean finished) {
        PlayerAction nextAction;
        nextAction = players[playerID].getNextActionInteraction();
        switch(nextAction.getActionType()){
            case Pass:
                finished = true;
                break;
            case Money:
                if(gameState.getPlayerState(playerID).hasDie(nextAction.getDice()[0])){
                    gameState.getPlayerState(playerID).addMoney(nextAction.getDice()[0].getDieValue());
                    gameState.getPlayerState(playerID).removeDie(nextAction.getDice()[0]);
                }
                break;
            case Draw:
                if(gameState.getPlayerState(playerID).hasDie(nextAction.getDice()[0])){
                    try {
                        Card[] newCards = gameState.getDeck().drawCard(nextAction.getDice()[0].getDieValue());
                        gameState.getPlayerState(playerID).addToHand(players[playerID].newCardChoiceInteraction(Arrays.asList(newCards)));
                        gameState.getPlayerState(playerID).removeDie(nextAction.getDice()[0]);
                    } catch (Deck.DeckEmptyException e){
                        System.exit(1);
                    }
                }
                break;
            case Place:
                if(nextAction.getCard() == null){
                    System.out.println("There is no card to place");
                }else if(gameState.getPlayerState(playerID).getMoney() >= nextAction.getCard().getPrice()){
                    gameState.getPlayerState(playerID).placeOnField(nextAction.getCard(), nextAction.getLocation());
                    gameState.getPlayerState(playerID).addMoney(-nextAction.getCard().getPrice());
                }else{
                    System.out.println("You cannot afford to place this card");
                }
                break;
            case Activate:
                if(gameState.getPlayerState(playerID).hasDie(nextAction.getDice()[0]) && !gameState.getBlockedDisks().contains(new Disk(nextAction.getDice()[0].getDieValue()))){
                    gameState.getPlayerState(playerID).removeDie(nextAction.getDice()[0]);
                    if(gameState.getPlayerState(playerID).getField().get(new Disk(nextAction.getDice()[0].getDieValue())) != null){
                        CardView view = new CardView(gameState,playerID);
                        CardAction ac = gameState.getPlayerState(playerID).getField().get(new Disk(nextAction.getDice()[0].getDieValue())).getCardAction(view);
                        if(ac!= null){
                            gameState.applyAction(ac, playerID);
                        }
                    }
                }
                break;
            case Loop:
                break;
        }
        return finished;
    }

    //Creates a Game.PlayerView for a player
    private PlayerView getView(int playerId) {
        return new PlayerView(gameState, playerId);
    }
    
    //Checks that cards chosen by players in start phase matches the cards in their hand
    public boolean verifyCardAssignments(List<Map<Disk, Card>> cardAssignments){
        
        //initialize returnValue
        boolean returnValue = true;

        //For each player
        for (Map<Disk, Card> m : cardAssignments){
            int count = 0;

            //For each of their chosen cards
            for (Map.Entry<Disk,Card> e:m.entrySet()){
                count++;
                if (!gameState.getPlayerState(cardAssignments.indexOf(m)).getHand().contains(e.getValue())){
                    returnValue = false;
                    System.out.println(e.getValue().toString());
                }
                //to check that the player has not submitted multiple copies of the same card
            }
            //If there has not been a total of 4 cards chosen then return false
            if (count !=4){
                returnValue = false;
            }
        }
        return returnValue;
    }
}
