package card.Buildings;

import card.Card;
import card.CardAction;
import Game.CardView;

import java.util.Collection;
import java.util.LinkedList;

/**
 *
 * This Class implements Aesculapinum Card
 *
 * <p>This card enables the player to choose a discarded card
 * and add it back into their hand. Has a price of 5 and a defence
 * of 2.  Is a building.</p>
 *
 *
 * @author: ates466, kmcl552
 * @since 5/8/12
 *
 */
public class AesculapinumCard implements Card {
    /**
     *
     * @return The card's name in this case "Aesculapius"
     */
    @Override public String toString() {
        return "Aesculapinum";
    }

    /**
     *
     * @return true if building, false if not building
     */
    @Override public boolean isBuilding() {
        return true;
    }

    /**
     *
     * @return number of dice required to activate
     */
    @Override public int getNumberOfDiceRequired() {
        return 1;
    }

    /**
     *
     * @return price to place on board.
     */
    @Override public int getPrice() {
        return 5;
    }

    /**
     *
     * @return defence value.
     */
    @Override public int getDefence() {
        return 2;
    }

    /**
     * If card as an action associated with being placed on the board
     *
     * @param input CardView class for affecting cards if relevant, in this case not relevant
     * @return      null
     */
    public CardAction getCardPlacementAction(CardView input) {
        return null;
    }

    /**
     * Action that occurs when the card is activated
     *
     * @param input CardView for selecting cards
     * @return      CardAction to be carried out
     */
    @Override public CardAction getCardActivationAction(CardView input) {
        //gets the discard pile and reads out to player to select card to resurrect
        Collection<Card> discardPile = new LinkedList<Card>(input.getDiscard());
        Collection<Card> toAddToHand = input.getPlayer().cardChooser(
                "Please Choose a card to resurrect",
                "You Cannot resurrect a card",
                1,
                discardPile);
        CardAction action = new CardAction();
        //sets action to be carried out
        action.setToAddToHandFromDiscard(toAddToHand);
        return action;
    }

    /**
     *
     * @return card description.
     */
    @Override public String getCardOracle() {
        return "The temple of Asculapius (the God of healing) enables " +
                "the player to pick up any character card from the discard " +
                "pile and add it to their hand.";
    }
}
