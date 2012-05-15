package card.Buildings;

import card.Card;
import card.CardAction;
import Game.CardView;

import java.util.Collection;
import java.util.HashSet;

/**
 * Created by IntelliJ IDEA.
 * User: ates466
 * Date: 5/8/12
 * Time: 1:02 PM
 * To change this template use File | Settings | File Templates.
 */
public class MachinaCard implements Card {
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

    public CardAction getCardAction(CardView view) {
        Collection<Card> cardsOnField = view.getMyPlayerView().getField(view.getMyPlayerView().getPlayerId()).values();
        Collection<Card> characterCardsOnField = new HashSet<Card>();
        for(Card c:cardsOnField) if(c.isBuilding()) characterCardsOnField.add(c);
        CardAction returnValue = new CardAction();
        returnValue.setReLayCards(view.getPlayer().cardMultiPlacer(characterCardsOnField,true));
        return null;
    }

    public String getCardOracle() {
        return "The player picks up their building cards and lays them again on any dice " +
                "discs. Character cards can be covered.";
    }
}
