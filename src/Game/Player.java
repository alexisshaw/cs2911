package Game;

import Game.CLIPlayer.CliPlayerPrinter;
import Game.CLIPlayer.newPlayerAction;
import card.Card;

import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: Alexis Shaw
 * Date: 5/20/12
 * Time: 3:15 PM
 * To change this template use File | Settings | File Templates.
 */
public interface Player {
    public PlayerView getMyView();
    public String getName();
    public void setPlayerName();
    public void setPlayerView(PlayerView newView);

    public void notifyOfLoss(String winner);
    public void notifyOfWin();

    public void sendMessage(String in);

    public PlayerAction getNextActionInteraction();

    public boolean conditionalInteraction(String question, String trueChar, String falseChar);
    public int integerInteraction(String question, int maxVal, int minVal);
    public Collection<Card> initialSwapCardsInteraction();
    public Map<Disk,Card> initialCardPlacementInteraction();

    public Collection<Card> cardChooser(String message, String emptyMessage,  int numCards, Collection<Card> cardsToChoseFromIn);
    public Collection<Disk> diskChooser(String message, String emptyMessage,  int numCards, Collection<Disk> cardsToChoseFromIn);
    public Map<Disk, Card> cardPlacer(Collection<Card> cards, String titleMessage, String perCardMessage);

    public Die diceChooser(String message, String emptyMessage);
    public Die diceChooser(String message, String emptyMessage, List<Die> d);

    public void printGameState();
    public Map<Disk, Card> cardMultiPlacer(Collection<Card> toChooseFrom, boolean mustPlaceAll);
}
