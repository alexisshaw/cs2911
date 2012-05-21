package Game.testadapter;

import Game.Die;
import Game.Disk;
import framework.Rules;
import framework.cards.Card;

import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: Alexis Shaw
 * Date: 5/20/12
 * Time: 11:58 AM
 * To change this template use File | Settings | File Templates.
 */
public class GameState implements framework.interfaces.GameState {
    private Game.GameState.GameState gameState;

    public GameState(Game.GameState.GameState gameState){
        this.gameState = gameState;
    }
    /**
     * Get the current turn's player number
     * <p/>
     * <p>
     * This method will return an integer between 0 and
     * ({@link framework.Rules#NUM_PLAYERS NUM_PLAYERS} - 1), as
     * specified in the GameState interface.
     * </p>
     *
     * @return the number of the current player
     */
    @Override
    public int getWhoseTurn() {
        return gameState.getCurrentPlayerID();
    }

    /**
     * Set the current player.
     * <p/>
     * <p>
     * This method sets which player is currently having a turn. Valid
     * inputs are between 0 and ({@link framework.Rules#NUM_PLAYERS
     * NUM_PLAYERS} - 1) inclusive.
     * </p>
     *
     * @param player the player whose turn it will be
     */
    @Override
    public void setWhoseTurn(int player) {
        gameState.setCurrentPlayerID(player);
    }

    /**
     * Gets the GameState's current deck.
     * <p/>
     * <p>
     * The current deck of the GameState is to be returned as a List of
     * Cards. The first item in the list is the next card that would be
     * drawn from the deck, and so on.
     * </p>
     *
     * @return the current GameState deck
     */
    @Override
    public List<Card> getDeck() {
        Stack<card.Card> internalList = gameState.getDeck().getDeckStack();
        List<Card>      returnList    = new ArrayList<Card>(internalList.size());
        for (card.Card c : internalList){
            returnList.add(getTestingCardFromInternalCard(c));
        }
        Collections.reverse(returnList);
        return returnList;
    }
    private Card getTestingCardFromInternalCard(card.Card in){
        return AssetTranslator.fromInternalToTestCard(in);
    }
    private card.Card getInternalCardFromTestingCard(Card in){
        return AssetTranslator.fromTestToInternalCard(in);
    }

    /**
     * Sets the GameState's current deck.
     * <p/>
     * <p>
     * The new deck of the GameState is to be given as a List of Cards.
     * The first item in the list is the next card that would be
     * drawn from the deck, and so on.
     * </p>
     *
     * @param deck the new deck of the GameState
     */
    @Override
    public void setDeck(List<Card> deck) {
        Stack<card.Card> toSet = new Stack<card.Card>();
        for (Card c: deck){
            toSet.add(getInternalCardFromTestingCard(c));
        }
        Collections.reverse(toSet);
        gameState.getDeck().setDeckStack(toSet);
    }

    /**
     * Gets the GameState's current discard pile.
     * <p/>
     * <p>
     * The current discard pile of the GameState is to be returned as a
     * List of Cards. The first item in the list is the most recently
     * discarded card, and so on.
     * </p>
     *
     * @return the current GameState discard pile
     */
    @Override
    public List<Card> getDiscard() {
        List<card.Card> internalList = gameState.getDiscardPile();
        List<Card>      returnList    = new ArrayList<Card>(internalList.size());
        for (card.Card c : internalList){
            returnList.add(getTestingCardFromInternalCard(c));
        }
        Collections.reverse(returnList);
        return returnList;
    }

    /**
     * Sets the GameState's current discard pile.
     * <p/>
     * <p>
     * The current discard pile of the GameState is to be given as a
     * List of Cards. The first item in the list is the most recently
     * discarded card, and so on.
     * </p>
     *
     * @param discard the new discard pile of the GameState
     */
    @Override
    public void setDiscard(List<Card> discard) {
        List<card.Card> toSet = new LinkedList<card.Card>();
        for (Card c: discard){
            toSet.add(getInternalCardFromTestingCard(c));
        }
        Collections.reverse(toSet);
        gameState.getDiscardPile().clear();
        gameState.getDiscardPile().addAll(toSet);
    }

