package card.Character;

import card.Card;
import card.CardAction;
import Game.CardView;
import Game.Die;

import java.util.Collection;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

/**
 * Created by IntelliJ IDEA.
 * User: ates466
 * Date: 5/4/12
 * Time: 10:38 AM
 * To change this template use File | Settings | File Templates.
 */
public class VelitesCard implements Card {
    public String toString(){
        return "Velites";
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
        return 3;
    }

    public CardAction getCardAction(CardView input) {
        Collection<Card> opposingCards = input.getOpposingCards(this);
        Collection<Card> toAttackCollection = new HashSet<Card>();
        for(Card c: opposingCards) if(!c.isBuilding()) toAttackCollection.add(c);
        Die BattleDie = new Die(new Random());
        Collection<Card> toAttackSet = input.getPlayer().cardChooser(
                "Please Choose one of these cards to attack",
                "You cannot attack a card",
                1,
                toAttackCollection);
        CardAction returnValue = new CardAction();
        if(!toAttackSet.isEmpty()) {
            Card toAttack = toAttackSet.iterator().next();
            if(toAttack.getDefence() <= BattleDie.getDieValue()){
                Set<Card> killedSet = new HashSet<Card>();
                killedSet.add(toAttack);
                returnValue.setDestroyCards(killedSet);
            }
        }
        return returnValue;
    }

    public String getCardOracle() {
        return "attacks any opposing character card (does not have to be directly opposite). The battle die is thrown once.";
    }
}
