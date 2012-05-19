package card;

import Game.CardView;

/**
 * This File is liscensed under the CC/PD 1.0 liscense. and is in the package card
 * <p/>
 * User: ates466
 * Date: 5/19/12
 * Time: 5:11 PM
 */
public interface DefenseModificationActor {
    public boolean doesModify(Card c);
    public int getDefenseDelta(Card c);
}
