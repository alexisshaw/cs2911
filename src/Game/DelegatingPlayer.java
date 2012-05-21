package Game;

import card.Card;

import java.util.*;

/**
 * This File is liscensed under the CC/PD 1.0 liscense. and is in the package Game
 * <p/>
 * User: ates466
 * Date: 5/21/12
 * Time: 7:10 PM
 */
public class DelegatingPlayer implements Player {
    Player delegate;
    PlayerView myView;

    public Player getDelegate() {
        return delegate;
    }

    public void setDelegate(Player delegate) {
        this.delegate = delegate;
    }
    
    public DelegatingPlayer(Player delegate){
        this.delegate = delegate;
    }

    @Override
    public PlayerView getMyView() {
        return myView;
    }

    @Override
    public String getName() {
        return "Player" + delegate.getName();
    }

    @Override
    public void setPlayerName() {
        delegate.setPlayerName();
    }

    @Override
    public void setPlayerView(PlayerView newView) {
        myView = newView;
    }

    @Override
    public void notifyOfLoss(String winner) {
        delegate.notifyOfLoss(winner);
    }

    @Override
    public void notifyOfWin() {
        delegate.notifyOfWin();
    }

    @Override
    public void sendMessage(String in) {
        delegate.sendMessage(in);
    }

    @Override
    public PlayerAction getNextActionInteraction() {
        return delegate.getNextActionInteraction();
    }

    @Override
    public boolean conditionalInteraction(String question, String trueChar, String falseChar) {
        return delegate.conditionalInteraction(question, trueChar, falseChar);
    }

    @Override
    public int integerInteraction(String question, int maxVal, int minVal) {
        return delegate.integerInteraction(question, maxVal, minVal);
    }

    @Override
    public Collection<Card> initialSwapCardsInteraction() {
        Collection<Card> toReturn = new LinkedList<Card>();
        Iterator<Card> it = myView.getHand().iterator();
        toReturn.add(it.next());
        toReturn.add(it.next());
        return toReturn;
    }

    @Override
    public Map<Disk, Card> initialCardPlacementInteraction() {
        Map<Disk, Card> toReturn = new HashMap<Disk,Card>();
        Iterator<Card> it = myView.getHand().iterator();
        toReturn.put(new Disk(1), it.next());
        toReturn.put(new Disk(2), it.next());
        toReturn.put(new Disk(3), it.next());
        toReturn.put(new Disk(4), it.next());
        toReturn.put(new Disk(5), it.next());
        return toReturn;
    }

    @Override
    public Collection<Card> cardChooser(String message, String emptyMessage, int numCards, Collection<Card> cardsToChoseFromIn) {
        return delegate.cardChooser(message, emptyMessage, numCards, cardsToChoseFromIn);
    }

    @Override
    public Collection<Disk> diskChooser(String message, String emptyMessage, int numCards, Collection<Disk> cardsToChoseFromIn) {
        return delegate.diskChooser(message, emptyMessage, numCards, cardsToChoseFromIn);
    }

    @Override
    public Map<Disk, Card> cardPlacer(Collection<Card> cards, String titleMessage, String perCardMessage) {
        return delegate.cardPlacer(cards, titleMessage, perCardMessage);
    }

    @Override
    public Die diceChooser(String message, String emptyMessage) {
        return delegate.diceChooser(message, emptyMessage);
    }

    @Override
    public Die diceChooser(String message, String emptyMessage, List<Die> d) {
        return delegate.diceChooser(message, emptyMessage, d);
    }

    @Override
    public void printGameState() {
        delegate.printGameState();
    }

    @Override
    public Map<Disk, Card> cardMultiPlacer(Collection<Card> toChooseFrom, boolean mustPlaceAll) {
        return delegate.cardMultiPlacer(toChooseFrom, mustPlaceAll);
    }
}
