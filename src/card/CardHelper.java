package card;

import Game.CardView;
import Game.Die;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * This File is liscensed under the CC/PD 1.0 liscense. and is in the package card
 * <p/>
 * User: ates466
 * Date: 5/19/12
 * Time: 5:39 PM
 */
public class CardHelper {
    public static CardAction attack(CardView input, int attack, Card toAttack) {
        CardAction returnValue = new CardAction();
        if(toAttack != null) {
            if(toAttack.getDefence() + input.getDefenseDelta(toAttack) <= attack){
                Set<Card> killedSet = new HashSet<Card>();
                killedSet.add(toAttack);
                returnValue.setDestroyCards(killedSet);
            }
        }
        return returnValue;
    }
}
