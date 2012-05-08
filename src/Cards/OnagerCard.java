package Cards;

import Game.CardView;

/**
 * Created by IntelliJ IDEA.
 * User: ates466
 * Date: 5/8/12
 * Time: 1:15 PM
 * To change this template use File | Settings | File Templates.
 */
public class OnagerCard implements Card{
    public String toString() {
        return "Onager";
    }

    public boolean isBuilding() {
        return true;
    }

    public int getNumberOfDiceRequired() {
        return 1;
    }

    public int getPrice() {
        return 5;
    }

    public int getDefence() {
        return 4;
    }

    public CardAction getCardAction(CardView input) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public String getCardOracle() {
        return "This Roman catapult attacks any opposing building. The battle die is thrown once.";
    }
}
