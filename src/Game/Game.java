package Game;

import Game.GameState.GameState;
import card.Card;
import card.CardAction;

import java.util.*;

/**
 * Created by IntelliJ IDEA.
 * User: ates466
 * Date: 3/12/12
 * Time: 6:38 PM
 * Class for main running of a game.
 */
public class Game {

    public static final int NO_CARDS_TO_SWAP = 2;
    public static final int NO_INITIAL_CARDS = 5;
    //Declarations for Game.CLIPlayer.Player, Game.GameState.GameState classes
    private Player[] players;
    private GameState gameState;
    public static final int VPPool = 36;

    public GameState getGameState() {
        return gameState;
    }

    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }

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
        Card[][] chosenCards = new Card[players.length][NO_CARDS_TO_SWAP];
        for(int i=0; i< players.length; i++){
            chosenCards[i] = players[i].initialSwapCardsInteraction().toArray(new Card[1]);
        }
        for(int i=0; i< players.length; i++){
            //Give 2 cards to opponent.
            //Remove cards from hand
            gameState.getPlayerState(i).removeFromHand(Arrays.asList(chosenCards[i]));
            for (int j = 0; j < NO_CARDS_TO_SWAP; j++) {
                gameState.getPlayerState(i).addToHand(chosenCards[(i + 1) % NO_CARDS_TO_SWAP][j]);
            }
        }
    }

    private void givePlayersInitialCards() throws Deck.DeckEmptyException{
        //Give each player 4 cards
        for(int i=0; i< players.length; i++){
            for (int j = 0; j < NO_INITIAL_CARDS; j++) {
               gameState.getPlayerState(i).addToHand(gameState.getDeck().drawCard());
            }
        }
    }

    public void runGame(){
        //Runs the main stage of the game;
        while(!gameState.isGameOver()){
            getPlayersNextAction();
        }
        notifyOfGameEnd(findWinner());
        gameState.setGameOver(true);
        System.exit(0);
    }
    private Player findWinner(){
        int mostVP = 0;
        for(int i=0; i< players.length;i++){
            if(gameState.getPlayerState(i).getVictoryPoints() > gameState.getPlayerState(mostVP).getVictoryPoints() ){
                mostVP = i;
            }
        }
        return players[mostVP];
    }
    private void notifyOfGameEnd(Player winner){
        for(Player p: players){
            if(p == winner) p.notifyOfWin();
            else p.notifyOfLoss(winner.getName());
        }
    }

    public void getPlayersNextAction() {
        int playerID = gameState.getCurrentPlayerID();
        PlayerAction nextAction;
        nextAction = players[playerID].getNextActionInteraction();
        switch(nextAction.getActionType()){
            case Pass:
                gameState.setCurrentPlayerID(gameState.getCurrentPlayerID() + 1);
                gameState.setTurnNumber(gameState.getTurnNumber() + 1);
                if(gameState.getCurrentPlayerID() >= players.length){
                    gameState.setCurrentPlayerID(0);
                }

                gameState.getPlayerState(gameState.getCurrentPlayerID()).rollDice();
                gameState.getPlayerState(gameState.getCurrentPlayerID()).removeVictoryPointsForEmpty();
                gameState.activateTurnChangeActors();
                gameState.checkVictoryPoints();
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
                        Card toAddToHand = players[playerID].cardChooser(
                                "Please choose One of the following cards to put into your hand:\n",
                                "",
                                1,
                                Arrays.asList(newCards)).iterator().next();
                        gameState.getPlayerState(playerID).addToHand(toAddToHand);
                        HashSet<Card> cardsToDiscard = new HashSet<Card>(Arrays.asList(newCards));
                        cardsToDiscard.remove(toAddToHand);
                        gameState.getDiscardPile().addAll(cardsToDiscard);
                        gameState.getPlayerState(playerID).removeDie(nextAction.getDice()[0]);
                    } catch (Deck.DeckEmptyException e){
                        System.exit(1);
                    }
                }
                break;
            case Place:
                if(nextAction.getCard() == null){
                    players[playerID].sendMessage("There is no card to place");
                }else if(gameState.getPlayerState(playerID).getMoney() >= nextAction.getCard().getPrice()){
                    gameState.getPlayerState(playerID).placeOnField(nextAction.getCard(), nextAction.getLocation());
                    gameState.getPlayerState(playerID).addMoney(-nextAction.getCard().getPrice());
                }else{
                    players[playerID].sendMessage("You cannot afford to place this card");
                }
                CardAction acp = nextAction.getCard().getCardPlacementAction(new CardView(gameState,playerID));
                if(acp!=null){
                    gameState.applyAction(acp,playerID,nextAction.getCard());
                }
                break;
            case Activate:
                if(gameState.getPlayerState(playerID).canActivateDisk(nextAction.getLocation(), nextAction.getDice()[0])){
                    if(nextAction.getLocation().equals(Disk.BRIBE_DISK)){
                        gameState.getPlayerState(playerID).addMoney(-nextAction.getDice()[0].getDieValue());
                    }
                    gameState.getPlayerState(playerID).removeDie(nextAction.getDice()[0]);
                    CardView view = new CardView(gameState,playerID);
                    Disk d;
                    if(!nextAction.getLocation().equals(Disk.BRIBE_DISK)){
                        d = new Disk(nextAction.getDice()[0].getDieValue());
                    } else {
                        d = Disk.BRIBE_DISK;
                    }
                    Card c = gameState.getPlayerState(playerID).getField().get(d);
                    CardAction ac = c.getCardActivationAction(view);
                    if(ac!= null){
                        gameState.applyAction(ac, playerID,c);
                    }
                }
                break;
            case Loop:
                break;
        }
        gameState.checkVictoryPoints();
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
                }
                //to check that the player has not submitted multiple copies of the same card
            }
            //If there has not been a total of 4 cards chosen then return false
            if (count != Game.NO_INITIAL_CARDS){
                returnValue = false;
            }
        }
        return returnValue;
    }

    public void getNames() {
        for(Player p: players){
            p.setPlayerName();
        }
    }
}
