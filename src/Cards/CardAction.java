package Cards;

import java.security.PublicKey;

/**
 * Created by IntelliJ IDEA.
 * User: ates466
 * Date: 3/26/12
 * Time: 9:03 PM
 * To change this template use File | Settings | File Templates.
 */
public class CardAction {
    Card[] LayCards = null;
    Card[] destroyCards = null;

    public Card[] getLayCards() {
        return LayCards;
    }

    public void setLayCards(Card[] layCards) {
        LayCards = layCards;
    }

    public Card[] getDestroyCards() {
        return destroyCards;
    }

    public void setDestroyCards(Card[] destroyCards) {
        this.destroyCards = destroyCards;
    }
}
