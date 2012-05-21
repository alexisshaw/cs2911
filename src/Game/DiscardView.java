package Game;

import card.Card;

/**
 * This File is liscensed under the CC/PD 1.0 liscense. and is in the package Game
 * <p/>
 * User: ates466
 * Date: 5/19/12
 * Time: 3:10 PM
 */
public class DiscardView {
    public enum DiscardManor{
        NORMAL, COVER
    }
    private Card discarding;
    DiscardManor manorOfDiscard;
    Disk originalLocation;
    int originalField;

    public DiscardView(Card beingDiscarded, DiscardManor manor, Disk originalLocation, int originalField){
        manorOfDiscard = manor;
        discarding = beingDiscarded;
        this.originalLocation = originalLocation;
        this.originalField = originalField;
    }

    public void setOriginalLocation(Disk originalLocation) {
        this.originalLocation = originalLocation;
    }

    public int getOriginalField() {
        return originalField;
    }

    public Card getCardBeingDiscarded(){
        return discarding;
    }
    public DiscardManor getManorOfDiscard(){
        return manorOfDiscard;
    }
    public Disk getOriginalLocation(){
        return originalLocation;
    }
}
