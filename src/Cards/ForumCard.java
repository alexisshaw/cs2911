package Cards;

import Game.CardView;

/**
 * Created by IntelliJ IDEA.
 * User: ates466
 * Date: 5/8/12
 * Time: 1:14 PM
 * To change this template use File | Settings | File Templates.
 */
public class ForumCard implements Card {
    public String toString() {
        return super.toString();
    }

    public boolean isBuilding() {
        return true;
    }

    public int getNumberOfDiceRequired() {
        return 2;
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
        return "requires 2 action\n" +
                "dice: one to activate\n" +
                "the Forum and the\n" +
                "other to determine\n" +
                "how many victory\n" +
                "points the player\n" +
                "receives.";
    }
}
