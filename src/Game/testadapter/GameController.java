package Game.testadapter;

import Game.Deck;
import Game.Player;
import Game.PlayerAction;
import Game.PlayerView;
import card.Card;

import java.util.Collection;

/**
 * Created with IntelliJ IDEA.
 * User: Alexis Shaw
 * Date: 5/20/12
 * Time: 6:23 PM
 * To change this template use File | Settings | File Templates.
 */
public class GameController {
    public void performAction() {
    }

    public void useFollowingActivatorPlayerDelegate(Player p) {
    }

    public void ceaseUsingActivatorPlayerDelegate() {
    }

    public void setBattleDieRoll(int roll) {
    }

    public Deck getDeck() {
        return null;
    }

    public void nextTurn() {
    }

    ;

    public void setToSend(PlayerAction in) {
    }

    ;

    public PlayerView getMyPlayerView() {
        return null;
    }

    ;

    public interface toChooseFunctor {
        Collection<Card> cardToChoose(Collection<card.Card> chooseFrom);
    }

    public void setToChoose(toChooseFunctor functor) {
    }

    ;
}
