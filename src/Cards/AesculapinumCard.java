package Cards;

import Game.CardView;

/**
 * Created by IntelliJ IDEA.
 * User: ates466
 * Date: 5/8/12
 * Time: 12:57 PM
 * To change this template use File | Settings | File Templates.
 */
public class AesculapinumCard implements Card {
    /**
     *
     * @return The card's name in this case "Aesculapius"
     */
    @Override public String toString() {
        return "Aesculapinum";
    }

    @Override public boolean isBuilding() {
        return true;
    }

    @Override public int getNumberOfDiceRequired() {
        return 0;
    }

    @Override public int getPrice() {
        return 5;
    }

    @Override public int getDefence() {
        return 2;
    }

    @Override public CardAction getCardAction(CardView input) {
        return null;
    }

    @Override public String getCardOracle() {
        return "The temple of Asculapius (the God of healing) enables " +
                "the player to pick up any character card from the discard " +
                "pile and add it to their hand.";
    }
}
