package Game.testadapter;

import Game.*;
import card.Card;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Alexis Shaw
 * Date: 5/20/12
 * Time: 5:42 PM
 * To change this template use File | Settings | File Templates.
 */
public class RegisteredActionPlayer implements Player {
    private String name;
    private PlayerView myView;
    private MoveMaker myMoveMaker;

    /**
     * these are Getters and setters
     */
    @Override
    public PlayerView getMyView() {
        return myView;
    }

    @Override
    public void setPlayerName() {
        name = "Player";
    }

    @Override
    public void setPlayerView(PlayerView newView) {
        myView = newView;
    }

    @Override
    public String getName() {
        return name;
    }

    /**
     * These Are initialisation actions
     */
    @Override
    public Collection<Card> initialSwapCardsInteraction() {
        return myView.getHand().subList(0, Game.NO_CARDS_TO_SWAP - 1);
    }

    @Override
    public Map<Disk, Card> initialCardPlacementInteraction() {
        int count = 0;
        Map<Disk, Card> cardPlacement = new HashMap<Disk, Card>();
        for (Card c : myView.getHand()) {
            count++;
            cardPlacement.put(new Disk(count), c);
        }
        return cardPlacement;
    }

    @Override
    public void notifyOfLoss(String winner) {
    }

    @Override
    public void notifyOfWin() {
    }

    @Override
    public void sendMessage(String in) {
    }

    @Override
    public PlayerAction getNextActionInteraction() {
        return myMoveMaker.getNextAction();
    }

    @Override
    public boolean conditionalInteraction(String question, String trueChar, String falseChar) {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public int integerInteraction(String question, int maxVal, int minVal) {
        return 0;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Collection<Card> cardChooser(String message, String emptyMessage, int numCards, Collection<card.Card> cardsToChoseFromIn) {
        return myMoveMaker.cardToChoose().cardToChoose(cardsToChoseFromIn);
    }

    @Override
    public Collection<Disk> diskChooser(String message, String emptyMessage, int numCards, Collection<Disk> cardsToChoseFromIn) {
        return null; //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Map<Disk, Card> cardPlacer(Collection<Card> cards, String titleMessage, String perCardMessage) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Die diceChooser(String message, String emptyMessage, List<Die> d) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }


    @Override
    public Map<Disk, Card> cardMultiPlacer(Collection<Card> toChooseFrom, boolean mustPlaceAll) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Die diceChooser(String message, String emptyMessage) {
        return diceChooser(message, emptyMessage, myView.getDice());
    }

    @Override
    public void printGameState() {
    }
}
