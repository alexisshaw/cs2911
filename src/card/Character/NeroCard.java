package card.Character;

import card.Card;
import card.CardAction;
import Game.CardView;

import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: ates466
 * Date: 5/1/12
 * Time: 5:52 PM
 * To change this template use File | Settings | File Templates.
 */
public class NeroCard implements Card {
    public String toString() {
        return "Nero";
    }
    public boolean isBuilding() {
        return false;
    }

    public int getNumberOfDiceRequired() {
        return 1;
    }

    public int getPrice() {
        return 8;
    }

    public int getDefence() {
        return 9;
    }

    public CardAction getCardPlacementAction(CardView input) {
        return null;
    }

    public CardAction getCardActivationAction(CardView in) {
        Collection<Card> opposingCards = in.getOpposingCards(this);
        Collection<Card> cardsToChooseFrom = new HashSet<Card>();
        for(Card c:opposingCards) if(c.isBuilding()) cardsToChooseFrom.add(c);

        Collection<Card> toDestroy = in.getPlayer().cardChooser(
                "Please choose one of the following opponents cards to destroy",
                "You cannot destroy a card",
                1,
                cardsToChooseFrom);
        Collection<Card> toDestroyList = new LinkedList<Card>(toDestroy);
        toDestroyList.add(in.getMe(this));
        CardAction returnValue = new CardAction();
        returnValue.setDestroyCards(toDestroyList);
        return returnValue;
    }

    public String getCardOracle() {
        return "Destroys any face-up opposing building card. The card destroyed and Nero are both discarded";
    }
}
