package card;

import Game.DiscardView;

/**
 * This File is liscensed under the CC/PD 1.0 liscense. and is in the package card
 * <p/>
 * User: ates466
 * Date: 5/19/12
 * Time: 3:18 PM
 */
public interface DiscardActor {
    public CardAction getAction(DiscardView in);
    public void cleanupAfterRound();
    public Card getCard();
}