    /**
     * Gets a player's current Sestertii.
     * <p/>
     * <p>
     * The current Sestertii (money) of the specified player is returned
     * as an integer. Correct player indexing is discussed in the
     * GameState interface header.
     * </p>
     *
     * @param playerNum which player's Sestertii to return
     * @return the player's Sestertii
     */
    @Override
    public int getPlayerSestertii(int playerNum) {
        return gameState.getPlayerState(playerNum).getMoney();
    }

    /**
     * Sets a player's current Sestertii.
     * <p/>
     * <p>
     * The new Sestertii (money) of the specified player is given
     * as an integer. Correct player indexing is discussed in the
     * GameState interface header.
     * </p>
     *
     * @param playerNum which player's Sestertii to set
     * @param amount    the quantity of Sestertii for the player to have
     */
    @Override
    public void setPlayerSestertii(int playerNum, int amount) {
        gameState.getPlayerState(playerNum).setMoney(amount);
    }

    /**
     * Gets a player's current Victory Points.
     * <p/>
     * <p>
     * The current Victory Points of the specified player are returned as
     * an integer. Correct player indexing is discussed in the
     * GameState interface header.
     * </p>
     *
     * @param playerNum which player's Victory Points to get
     * @return the player's Victory Points
     */
    @Override
    public int getPlayerVictoryPoints(int playerNum) {
        return gameState.getPlayerState(playerNum).getVictoryPoints();
    }

    /**
     * Gives a player VPs from the stockpile or give the stockpile VPs from a player.
     * <p/>
     * <p>
     * The new Victory Points of the specified player are given as an
     * integer. Correct player indexing is discussed in the GameState
     * interface header.
     * </p>
     * <p>
     * If the given amount is more than what the player already has,
     * then points need to be removed from the stockpile and given
     * to the player and vice versa.
     * </p>
     *
     * @param playerNum which player's Victory Points to set
     * @param points    the player's Victory Points
     */
    @Override
    public void setPlayerVictoryPoints(int playerNum, int points) {
        gameState.getPlayerState(playerNum).setVictoryPoints(points);
    }

    /**
     * Gets the contents of a player's current Hand.
     * <p/>
     * <p>
     * The contents of the hand of the specified player is returned as an
     * unordered collection of Cards. Correct player indexing is
     * discussed in the GameState interface header.
     * </p>
     *
     * @param playerNum which player's hand cards to get
     * @return the contents of the player's hand
     */
    @Override
    public Collection<Card> getPlayerHand(int playerNum) {
        Collection<card.Card> internalHand = gameState.getPlayerState(playerNum).getHand();
        Collection<Card>      testHand     = new LinkedList<Card>();
        for(card.Card c: internalHand){
            testHand.add(getTestingCardFromInternalCard(c));
        }
        return testHand;
    }

    /**
     * Sets the contents of a player's current Hand.
     * <p/>
     * <p>
     * The contents of the hand of the specified player is given as an
     * unordered collection of Cards. Correct player indexing is
     * discussed in the GameState interface header.
     * </p>
     *
     * @param playerNum which player's hand cards to set
     * @param hand      the contents of the the player's hand
     */
    @Override
    public void setPlayerHand(int playerNum, Collection<Card> hand) {
        Vector<card.Card> internalHand = new Vector<card.Card>();
        for(Card c: hand){
            internalHand.add(getInternalCardFromTestingCard(c));
        }
        gameState.getPlayerState(playerNum).setHand(internalHand);
    }

