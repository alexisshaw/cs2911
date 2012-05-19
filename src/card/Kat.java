package card;

import Game.CardView;
import Game.DiscardView;
import Game.Disk;

import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Alexis Shaw
 * Date: 5/20/12
 * Time: 12:47 AM
 * To change this template use File | Settings | File Templates.
 */
public class Kat implements Card {
    private int currentLife = 1;

    @Override
    public String toString() {
        return "Kat ("+getLifeString()+")";
    }
    private String getLifeString(){
        if(currentLife ==1){
            return "1st life";
        } else if (currentLife == 2){
            return "2nd life";
        } else if (currentLife == 3){
            return "3rd life";
        } else {
            return ""+ currentLife + "th life";
        }
    }

    @Override
    public boolean isBuilding() {
        return false;
    }

    @Override
    public int getNumberOfDiceRequired() {
        return 0;
    }

    @Override
    public int getPrice() {
        return 5;
    }

    @Override
    public int getDefence() {
        return 1;
    }

    @Override
    public CardAction getCardPlacementAction(CardView input) {
        CardAction returnValue = new CardAction();

        returnValue.setDiscardActorToAdd(new NineLives());

        return returnValue;
    }

    @Override
    public CardAction getCardActivationAction(CardView input) {
        return null;
    }

    private class NineLives implements DiscardActor{
        @Override
        public CardAction getAction(DiscardView in) {
            CardAction returnValue = new CardAction();
            if(in.getCardBeingDiscarded().equals(Kat.this)){
                if(Kat.this.currentLife < 9 && in.getManorOfDiscard().equals(DiscardView.DiscardManor.NORMAL)){
                    Map<Disk, Card> relayMap = new HashMap<Disk, Card>();
                    relayMap.put(in.getOriginalLocation(), Kat.this);
                    returnValue.setPlaceCards(relayMap);
                    Kat.this.currentLife++;
                } else {
                    returnValue.setDiscardActorToRemove(this);
                }
            }
            return returnValue;
        }

        @Override
        public Card getCard() {
            return Kat.this;
        }
    }

    @Override
    public String getCardOracle() {
        return "Mysterious and revered animal.  Its haunting cry lifts the heart of all who hear it.  Has nine lives.";
    }
}
