package card.Character;

import card.Card;
import card.CardAction;
import Game.CardView;
import Game.Disk;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by IntelliJ IDEA.
 * User: ates466
 * Date: 5/4/12
 * Time: 10:34 AM
 * To change this template use File | Settings | File Templates.
 */
public class SenatorCard implements Card {
    public String toString() {
        return "Senator";
    }
    public boolean isBuilding() {
        return false;
    }

    public int getNumberOfDiceRequired() {
        return 1;
    }

    public int getPrice() {
        return 5;
    }

    public int getDefence() {
        return 1;
    }

    public CardAction getCardPlacementAction(CardView input) {
        return null;
    }

    public CardAction getCardActivationAction(CardView input) {
        //Get the Building cards the player has in their hand
        Set<Card> currentHand = new HashSet<Card>(input.getMyPlayerView().getHand());
        Set<Card> characterCardsInHand = new HashSet<Card>();
        for(Card c: currentHand) if(!c.isBuilding()) characterCardsInHand.add(c);
        //The player chooses to place cards on the field and where
        Map<Disk, Card> Location = input.getPlayer().cardMultiPlacer(characterCardsInHand, false);

        //Create and populate the CardAction
        CardAction myAction = new CardAction();
        int money = 0;
        for (Card c: Location.values()){
            money += c.getPrice();
        }
        if(input.getMyPlayerView().getMoney(input.getCurrentPlayerID()) >= money){
            myAction.setDestroyCards(Location.values());
            myAction.setLayCards(Location);
            myAction.setMoneyToPay(money);
        }
        return myAction;
    }

    public String getCardOracle() {
        return "Enables the player to lay as many character cards as they wish free of charge. The player is allowed to cover any cards";
    }
}