    /**
     * Gets the cards currently laid on a player's dice discs.
     * <p/>
     * <p>
     * The cards on the specified player's dice discs are returned in an
     * array of length {@link framework.Rules#NUM_DICE_DISCS
     * NUM_DICE_DISCS}. The 0th index in the array represents the dice
     * disc of value 1. Dice discs with no card are returned with
     * Card.NOT_A_CARD as their value. Correct player indexing is
     * discussed in the GameState interface header.
     * </p>
     *
     * @param playerNum which player's dice disc contents to get
     * @return the cards currently on the player's dice discs
     */
    @Override
    public Card[] getPlayerCardsOnDiscs(int playerNum) {
        Card[] playerTestField = new Card[Rules.NUM_DICE_DISCS];
        for(int i=0; i<playerTestField.length; i++){
            if(gameState.getPlayerState(playerNum).getField().containsKey(new Disk(i+1))){
                card.Card c = gameState.getPlayerState(playerNum).getField().get(new Disk(i+1));
                playerTestField[i] = getTestingCardFromInternalCard(c);
            } else {
                playerTestField[i] = Card.NOT_A_CARD;
            }
        }
        return playerTestField;
    }

    /**
     * Sets the contents of a player's dice discs.
     * <p/>
     * <p>
     * The cards on the specified player's dice discs are given in an
     * array of length {@link framework.Rules#NUM_DICE_DISCS
     * NUM_DICE_DISCS}. The 0th index in the array represents the dice
     * disc of value 1. Dice discs with no card are returned with
     * Card.NOT_A_CARD as their value. Correct player indexing is
     * discussed in the GameState interface header.
     * </p>
     *
     * @param playerNum which player's cards to set
     * @param discCards the cards to be placed on the dice discs
     */
    @Override
    public void setPlayerCardsOnDiscs(int playerNum, Card[] discCards) {
        gameState.getPlayerState(playerNum).getField().clear();
        for (int i=0; i<discCards.length; i++){
            Card c = discCards[i];
            if(c != Card.NOT_A_CARD){
                gameState.getPlayerState(playerNum).getField().put(new Disk(i+1),getInternalCardFromTestingCard(c));
            }
        }
    }

    /**
     * Gets the current player's action dice values.
     * <p/>
     * <p>
     * The values of the current player's action dice are returned in an
     * array in unspecified order. Dice are to be referred to by their
     * value, <i>not</i> by their position in this array.
     * </p>
     *
     * @return the current player's dice
     */
    @Override
    public int[] getActionDice() {
        int[] dice = new int[gameState.getPlayerState(gameState.getCurrentPlayerID()).getDice().size()];
        int i = 0;
        for (Die d: gameState.getPlayerState(gameState.getCurrentPlayerID()).getDice()){
            dice[i] = d.getDieValue();
            i++;
        }
        return dice;  //To change body of implemented methods use File | Settings | File Templates.
    }

    /**
     * Sets the current player's action dice values.
     * <p/>
     * <p>
     * The values of the current player's action dice are given in an
     * array in unspecified order. Dice are to be referred to by their
     * value, <i>not</i> by their position in this array.
     * </p>
     *
     * @param dice the new values of the current player's dice
     */
    @Override
    public void setActionDice(int[] dice) {
        gameState.getPlayerState(gameState.getCurrentPlayerID()).getDice().clear();
        for (int i: dice){
            gameState.getPlayerState(gameState.getCurrentPlayerID()).addDie(new Die(i));
        }
    }

    /**
     * Returns the number of Victory Points not currently held by a
     * player.
     * <p/>
     * <p>
     * The number of victory points not held by any player are returned.
     * This method is included so that the total number of Victory
     * Points in a game can be tested.
     * </p>
     *
     * @return the number of Victory Points not held by any player
     */
    @Override
    public int getPoolVictoryPoints() {
        return gameState.getCurrentVPPool();
    }

    /**
     * Returns true iff a game has been started AND the game has come to completion
     * otherwise return false.
     *
     * @return whether a game has come to completion
     */
    @Override
    public boolean isGameCompleted() {
        return gameState.isGameOver();
    }
}
