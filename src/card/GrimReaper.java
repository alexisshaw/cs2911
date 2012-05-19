package card;

import Game.CardView;

/**
 * Created with IntelliJ IDEA.
 * User: Alexis Shaw
 * Date: 5/20/12
 * Time: 1:21 AM
 * To change this template use File | Settings | File Templates.
 */
public class GrimReaper implements Card {
    @Override
    public String toString() {
        return "Grim Reaper";
    }

    @Override
    public boolean isBuilding() {
        return false;
    }

    @Override
    public int getNumberOfDiceRequired() {
        return 1;
    }

    @Override
    public int getPrice() {
        return 6;
    }

    @Override
    public int getDefence() {
        return 3;
    }

    @Override
    public CardAction getCardPlacementAction(CardView input) {
        return null;
    }

    @Override
    public CardAction getCardActivationAction(CardView input) {
        return null;
    }

    @Override
    public String getCardOracle() {
        return "Once placed on a disc this card provides a chance to cheat death." +
                "  The player's other character cards are returned to the player's"+
                " hand rather than to the discard pile whenever they are successfully"+
                " attacked and defeated by the opponent.";
    }
}
