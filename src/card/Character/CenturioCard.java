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
 * Date: 5/8/12
 * Time: 11:33 AM
 * To change this template use File | Settings | File Templates.
 */
public class CenturioCard implements Card {
    public String toString() {
        return "Centurio";
    }

    public boolean isBuilding() {
        return false;
    }

    public int getNumberOfDiceRequired() {
        return 0;
    }

    public int getPrice() {
        return 9;
    }

    public int getDefence() {
        return 5;
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
        boolean useAnotherDie = !input.getMyPlayerView().getDice().isEmpty() && input.getPlayer().conditionalInteraction(
                "The battle die is a " + Integer.toString(BattleDie.getDieValue()) + "Would you like to add one fo your action die",
                "y",
                "n");
        CardAction returnValue = new CardAction();
        Die toAdd = new Die(0);
        if(useAnotherDie){
            toAdd = input.getPlayer().diceChooser("Please Select the die you wish to add");
            Collection<Die> toAddSet = new HashSet<Die>();
            returnValue.setDiceUsed(toAddSet);
        }
        if(!toAttackSet.isEmpty()) {
            Card toAttack = toAttackSet.iterator().next();
            if(toAttack.getDefence() <= (BattleDie.getDieValue() + toAdd.getDieValue())){
                Set<Card> killedSet = new HashSet<Card>();
                killedSet.add(toAttack);
                returnValue.setDestroyCards(killedSet);
            }
        }
        return returnValue;
    }

    public String getCardOracle() {
        return "attacks the card directly opposite, whether it is a " +
                "character or building card. " +
                "The value of an unused action die can be added to the " +
                "value of the battle die (the action die is then counted as used). " +
                "This is decided after the battle die has been thrown.";
    }
}
