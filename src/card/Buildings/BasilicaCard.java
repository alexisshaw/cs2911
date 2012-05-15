package card.Buildings;

import card.Card;
import card.CardAction;
import Game.CardView;

/**
 * Created by IntelliJ IDEA.
 * User: ates466
 * Date: 5/8/12
 * Time: 12:59 PM
 * To change this template use File | Settings | File Templates.
 */
public class BasilicaCard implements Card {
    public String toString() {
        return "Basilica";
    }

    public boolean isBuilding() {
        return true;
    }

    public int getNumberOfDiceRequired() {
        return 0;
    }

    public int getPrice() {
        return 5;
    }

    public int getDefence() {
        return 6;
    }

    public CardAction getCardAction(CardView input) {
        return null;
    }

    public String getCardOracle() {
        return "If a Forum is activated (it must lie directly next to the basilica), the player " +
                "gets 2 more victory points. The Basilica itself is not activiated.";
    }
}
