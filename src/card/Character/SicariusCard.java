package card.Character;

import card.Card;
import card.CardAction;
import Game.CardView;

import java.util.*;


public class SicariusCard  implements Card {
    //returns the name of the card
    public String toString(){
        return "Sicarius";
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
        return 9;
    }

    //returns defence value of the card
    public int getDefence(){
        return 2;
    }

    public CardAction getCardPlacementAction(CardView input) {
        return null;
    }

    //Returns this cards Card Action
    public CardAction getCardActivationAction(CardView in){
        Collection<Card> opposingCards = in.getOpposingCards(this);
        Collection<Card> cardsToChooseFrom = new HashSet<Card>();
        for(Card c:opposingCards) if(!c.isBuilding()) cardsToChooseFrom.add(c);
        Collection<Card> toDestroy = in.getPlayer().cardChooser("Please choose one of the following opponents cards to destroy",
                "You cannot destroy a card",
                1,
                cardsToChooseFrom);
        List<Card> toDestroyList = new LinkedList<Card>(toDestroy);
        toDestroyList.add(in.getMe(this));
        CardAction returnValue = new CardAction();
        returnValue.setDestroyCards(toDestroyList);
        return returnValue;
    }

    //returns description of card
    public String getCardOracle(){
        return "Eliminates an opposing face up character card. The opposing card and Sicarius are both discarded";
    }
}
