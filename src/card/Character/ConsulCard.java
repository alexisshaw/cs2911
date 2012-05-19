package card.Character;

import card.Card;
import card.CardAction;
import Game.CardView;
import Game.Die;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: ates466
 * Date: 5/1/12
 * Time: 5:30 PM
 * To change this template use File | Settings | File Templates.
 */
public class ConsulCard implements Card {
    public String toString() {
        return "Consul";
    }

    public boolean isBuilding() {
        return false;
    }

    public int getNumberOfDiceRequired() {
        return 1;
    }

    public int getPrice() {
        return 3;
    }

    public int getDefence() {
        return 3;
    }

    public CardAction getCardAction(CardView input) {
        Die toChange = input.getPlayer().diceChooser("Choose A Dice To Modify", "You cannot choose a die");
        boolean positive = input.getPlayer().conditionalInteraction(
                "Do you want to increase or decrease the value by 1 (I/d)",
                "i",
                "d"
        );
        int toChangeBy = positive? 1 : -1;
        Map<Die, Integer> dieChange = new HashMap<Die, Integer>();
        dieChange.put(toChange, new Integer(toChangeBy));
        CardAction toReturn = new CardAction();
        toReturn.setDiceModifications(dieChange);
        return toReturn;
    }

    public String getCardOracle() {
        return "The score on an action die which has not yet been used can be increased or decreased by 1 point";
    }
}
