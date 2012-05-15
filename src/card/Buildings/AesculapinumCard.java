package card.Buildings;

import card.Card;
import card.CardAction;
import Game.CardView;

import java.util.Collection;
import java.util.LinkedList;

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
        return 1;
    }

    @Override public int getPrice() {
        return 5;
    }

    @Override public int getDefence() {
        return 2;
    }

    @Override public CardAction getCardAction(CardView input) {
        Collection<Card> discardPile = new LinkedList<Card>(input.getDiscard());
        Collection<Card> toAddToHand = input.getPlayer().cardChooser(
                "Please Choose a card to resurrect",
                "You Cannot resurrect a card",
                1,
                discardPile);
        CardAction action = new CardAction();
        action.setToAddToHandFromDiscard(toAddToHand);
        return action;
    }

    @Override public String getCardOracle() {
        return "The temple of Asculapius (the God of healing) enables " +
                "the player to pick up any character card from the discard " +
                "pile and add it to their hand.";
    }
}
