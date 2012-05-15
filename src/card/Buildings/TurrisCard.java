package card.Buildings;

import card.Card;
import card.CardAction;
import Game.CardView;

/**
 * Created by IntelliJ IDEA.
 * User: ates466
 * Date: 5/8/12
 * Time: 1:16 PM
 * To change this template use File | Settings | File Templates.
 */
public class TurrisCard implements Card {
    public String toString() {
        return "Turris";
    }

    public boolean isBuilding() {
        return true;
    }

    public int getNumberOfDiceRequired() {
        return 0;
    }

    public int getPrice() {
        return 6;
    }

    public int getDefence() {
        return 6;
    }

    public CardAction getCardAction(CardView input) {
        return null;
    }

    public String getCardOracle() {
        return "As long as the Turris is face-up, the defence value of all the player's " +
                "other face-up cards increases by 1.";
    }
}
