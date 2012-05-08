package Cards;

import Game.CardView;

/**
 * Created by IntelliJ IDEA.
 * User: ates466
 * Date: 5/8/12
 * Time: 1:14 PM
 * To change this template use File | Settings | File Templates.
 */
public class MercatusCard implements Card {
    public String toString() {
        return "Mercatus";
    }

    public boolean isBuilding() {
        return true;
    }

    public int getNumberOfDiceRequired() {
        return 1;
    }

    public int getPrice() {
        return 6;
    }

    public int getDefence() {
        return 3;
    }

    public CardAction getCardAction(CardView input) {
        return null;
    }

    public String getCardOracle() {
        return "The player gets\n" +
                "1 victory point for\n" +
                "every face-up Forum\n" +
                "that the opponent\n" +
                "has.";
    }
}
