package card.Buildings;

import card.Card;
import card.CardAction;
import Game.CardView;

/**
 * Created by IntelliJ IDEA.
 * User: ates466
 * Date: 5/8/12
 * Time: 1:15 PM
 * To change this template use File | Settings | File Templates.
 */
public class TempulmCard implements Card {
    public String toString() {
        return "Templum";
    }

    public boolean isBuilding() {
        return true;
    }

    public int getNumberOfDiceRequired() {
        return 0;
    }

    public int getPrice() {
        return 2;
    }

    public int getDefence() {
        return 2;
    }

    public CardAction getCardAction(CardView input) {
        return null;
    }

    public String getCardOracle() {
        return "If a Forum is activated (it must lie directly next to the Templum), " +
                "the third action die can be used to determine " +
                "the number of additional victory points which the " +
                "player gets from the general stockpile.\n" +
                "The action dice must not yet have been used in this go. " +
                "The Templum itself is not activated separately.";
    }
}
