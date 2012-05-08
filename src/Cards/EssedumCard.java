package Cards;

import Game.CardView;

/**
 * Created by IntelliJ IDEA.
 * User: ates466
 * Date: 5/8/12
 * Time: 12:36 PM
 * To change this template use File | Settings | File Templates.
 */
public class EssedumCard implements Card{
    public String toString() {
        return "Essedum";
    }

    public boolean isBuilding() {
        return false;
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
        return "The defence value of the opponent's face-up cards is reduced by 2.";
    }
}
