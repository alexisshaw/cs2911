package Cards;

import Game.CardView;

/**
 * Created by IntelliJ IDEA.
 * User: ates466
 * Date: 5/8/12
 * Time: 1:02 PM
 * To change this template use File | Settings | File Templates.
 */
public class MachinaCard implements Card{
    public String toString() {
        return "Machina";
    }

    public boolean isBuilding() {
        return true;
    }

    public int getNumberOfDiceRequired() {
        return 1;
    }

    public int getPrice() {
        return 4;
    }

    public int getDefence() {
        return 4;
    }

    public CardAction getCardAction(CardView input) {
        return null;
    }

    public String getCardOracle() {
        return "The player picks up their building cards and lays them again on any dice " +
                "discs. Character cards can be covered.";
    }
}
