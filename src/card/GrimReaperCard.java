package card;

import Game.CardView;
import Game.DiscardView;
import org.slf4j.impl.StaticLoggerBinder;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Alexis Shaw
 * Date: 5/20/12
 * Time: 1:21 AM
 * To change this template use File | Settings | File Templates.
 */
public class GrimReaperCard implements Card {
    @Override
    public String toString() {
        return "Grim Reaper";
    }

    @Override
    public boolean isBuilding() {
        return false;
    }

    @Override
    public int getNumberOfDiceRequired() {
        return 0;
    }

    @Override
    public int getPrice() {
        return 6;
    }

    @Override
    public int getDefence() {
        return 3;
    }

    @Override
    public CardAction getCardPlacementAction(CardView input) {
        CardAction returnValue = new CardAction();

        returnValue.setDiscardActorToAdd(new GrimReaperDiscardActor(this, input));

        return returnValue;
    }

    @Override
    public CardAction getCardActivationAction(CardView input) {
        return null;
    }

    @Override
    public String getCardOracle() {
        return "Once placed on a disc this card provides a chance to cheat death." +
                "  The player's other character cards are returned to the player's"+
                " hand rather than to the discard pile whenever they are successfully"+
                " attacked and defeated by the opponent.";
    }
}
