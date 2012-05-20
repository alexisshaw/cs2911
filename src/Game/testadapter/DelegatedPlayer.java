package Game.testadapter;

import Game.*;
import card.Card;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Alexis Shaw
 * Date: 5/20/12
 * Time: 7:08 PM
 * To change this template use File | Settings | File Templates.
 */
public class DelegatedPlayer implements Player {
    @Override
    public PlayerView getMyView() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public String getName() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void setPlayerName() {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void setPlayerView(PlayerView newView) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void notifyOfLoss(String winner) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void notifyOfWin() {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void sendMessage(String in) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public PlayerAction getNextActionInteraction() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
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
    public Collection<Card> initialSwapCardsInteraction() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Map<Disk, Card> initialCardPlacementInteraction() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Collection<Card> cardChooser(String message, String emptyMessage, int numCards, Collection<Card> cardsToChoseFromIn) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Collection<Disk> diskChooser(String message, String emptyMessage, int numCards, Collection<Disk> cardsToChoseFromIn) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Map<Disk, Card> cardPlacer(Collection<Card> cards, String titleMessage, String perCardMessage) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Die diceChooser(String message, String emptyMessage) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Die diceChooser(String message, String emptyMessage, List<Die> d) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void printGameState() {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Map<Disk, Card> cardMultiPlacer(Collection<Card> toChooseFrom, boolean mustPlaceAll) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
