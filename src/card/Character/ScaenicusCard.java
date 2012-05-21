package card.Character;

import Game.Disk;
import card.Card;
import card.CardAction;
import Game.CardView;

import java.util.Collection;
import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.Set;

public class ScaenicusCard implements Card {
    private Card cloning;
    /*@Override
    public boolean equals(Object o) {
        return this == o || o == cloning || ((o.getClass() == ScaenicusCard.class)?((ScaenicusCard) o).cloning == this : cloning.equals(o)) ;
    } */

    //returns the name of the card
    public String toString(){
        return "Scaenicus";
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
        return 8;
    }

    //returns defence value of the card
    public int getDefence(){
        return 3;
    }

    public CardAction getCardPlacementAction(CardView input) {
        return null;
    }

    //Returns this cards Card Action
    public CardAction getCardActivationAction(CardView in){

        Set<Card> cardSet = new HashSet<Card>();
        for(int i=0;i<in.getMyPlayerView().getNoPlayers();i++){
            cardSet.addAll(in.getMyPlayerView().getField(i).values());
        }
        Collection<Card> toClone = in.getPlayer().cardChooser("Please Choose a card whose action you wish to copy", "You cannot copy a card", 1, cardSet );
        cloning = toClone.iterator().next();
        CardView toReport;
        if(in.getClass() == InternalScaenicusCardViewProxy.class){
            toReport = in;
        } else{
            toReport = new InternalScaenicusCardViewProxy(in,in.getCardKey(this));
        }
        CardAction returnValue = cloning.getCardActivationAction(toReport);
        cloning = null;
        return returnValue;
    }

    private class InternalScaenicusCardViewProxy extends CardView {
        Disk locationToReport;
        InternalScaenicusCardViewProxy(CardView view, Disk location){
            super(view);
            locationToReport = location;
        }

        @Override
        public Disk getCardKey(Card me) throws NoSuchElementException {
            return locationToReport;
        }
    }

    //returns description of card
    public String getCardOracle(){
        return "He performs no action of his own but can copy the action of any of the player's face-up character cards, and the next time round that of another.";
    }
}
