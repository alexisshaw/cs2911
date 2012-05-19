package card.Character;

import card.Card;
import card.CardAction;
import Game.CardView;
import Game.Disk;
import card.PlayerTurnChangeActor;

import java.util.Collection;

public class PraetorianusCard implements Card {
    //returns the name of the card
    public String toString(){
        return "Praetorianus";
    }

    //returns if the card is a building or not
    public boolean isBuilding(){
        return false;
    }

    //returns the amount of dice required to activate card
    public int getNumberOfDiceRequired(){
        return 1;
    }

    //returns dollar value of placing card
    public int getPrice(){
        return 4;
    }

    //returns defence value of the card
    public int getDefence(){
        return 4;
    }

    public CardAction getCardPlacementAction(CardView input) {
        return null;
    }

    //Returns this cards Card Action
    public CardAction getCardActivationAction(CardView in){
        CardAction returnValue = new CardAction();
        Collection<Disk> toBlock = in.getPlayer().diskChooser("Please Choose a disk to Block", "", 1, Disk.diskSet());
        returnValue.setToBlock(toBlock);
        returnValue.setPlayerTurnChangeActorToAdd(new nextTurnUnblock(toBlock, in));
        return returnValue;
    }
    private class nextTurnUnblock implements PlayerTurnChangeActor{
        private int oldTurn;
        private int playerID;
        private Collection<Disk> diskToUnblock;
        private CardView in;

        nextTurnUnblock(Collection<Disk> diskToUnblock, CardView in){
            this.oldTurn = in.getTurnNumber();
            this.playerID = in.getMyPlayerView().getPlayerId();
            this.diskToUnblock = diskToUnblock;
            this.in = in;
        }
        @Override
        public CardAction getAction() {
            CardAction returnValue = new CardAction();
            if(in.getTurnNumber() > oldTurn && in.getCurrentPlayerID() == playerID){
                returnValue.setToUnblock(diskToUnblock);
            }
            return returnValue;
        }
        public Card getCard(){
            return PraetorianusCard.this;
        }
        public int getOwnerPlayerID() {
            return playerID;
        }
    }

    //returns description of card
    public String getCardOracle(){
        return "Any of the opponent's dice disk can be blocked for one go.";
    }
}
