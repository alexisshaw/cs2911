package card.Buildings;

import Game.DiscardView;
import card.*;
import Game.CardView;

/**
 * Created by IntelliJ IDEA.
 * User: ates466
 * Date: 5/8/12
 * Time: 1:16 PM
 * To change this template use File | Settings | File Templates.
 */
public class TurrisCard implements Card {
    public String toString() {
        return "Turris";
    }

    public boolean isBuilding() {
        return true;
    }

    public int getNumberOfDiceRequired() {
        return 0;
    }

    public int getPrice() {
        return 6;
    }

    public int getDefence() {
        return 6;
    }

    public CardAction getCardAction(CardView input) {
        DefenseModificationActor dma = new decreaseOppositionCardsBy2(input);
        CardAction returnValue = new CardAction();

        returnValue.setDefenseModificationActorToAdd(dma);
        returnValue.setDiscardActorToAdd(new whenDiscardedDefenseModificationActor(dma));

        return returnValue;
    }
    private class decreaseOppositionCardsBy2 implements DefenseModificationActor{
        CardView cardView;
        decreaseOppositionCardsBy2(CardView in){
            cardView = in;
        }
        @Override
        public boolean doesModify(Card shouldModifyP) {
            boolean returnValue = false;
            for(Card c :cardView.getMyPlayerView().getField(cardView.getMyPlayerView().getPlayerId()).values()){
                if(c == shouldModifyP){
                    returnValue = true;
                }            
            }
            return returnValue;
        }

        @Override
        public int getDefenseDelta(Card c) {
            return doesModify(c)?2:0;
        }
    }
    private class whenDiscardedDefenseModificationActor implements DiscardActor {
        private DefenseModificationActor toRemove;

        whenDiscardedDefenseModificationActor(DefenseModificationActor toRemove){
            this.toRemove = toRemove;
        }
        @Override
        public CardAction getAction(DiscardView discardView) {
            CardAction returnValue = new CardAction();
            if(discardView.getCardBeingDiscarded().equals(TurrisCard.this)){
                returnValue.setDefenseModificationActorToRemove(toRemove);
            }
            return returnValue;
        }
        public Card getCard(){
            return TurrisCard.this;
        }
    }

    public String getCardOracle() {
        return "As long as the Turris is face-up, the defence value of all the player's " +
                "other face-up cards increases by 1.";
    }
}
