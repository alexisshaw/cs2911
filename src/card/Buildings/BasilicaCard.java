package card.Buildings;

import card.Card;
import card.CardAction;
import Game.CardView;

/**
 * Created with IntelliJ IDEA.
 * @author: ates466, kmcl552
 * @since 5/8/12
 *
 *
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
        return 6;
    }

    public int getDefence() {
        return 5;
    }

    public CardAction getCardPlacementAction(CardView input) {
        return null;
    }

    public CardAction getCardActivationAction(CardView input) {
        return null;
    }

    public String getCardOracle() {
        return "If a Forum is activated (it must lie directly next to the basilica), the player " +
                "gets 2 more victory points. The Basilica itself is not activiated.";
    }
}
