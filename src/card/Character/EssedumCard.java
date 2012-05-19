package card.Character;

import card.Card;
import card.CardAction;
import Game.CardView;
import card.DefenseModificationActor;
import card.PlayerTurnChangeActor;

/**
 * Created by IntelliJ IDEA.
 * User: ates466
 * Date: 5/8/12
 * Time: 12:36 PM
 * To change this template use File | Settings | File Templates.
 */
public class EssedumCard implements Card {
    public String toString() {
        return "Essedum";
    }

    public boolean isBuilding() {
        return false;
    }

    @Override
    public CardAction getCardPlacementAction(CardView input) {
        return null;
    }

    public int getNumberOfDiceRequired() {
        return 1;
    }

    public int getPrice() {
        return 6;
    }

    public int getDefence() {
        return 3;
    }

    public CardAction getCardActivationAction(CardView input) {
        DefenseModificationActor dma = new decreaseOppositionCardsBy2(input);
        CardAction returnValue = new CardAction();

        returnValue.setDefenseModificationActorToAdd(dma);
        returnValue.setPlayerTurnChangeActorToAdd(new nextTurnRemoveDefenseModificationActor(dma, input));

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
            for(Card c :cardView.getOpposingCards(EssedumCard.this)){
                if(c == shouldModifyP){
                    returnValue = true;
                }
            }
            return returnValue;
        }

        @Override
        public int getDefenseDelta(Card c) {
            return doesModify(c)?-2:0;
        }
    }
    private class nextTurnRemoveDefenseModificationActor implements PlayerTurnChangeActor {
        private int oldTurn;
        private int playerID;
        private DefenseModificationActor toRemove;
        private CardView in;

        nextTurnRemoveDefenseModificationActor(DefenseModificationActor toRemove, CardView in){
            this.oldTurn = in.getTurnNumber();
            this.playerID = in.getMyPlayerView().getPlayerId();
            this.toRemove = toRemove;
            this.in = in;
        }
        @Override
        public CardAction getAction() {
            CardAction returnValue = new CardAction();
            if(in.getTurnNumber() > oldTurn && in.getCurrentPlayerID() == playerID){
                returnValue.setDefenseModificationActorToRemove(toRemove);
            }
            return returnValue;
        }
        public Card getCard(){
            return EssedumCard.this;
        }
        public int getOwnerPlayerID() {
            return playerID;
        }
    }

    public String getCardOracle() {
        return "The defence value of the opponent's face-up cards is reduced by 2.";
    }
}
